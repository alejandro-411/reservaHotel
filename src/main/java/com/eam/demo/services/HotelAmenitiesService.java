package com.eam.demo.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eam.demo.models.Department;
import com.eam.demo.models.HotelAmenities;
import com.eam.demo.repository.IHotelAmenitiesRepository;


@Service
public class HotelAmenitiesService {
    
    @Autowired
    private IHotelAmenitiesRepository hotelAmenitiesRepository;

    
    public List<HotelAmenities> getAllHotelAmenities() {
        return hotelAmenitiesRepository.findAll();
    }

    
    public Optional<HotelAmenities> getHotelAmenitiesById(long idHotelAmenities) {
        return hotelAmenitiesRepository.findById(idHotelAmenities);
    }

    
    public HotelAmenities saveHotelAmenities(HotelAmenities hotelAmenities) {
        return hotelAmenitiesRepository.save(hotelAmenities);
    }

    
    public boolean deleteHotelAmenities(long idHotelAmenities) {
        return hotelAmenitiesRepository.findById(idHotelAmenities)
        .map(hotelAmenities -> {
            hotelAmenitiesRepository.delete(hotelAmenities);
            return true;
        })
        .orElse(false);
}    

        public HotelAmenities updateHotelAmentity(Long id, HotelAmenities hotelAmenities) {
        return hotelAmenitiesRepository.findById(id)
                .map(hotelAmenity -> {
                    hotelAmenity.setAmenities(hotelAmenities.getAmenities());
                    hotelAmenity.setHotel(hotelAmenities.getHotel());
                    return hotelAmenitiesRepository.save(hotelAmenity);
                })
                .orElseThrow(() -> new RuntimeException("hotelAmenities not found with id " + id));
    }
}