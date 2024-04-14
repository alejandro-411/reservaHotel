package com.eam.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eam.demo.models.Amenities;

public interface IAmenitiesRepository  extends JpaRepository<Amenities, Long>{

}
