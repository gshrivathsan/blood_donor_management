
package com.example.bloodbank.controller;
import com.example.bloodbank.model.Hospital;
import com.example.bloodbank.repository.HospitalRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {

private final HospitalRepository repo;

public HospitalController(HospitalRepository repo){this.repo=repo;}

@GetMapping
public List<Hospital> getAll(){ return repo.findAll(); }

@PostMapping
public Hospital create(@RequestBody Hospital hospital){ return repo.save(hospital); }

}
