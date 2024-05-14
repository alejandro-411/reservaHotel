package com.eam.demo.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eam.demo.models.Amenities;
import com.eam.demo.models.City;
import com.eam.demo.models.Department;
import com.eam.demo.models.Hotel;
import com.eam.demo.models.HotelAmenities;
import com.eam.demo.models.Location;
import com.eam.demo.models.RoomType;
import com.eam.demo.repository.IAmenitiesRepository;
import com.eam.demo.repository.ICityRepository;
import com.eam.demo.repository.IDepartmentRepository;
import com.eam.demo.repository.IHotelAmenitiesRepository;
import com.eam.demo.repository.IHotelRepository;
import com.eam.demo.repository.IRoomRepository;
import com.eam.demo.repository.IRoomTypeRepository;
import com.eam.demo.repository.IlocationRepository;

@Service
public class HotelService {
    @Autowired
    private IlocationRepository locationRepository;
    @Autowired
    private IDepartmentRepository departmentRepository;
    @Autowired
    private ICityRepository cityRepository;

    @Autowired
    private IHotelRepository hotelRepository;

    @Autowired
    private IAmenitiesRepository amenitiesRepository;

    @Autowired
    private IHotelAmenitiesRepository hotelAmenitiesRepository;

    @Autowired
    private IRoomRepository roomRepository;

    @Autowired
    private IRoomTypeRepository roomTypeRepository;

    public List<Hotel> listHotels(){
        return hotelRepository.findAll();
    }

    public Long saveHotel(Hotel hotel, Long[] amenitiesIds) {
        Department departmentCreated = departmentRepository
                .save(new Department(null, hotel.getLocation().getCity().getDepartment().getDepartmentName()));

        City cityCreated = cityRepository.save(new City(0,
                hotel.getLocation().getCity().getCityName(), departmentCreated, null));

        Location locationSaved = locationRepository.save(new Location(0, hotel.getLocation().getAddress(),
                hotel.getLocation().getNeighborhood(), cityCreated));

        hotel.setLocation(locationSaved);

        Hotel hotelSaved = hotelRepository.save(hotel);

        Optional<Location> locationByID = locationRepository.findById(locationSaved.getLocationId());
        locationByID.ifPresent((location) -> {
            location.setHotel(hotelSaved);
            locationRepository.save(location);
        });

        List<Long> idsAmenities = Arrays.asList(amenitiesIds); // Ejemplo de lista de IDs de cursos

        List<Amenities> amenities = idsAmenities.stream()
                .map(idCurso -> amenitiesRepository.findById(idCurso)
                        .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + idCurso)))
                .collect(Collectors.toList());

        // Crear instancias de EstudianteCurso y guardarlas
        List<HotelAmenities> hotelAmenities = amenities.stream()
                .map(amenity -> new HotelAmenities(amenity, hotelSaved))
                .collect(Collectors.toList());

        hotelAmenitiesRepository.saveAll(hotelAmenities);

        return hotelSaved.getHotelId();
    }

    public Long saveHotelRest(Hotel hotel){
        Department departmentCreated = departmentRepository
                .save(new Department(null, hotel.getLocation().getCity().getDepartment().getDepartmentName()));

        City cityCreated = cityRepository.save(new City(0,
                hotel.getLocation().getCity().getCityName(), departmentCreated, null));

        Location locationSaved = locationRepository.save(new Location(0, hotel.getLocation().getAddress(),
                hotel.getLocation().getNeighborhood(), cityCreated));

        hotel.setLocation(locationSaved);

        Hotel hotelSaved = hotelRepository.save(hotel);

        Optional<Location> locationByID = locationRepository.findById(locationSaved.getLocationId());
        locationByID.ifPresent((location) -> {
            location.setHotel(hotelSaved);
            locationRepository.save(location);
        });

        return hotelSaved.getHotelId();

    }

    public Optional<Hotel> findHotelByID(Long idHotel){
        return hotelRepository.findById(idHotel)        ;
    }
}
