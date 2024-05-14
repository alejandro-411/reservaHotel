package com.eam.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eam.demo.models.Image;


public interface IImageRepository  extends JpaRepository<Image, Long>{

    List<Image> findByHotelHotelId(Long hotelId);

}
