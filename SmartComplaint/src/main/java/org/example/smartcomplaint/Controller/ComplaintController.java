package org.example.smartcomplaint.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.smartcomplaint.Dto.ComplaintDTO;
import org.example.smartcomplaint.Entity.Complaint;
import org.example.smartcomplaint.Service.ComplaintService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/complaints")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ComplaintController {

    private final ComplaintService complaintService;

    // Create Complaint
    @PostMapping
    public ResponseEntity<Complaint> createComplaint(
            @Valid @RequestBody ComplaintDTO complaintDTO) {

        Complaint complaint = complaintService.createComplaint(complaintDTO);

        return new ResponseEntity<>(complaint, HttpStatus.CREATED);
    }

    // Get All Complaints
    @GetMapping
    public ResponseEntity<List<Complaint>> getAllComplaints() {

        List<Complaint> complaints = complaintService.getAllComplaints();

        return ResponseEntity.ok(complaints);
    }

    // Get Complaint By Id
    @GetMapping("/{id}")
    public ResponseEntity<Complaint> getComplaintById(
            @PathVariable Long id) {

        Complaint complaint = complaintService.getComplaintById(id);

        return ResponseEntity.ok(complaint);
    }

    // Update Complaint
    @PutMapping("/{id}")
    public ResponseEntity<Complaint> updateComplaint(
            @PathVariable Long id,
            @Valid @RequestBody ComplaintDTO complaintDTO) {

        Complaint updatedComplaint =
                complaintService.updateComplaint(id, complaintDTO);

        return ResponseEntity.ok(updatedComplaint);
    }

    // Delete Complaint
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComplaint(
            @PathVariable Long id) {

        complaintService.deleteComplaint(id);

        return ResponseEntity.ok("Complaint Deleted Successfully");
    }

    // Update Complaint Status
    @PatchMapping("/{id}/status")
    public ResponseEntity<Complaint> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        Complaint complaint =
                complaintService.updateStatus(id, status);

        return ResponseEntity.ok(complaint);
    }
}