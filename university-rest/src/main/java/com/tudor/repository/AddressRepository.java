package com.tudor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tudor.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
