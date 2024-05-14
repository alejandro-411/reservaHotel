package com.eam.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eam.demo.models.City;
import com.eam.demo.repository.ICityRepository;

@Service
public class  CityService {
    
    @Autowired
    private ICityRepository cityRepository;

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    
    public City getCityById(long cityId) {
        return cityRepository.findById(cityId).orElse(null);
    }

    
    public City saveCity(City city) {
        return cityRepository.save(city);
    }

    
    public void deleteCity(long cityId) {
        cityRepository.deleteById(cityId);
    }
}