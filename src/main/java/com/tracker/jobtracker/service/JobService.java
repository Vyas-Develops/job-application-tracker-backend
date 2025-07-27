package com.tracker.jobtracker.service;

import com.tracker.jobtracker.model.Job;
import com.tracker.jobtracker.repository.JobApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service annotation indicates that this class is a Spring service
// This class contains business logic for managing job applications
@Service
public class JobService {

    private final JobApplicationRepository jobRepository;
    // Constructor injection for the JobApplicationRepository
    // This allows for better testability and adherence to the Dependency Inversion Principle
    // Autowired annotation is used to inject the JobApplicationRepository bean
    @Autowired
    public JobService(JobApplicationRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    // Get all jobs
    // This method retrieves all job applications from the repository
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    // Get a job by ID
    // Optional is used to handle cases where the job may not be found - option 1
    // or we can throw an exception if the job is not found - option 2
    // Here, we are using option 2 to throw a RuntimeException if the job is not found
    public Job getJobById(Long id) {
    return jobRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Job not found with id: " + id));
    }

    // Create a new job
    // save() is smart: if the ID is null, it will insert; if ID exists, it will update.
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    // Update a job
    public Job updateJob(Long id, Job updatedJob) {
        // This line checks if a job exists in the database with the given ID
        // It returns an Optional<Job>, which might contain a job or be empty if not found.
        return jobRepository.findById(id)
                .map(job -> {
                    //It returns an Optional<Job>, which might contain a job or be empty if not found.
                    job.setCompany(updatedJob.getCompany());
                    job.setTitle(updatedJob.getTitle());
                    job.setLocation(updatedJob.getLocation());
                    job.setStatus(updatedJob.getStatus());
                    job.setAppliedDate(updatedJob.getAppliedDate());
                    job.setNotes(updatedJob.getNotes());
                    return jobRepository.save(job);
                })
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + id));
    }

    // Delete a job
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
}
