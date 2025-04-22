package com.example.batminton_backend.repository;

import com.example.batminton_backend.entity.Batminton;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatmintonRepository extends JpaRepository<Batminton, Long> {
}
