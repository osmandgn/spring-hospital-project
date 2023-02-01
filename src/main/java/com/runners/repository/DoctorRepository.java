package com.runners.repository;

import com.runners.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface DoctorRepository extends JpaRepository <Doctor, Long>{


    boolean existsByTcNo(String tcNo);

    Optional<Doctor> findByName(String name);
}
