package com.runners.repository;

import com.runners.domain.Role;
import com.runners.domain.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {


    Optional<Role> findByName(UserRole type);
}
