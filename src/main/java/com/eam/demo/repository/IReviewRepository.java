package com.eam.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eam.demo.models.Review;


public interface IReviewRepository  extends JpaRepository<Review, Long>{

}
