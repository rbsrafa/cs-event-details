package com.cs.java.logreader.repositories;

import com.cs.java.logreader.models.ServerEventDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerEventDetailsRepository extends JpaRepository<ServerEventDetails, String> {
}
