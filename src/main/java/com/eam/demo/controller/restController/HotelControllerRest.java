package com.eam.demo.controller.restController;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import com.eam.demo.models.Hotel;
import com.eam.demo.models.Location;
import com.eam.demo.repository.IHotelRepository;
import com.eam.demo.repository.IlocationRepository;
import com.eam.demo.services.HotelService;

import java.util.List;

@RestController
@RequestMapping("/api/hotel") // Ruta base para todos los endpoints del hotel
public class HotelControllerRest {

    @Autowired
    private IHotelRepository hotelRepository;

    @Autowired
    private IlocationRepository ilocationRepository;

@Autowired
	private HotelService hotelService;


    @GetMapping("/list")
    public List<Hotel> mostrarListaHoteles() {
        return hotelService.listHotels();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Hotel> obtenerHotel(@PathVariable Long id) {
        return hotelRepository.findById(id)
                .map(hotel -> new ResponseEntity<>(hotel, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> crearHotel(@RequestBody Hotel hotel) {
        hotelService.saveHotelRest(hotel);
        return new ResponseEntity<>("Hotel Created", HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> eliminarHotel(@PathVariable Long id) {
        return hotelRepository.findById(id)
                .map(hotel -> {
                    ilocationRepository.findById(hotel.getLocation().getLocationId()).ifPresent((location)->{
                        ilocationRepository.delete(location);
                    });
                    hotelRepository.delete(hotel);
                    return new ResponseEntity<>("Hotel Eliminado", HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>("Hotel No Encontrado", HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> actualizarHotel(@PathVariable Long id, @RequestBody Hotel hotelActualizado) {
        return hotelRepository.findById(id)
                .map(hotel -> {
                    // Actualizar campos
                    hotel.setHotelName(hotelActualizado.getHotelName());
                    hotel.setAvailability(hotelActualizado.isAvailability());
                    hotel.setRating(hotelActualizado.getRating());
                    hotel.setLocation(hotelActualizado.getLocation());                   
                    hotelRepository.save(hotel);
                    return new ResponseEntity<>("Hotel Actualizado", HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>("Hotel No Encontrado", HttpStatus.NOT_FOUND));
    }
}
