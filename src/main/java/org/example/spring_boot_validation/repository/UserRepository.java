package org.example.spring_boot_validation.repository;


import org.example.spring_boot_validation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}