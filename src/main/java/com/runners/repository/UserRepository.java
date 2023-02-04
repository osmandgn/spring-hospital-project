package com.runners.repository;



import com.runners.domain.User;
import com.runners.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByUserName(String username) throws ResourceNotFoundException;
}
