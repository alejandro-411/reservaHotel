package com.eam.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eam.demo.models.Amenities;
import com.eam.demo.repository.IAmenitiesRepository;


@Service
public class AmenitiesService {
    
    @Autowired
    private IAmenitiesRepository amenitiesRepository;

    
    public List<Amenities> getAllAmenities() {
        return amenitiesRepository.findAll();
    }

    
    public Amenities getAmenitiesById(long amenitiesId) {
        return amenitiesRepository.findById(amenitiesId).orElse(null);
    }

    
    public Amenities saveAmenities(Amenities amenities) {
        return amenitiesRepository.save(amenities);
    }

    
    public void deleteAmenities(long amenitiesId) {
        amenitiesRepository.deleteById(amenitiesId);
    }
}
