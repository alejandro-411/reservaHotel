package com.eam.demo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eam.demo.models.Review;
import com.eam.demo.repository.IReviewRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private IReviewRepository reviewRepository;

    public List<Review> findAllReviews() {
        return reviewRepository.findAll();
    }

    public Optional<Review> findReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    public Review updateReview(Long id, Review reviewDetails) {
        return reviewRepository.findById(id)
                .map(review -> {
                    review.setTittleReview(reviewDetails.getTittleReview());
                    review.setContentReview(reviewDetails.getContentReview());
                    review.setUser(reviewDetails.getUser());
                    review.setHotel(reviewDetails.getHotel());
                    return reviewRepository.save(review);
                })
                .orElseThrow(() -> new RuntimeException("Review not found with id " + id));
    }

    public boolean deleteReview(Long id) {
        return reviewRepository.findById(id)
                .map(review -> {
                    reviewRepository.delete(review);
                    return true;
                })
                .orElse(false);
    }
}
