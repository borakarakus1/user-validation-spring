package com.bora.validatetest.repository;

import com.bora.validatetest.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request,Long> {
    Optional<Request> findById(Long id);
}
