
package com.example.bloodbank.controller;

import com.example.bloodbank.repository.*;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

private final DonorRepository donorRepo;
private final HospitalRepository hospitalRepo;
private final BloodRequestRepository requestRepo;

public DashboardController(DonorRepository donorRepo,
HospitalRepository hospitalRepo,
BloodRequestRepository requestRepo){
this.donorRepo=donorRepo;
this.hospitalRepo=hospitalRepo;
this.requestRepo=requestRepo;
}

@GetMapping("/stats")
public Map<String,Long> stats(){
    return Map.of(
        "donors", donorRepo.count(),
        "hospitals", hospitalRepo.count(),
        "requests", requestRepo.count()
    );
}
}
