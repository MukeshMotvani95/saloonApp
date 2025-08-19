package com.sm.saloonApp.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingRequest {
    private Long salonId;
    private LocalDateTime appointmentTime;
}

