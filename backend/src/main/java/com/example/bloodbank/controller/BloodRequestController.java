
package com.example.bloodbank.controller;
import com.example.bloodbank.model.BloodRequest;
import com.example.bloodbank.repository.BloodRequestRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class BloodRequestController {

private final BloodRequestRepository repo;

public BloodRequestController(BloodRequestRepository repo){this.repo=repo;}

@GetMapping
public List<BloodRequest> getAll(){ return repo.findAll(); }

@PostMapping
public BloodRequest create(@RequestBody BloodRequest request){
    request.setStatus("PENDING");
    return repo.save(request);
}

@PutMapping("/{id}/approve")
public BloodRequest approve(@PathVariable Long id){
    BloodRequest req = repo.findById(id).orElseThrow();
    req.setStatus("APPROVED");
    return repo.save(req);
}
}
