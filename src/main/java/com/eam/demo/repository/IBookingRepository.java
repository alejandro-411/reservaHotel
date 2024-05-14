package com.eam.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eam.demo.models.Booking;
import com.eam.demo.models.Hotel;


public interface IBookingRepository  extends JpaRepository<Booking, Long>{
    
    
    List<Booking> findByHotel(Hotel hotel);
    
    List<Booking> findByReservationEntryDateGreaterThanEqualAndReservationDepartureDateLessThanEqual
    (Date entryDate, Date departureDate);

    List<Booking> findByReservationEntryDateGreaterThanEqualAndReservationDepartureDateLessThanEqualAndHotel(
        Date reservationEntryDate, Date reservationDepartureDate, Hotel hotelId);
}
