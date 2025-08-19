package com.sm.saloonApp.controller;

import com.sm.saloonApp.dto.BookingRequest;
import com.sm.saloonApp.entity.Booking;
import com.sm.saloonApp.services.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;


    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Booking> createBooking(@RequestBody BookingRequest request, Authentication authentication){

        String userEmail = authentication.getName();
        return ResponseEntity.ok(bookingService.createBooking(request,userEmail));
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Booking>> getUserBooking(Authentication authentication){
        String userEmail = authentication.getName();
        return ResponseEntity.ok(bookingService.getUserBookings(userEmail));
    }

    @GetMapping("/salon/{salonId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Booking>> getSalonBookings(@PathVariable("salonId") Long salonId){
        return ResponseEntity.ok(bookingService.getBookingsForSalon(salonId));
    }

}
