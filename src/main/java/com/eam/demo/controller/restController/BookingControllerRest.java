package com.eam.demo.controller.restController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eam.demo.models.Booking;
import com.eam.demo.repository.IBookingRepository;
import com.eam.demo.services.BookingService;

@RestController
@RequestMapping("/api/booking")
public class BookingControllerRest {

    @Autowired
    private IBookingRepository bookingRepository;

    @Autowired
    private BookingService bookingService;

    @GetMapping("/list")
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(bookingService.listBookings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        return booking.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {

        return new ResponseEntity<>(bookingService.saveBooking(booking), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody Booking updatedBooking) {
        return bookingRepository.findById(id)
                .map(existingBooking -> {
                    existingBooking.setReservationEntryDate(updatedBooking.getReservationEntryDate());
                    existingBooking.setReservationDepartureDate(updatedBooking.getReservationDepartureDate());
                    existingBooking.setTotalReservationPrice(updatedBooking.getTotalReservationPrice());
                    existingBooking.setUser(updatedBooking.getUser());
                    existingBooking.setHotel(updatedBooking.getHotel());
                    existingBooking.setReserveStatus(updatedBooking.getReserveStatus());
                    return ResponseEntity.ok(bookingRepository.save(existingBooking));
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable Long id) {
        return bookingRepository.findById(id)
                .map(booking -> {
                    bookingRepository.delete(booking);
                    return ResponseEntity.ok().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
