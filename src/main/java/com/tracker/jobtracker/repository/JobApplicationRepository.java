package com.tracker.jobtracker.repository;

import com.tracker.jobtracker.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @repository annotation indicates that this interface is a Spring Data repository
// JpaRepository provides CRUD operations for the Job entity
@Repository
public interface JobApplicationRepository extends JpaRepository<Job, Long> {

// Job represents the job entity
// Long is the type of the primary key of the Job entity
}
