package com.eam.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eam.demo.models.Booking;
import com.eam.demo.repository.IBookingRepository;
import com.eam.demo.repository.IHotelRepository;
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


    public List<Booking> listBookings(){
        return iBookingRepository.findAll();
    }

    public Booking saveBooking(Booking booking) {
        

return iBookingRepository.save(booking);
    }
}
