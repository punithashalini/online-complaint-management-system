package org.example.smartcomplaint.Dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
    public class ComplaintDTO {

        @NotBlank(message = "Title is required")
        private String title;

        @NotBlank(message = "Description is required")
        private String description;
        private Long userId;
        private String priority;
    }

