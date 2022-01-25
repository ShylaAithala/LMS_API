package com.ninja.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ninja.lms.entity.Program;
import com.ninja.lms.repository.ProgramRepository;

@RestController
@RequestMapping("/")

public class ProgramController {

	@Autowired
	ProgramRepository programRepo;
	
	@GetMapping("/programs")
	public List<Program> getAllPrograms() {
		return programRepo.findAll();
		
	}

	
}
