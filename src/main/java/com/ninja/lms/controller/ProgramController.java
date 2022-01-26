package com.ninja.lms.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ninja.lms.entity.Program;
import com.ninja.lms.repository.ProgramRepository;
import com.ninja.lms.service.ProgramService;

@RestController
@RequestMapping("/")
public class ProgramController {

	@Autowired
	ProgramRepository programRepo;
	
	@Autowired
	ProgramService programService;
	
	@GetMapping("/programs")
	public List<Program> getAllPrograms() {
		return programService.getAllPrograms();
		
	}
	
	@PostMapping("/programs")
	public ResponseEntity<Program> createPrograms(@RequestBody Program newProgram) throws Exception {
		
		Program createdProgram = programService.createPrograms(newProgram);
		createdProgram.getProgram_id();
		return ResponseEntity.created(new URI("/programs/" + createdProgram.getProgram_id())).body(createdProgram);
	}
	
	@PutMapping("/programs/{id}")
	public ResponseEntity<Program> updateProgram(@RequestBody Program newProgram, @PathVariable int id) throws Exception {
		
		Program updatedProgram = programService.updateProgram(newProgram, id);
		return new ResponseEntity<>(updatedProgram, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/programs/{id}")
	public void deleteProgram(@PathVariable int id) throws Exception{
		programService.deleteProgram(id);
		
	}
	
}
