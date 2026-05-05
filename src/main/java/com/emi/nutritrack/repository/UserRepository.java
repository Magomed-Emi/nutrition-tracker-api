package com.emi.nutritrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emi.nutritrack.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{
}