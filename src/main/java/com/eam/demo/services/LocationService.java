package com.eam.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eam.demo.models.Location;
import com.eam.demo.repository.IlocationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    private IlocationRepository locationRepository;

    public List<Location> findAllLocations() {
        return locationRepository.findAll();
    }

    public Optional<Location> findLocationById(Long id) {
        return locationRepository.findById(id);
    }

    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    public Location updateLocation(Long id, Location locationDetails) {
        return locationRepository.findById(id)
                .map(location -> {
                    location.setAddress(locationDetails.getAddress());
                    location.setNeighborhood(locationDetails.getNeighborhood());
                    location.setCity(locationDetails.getCity());
                    location.setHotel(locationDetails.getHotel());
                    return locationRepository.save(location);
                })
                .orElseThrow(() -> new RuntimeException("Location not found with id " + id));
    }

    public boolean deleteLocation(Long id) {
        return locationRepository.findById(id)
                .map(location -> {
                    locationRepository.delete(location);
                    return true;
                })
                .orElse(false);
    }
}
