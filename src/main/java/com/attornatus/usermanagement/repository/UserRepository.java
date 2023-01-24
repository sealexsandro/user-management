package com.attornatus.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attornatus.usermanagement.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
