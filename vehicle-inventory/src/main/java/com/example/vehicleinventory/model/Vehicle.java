package com.example.vehicleinventory.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Make is required")
    private String make;

    @NotBlank(message = "Model is required")
    private String model;

    @NotNull(message = "Year is required")
    @Min(value = 1900, message = "Year must be after 1900")
    @Max(value = 2025, message = "Year must be before or in 2025")
    private Integer year;

    private String color;

    @NotBlank(message = "VIN is required")
    @Size(min = 17, max = 17, message = "VIN must be 17 characters")
    private String vin;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private Double price;

    @PositiveOrZero(message = "Mileage must be a positive number or zero")
    private Integer mileage;

    private String imageUrl; // URL to the vehicle's image
}
