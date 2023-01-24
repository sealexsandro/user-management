package com.attornatus.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attornatus.usermanagement.entities.Address;

@Repository
public interface AddressRepository  extends JpaRepository<Address, Long>{

}
