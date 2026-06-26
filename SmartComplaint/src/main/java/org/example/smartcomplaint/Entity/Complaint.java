package org.example.smartcomplaint.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Complaint {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank(message = "Title is required")
        private String title;

        @NotBlank(message = "Description is required")
        private String description;

        private String status = "PENDING";

        private String priority;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;
    }

