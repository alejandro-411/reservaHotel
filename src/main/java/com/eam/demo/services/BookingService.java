package com.eam.demo.services;

import java.awt.print.Book;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eam.demo.models.Booking;
import com.eam.demo.models.Hotel;
import com.eam.demo.models.Payments;
import com.eam.demo.models.Room;
import com.eam.demo.repository.IBookingRepository;
import com.eam.demo.repository.IHotelRepository;
import com.eam.demo.repository.IPaymentRepository;
import com.eam.demo.repository.IRoomRepository;
import com.eam.demo.repository.IUserRepository;

@Service
public class BookingService {

    @Autowired
    IBookingRepository iBookingRepository;

    @Autowired
    IUserRepository iUserRepository;

    @Autowired
    IHotelRepository iHotelRepository;

    @Autowired
    IRoomRepository iRoomRepository;

    @Autowired
    IPaymentRepository iPaymentRepository;

    @Autowired
    HotelService hotelService;

    public List<Booking> listBookings(){
        return iBookingRepository.findAll();
    }

    public Booking saveBooking(Booking booking, List<Long> roomIds) {
        // Comprobar disponibilidad y asignar habitaciones
        List<Room> rooms = iRoomRepository.findAllById(roomIds);
        checkRoomAvailability(rooms, booking.getReservationEntryDate());
        Booking savedBooking = iBookingRepository.save(booking);

        assignRoomsAndSetAvailability(rooms, savedBooking);
        savePayment(savedBooking);
        return savedBooking;
    }
    
    private void checkRoomAvailability(List<Room> rooms, Date reservationEntryDate) {
        for (Room room : rooms) {
            if (!room.isAvalability() && room.getBooking() != null && room.getBooking().getReservationDepartureDate().equals(reservationEntryDate)) {
                throw new RuntimeException("Room not available");
            }
        }
    }
    
    private void assignRoomsAndSetAvailability(List<Room> rooms, Booking booking) {
        for (Room room : rooms) {
            room.setBooking(booking); // Asignar booking
            room.setAvalability(false); // Actualizar disponibilidad
        }
    }
    
    private void savePayment(Booking savedBooking) {
        iPaymentRepository.save(new Payments(0L,
                savedBooking.getTotalReservationPrice(), "Created", savedBooking.getUser(), savedBooking));
    }

    public List<Booking> findBookingsByfilters(Date startDate, Date endDate, Long hotelId) {
        if (startDate != null && endDate != null && hotelId != null) {
            // Si se proporcionan las fechas y el ID del hotel, buscar por ambas
            Hotel hotel = hotelService.findHotelByID(hotelId).get();
            return iBookingRepository.findByReservationEntryDateGreaterThanEqualAndReservationDepartureDateLessThanEqualAndHotel(startDate, endDate, hotel);
        } else if (startDate != null && endDate != null) {
            // Si se proporcionan las fechas pero no el ID del hotel, buscar solo por fechas
            return iBookingRepository.findByReservationEntryDateGreaterThanEqualAndReservationDepartureDateLessThanEqual(startDate, endDate);
        } else if (hotelId != null) {
            // Si se proporciona el ID del hotel pero no las fechas, buscar solo por hotel
            Hotel hotel = hotelService.findHotelByID(hotelId).get();

            return iBookingRepository.findByHotel(hotel);
        } else {
            // Si no se proporcionan ni fechas ni ID del hotel, devolver todas las reservas
            return iBookingRepository.findAll();

        }

    }
}
