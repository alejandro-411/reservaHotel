package com.eam.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eam.demo.models.Booking;


public interface IBookingRepository  extends JpaRepository<Booking, Long>{

}
