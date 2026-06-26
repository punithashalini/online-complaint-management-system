package org.example.smartcomplaint.Repository;
import org.example.smartcomplaint.Entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface ComplaintRepository
            extends JpaRepository<Complaint, Long> {
    }

