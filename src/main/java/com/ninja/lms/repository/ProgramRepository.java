package com.ninja.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ninja.lms.entity.Program;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Integer>{

	
}
