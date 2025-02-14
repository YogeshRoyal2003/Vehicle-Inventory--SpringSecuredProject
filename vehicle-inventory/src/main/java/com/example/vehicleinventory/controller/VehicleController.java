package com.example.vehicleinventory.controller;

import com.example.vehicleinventory.model.Vehicle;
import com.example.vehicleinventory.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public String listVehicles(Model model, @RequestParam(required = false) String keyword) {
        List<Vehicle> vehicles;
        if (keyword != null && !keyword.isEmpty()) {
            vehicles = vehicleService.searchVehicles(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            vehicles = vehicleService.getAllVehicles();
        }
        model.addAttribute("vehicles", vehicles);
        return "vehicle-list";
    }

    @GetMapping("/{id}")
    public String vehicleDetails(@PathVariable Long id, Model model) {
        Optional<Vehicle> vehicle = vehicleService.getVehicleById(id);
        if (vehicle.isPresent()) {
            model.addAttribute("vehicle", vehicle.get());
            return "vehicle-details";
        } else {
            return "error"; // Handle the case where the vehicle is not found
        }
    }

    @GetMapping("/new")
    public String newVehicleForm(Model model) {
        model.addAttribute("vehicle", new Vehicle());
        return "vehicle-form";
    }

    @PostMapping("/save")
    public String saveVehicle(@Valid @ModelAttribute("vehicle") Vehicle vehicle, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "vehicle-form"; // Return to the form with error messages
        }
        vehicleService.saveVehicle(vehicle);
        return "redirect:/vehicles";
    }

    @GetMapping("/edit/{id}")
    public String editVehicleForm(@PathVariable Long id, Model model) {
        Optional<Vehicle> vehicle = vehicleService.getVehicleById(id);
        if (vehicle.isPresent()) {
            model.addAttribute("vehicle", vehicle.get());
            return "vehicle-form"; // Reuse the vehicle-form.html for editing
        } else {
            return "error"; // Or redirect to a 404 page
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return "redirect:/vehicles";
    }
}