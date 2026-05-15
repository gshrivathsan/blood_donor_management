
package com.example.bloodbank.controller;
import com.example.bloodbank.model.Donor;
import com.example.bloodbank.repository.DonorRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/donors")
@CrossOrigin("*")
public class DonorController {

private final DonorRepository repo;

public DonorController(DonorRepository repo){this.repo=repo;}

@GetMapping
public List<Donor> getAll(){ return repo.findAll(); }

@PostMapping
public Donor create(@RequestBody Donor donor){ return repo.save(donor); }

@GetMapping("/{id}")
public Donor getById(@PathVariable Long id){ return repo.findById(id).orElseThrow(); }

@PutMapping("/{id}")
public Donor update(@PathVariable Long id,@RequestBody Donor donor){
    donor.setId(id);
    return repo.save(donor);
}

@DeleteMapping("/{id}")
public String delete(@PathVariable Long id){
    repo.deleteById(id);
    return "Deleted";
}
}
