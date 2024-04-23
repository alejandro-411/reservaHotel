package com.eam.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eam.demo.models.Image;
import com.eam.demo.models.Review;


public interface IImageRepository  extends JpaRepository<Image, Long>{

}
