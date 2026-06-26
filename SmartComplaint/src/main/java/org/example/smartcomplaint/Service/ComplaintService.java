package org.example.smartcomplaint.Service;

import lombok.RequiredArgsConstructor;
import org.example.smartcomplaint.Dto.ComplaintDTO;
import org.example.smartcomplaint.Entity.Complaint;
import org.example.smartcomplaint.Entity.User;
import org.example.smartcomplaint.Exception.ResourceNotFoundException;
import org.example.smartcomplaint.Repository.ComplaintRepository;
import org.example.smartcomplaint.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final UserRepository userRepository;

    // Create Complaint
    public Complaint createComplaint(ComplaintDTO dto) {

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        Complaint complaint = new Complaint();

        complaint.setTitle(dto.getTitle());
        complaint.setDescription(dto.getDescription());
        complaint.setPriority(dto.getPriority());
        complaint.setStatus("PENDING");
        complaint.setUser(user);

        return complaintRepository.save(complaint);
    }

    // Get All Complaints
    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    // Get Complaint By ID
    public Complaint getComplaintById(Long id) {

        return complaintRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Complaint not found with id: " + id));
    }

    // Update Complaint
    public Complaint updateComplaint(Long id, ComplaintDTO dto) {

        Complaint complaint = getComplaintById(id);

        complaint.setTitle(dto.getTitle());
        complaint.setDescription(dto.getDescription());
        complaint.setPriority(dto.getPriority());

        if (dto.getUserId() != null) {

            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "User not found"));

            complaint.setUser(user);
        }

        return complaintRepository.save(complaint);
    }

    // Delete Complaint
    public void deleteComplaint(Long id) {

        Complaint complaint = getComplaintById(id);

        complaintRepository.delete(complaint);
    }

    // Update Complaint Status
    public Complaint updateStatus(Long id, String status) {

        Complaint complaint = getComplaintById(id);

        complaint.setStatus(status);

        return complaintRepository.save(complaint);
    }
}