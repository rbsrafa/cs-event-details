package com.cs.java.logreader.repositories;

import com.cs.java.logreader.models.EventDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventDetailsRepository extends JpaRepository<EventDetails, String> {
}
