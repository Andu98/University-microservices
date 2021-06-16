package com.tudor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tudor.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	
	
}
