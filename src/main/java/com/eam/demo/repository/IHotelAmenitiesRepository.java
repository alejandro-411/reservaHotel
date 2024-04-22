package com.eam.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eam.demo.models.HotelAmenities;

public interface IHotelAmenitiesRepository  extends JpaRepository<HotelAmenities, Long>{

}
