package com.tracker.jobtracker.model;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;

//Entity maps columns / structure of table in the database
// Data is a Lombok annotation that generates getters, setters, toString, equals, and hashCode methods
// NoArgsConstructor generates a no-argument constructor
// AllArgsConstructor generates a constructor with all fields as parameters
// Builder allows for a fluent API to create instances of the class
// @Entity annotation indicates that this class is a JPA entity
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job {
    // Job entity representing a job application
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String company;
    private String title;
    private String location;
    @Enumerated(EnumType.STRING)
    private JobStatus status;
    private LocalDate appliedDate;
    @Column(length = 1000)
    private String notes;
}
