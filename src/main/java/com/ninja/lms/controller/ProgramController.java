package com.ninja.lms.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import com.ninja.lms.service.ProgramService;

@RestController
@RequestMapping("/")
public class ProgramController {
	
	@Autowired
	ProgramService programService;
	
	/** Get all Program Details **/
	@GetMapping("/programs")
	public List<Program> getAllPrograms() {
		return programService.getAllPrograms();
	}
	
	/** Get Program Details for one program using ProgramId**/
	@GetMapping("/programs/{id}")
	public Optional<Program> getProgram(@PathVariable int id) {
		return programService.getProgram(id);
	}
	
	
	/** Create Program **/
	@PostMapping("/programs")
	public ResponseEntity<Program> createPrograms(@RequestBody Program newProgram) throws Exception {
		Program createdProgram = programService.createPrograms(newProgram);
		createdProgram.getProgram_id();
		return ResponseEntity.created(new URI("/programs/" + createdProgram.getProgram_id())).body(createdProgram);
	}
	
	
	/** Update Program **/
	@PutMapping("/programs/{id}")
	public ResponseEntity<Program> updateProgram(@RequestBody Program newProgram, @PathVariable int id) throws Exception {
		Program updatedProgram = programService.updateProgram(newProgram, id);
		return new ResponseEntity<>(updatedProgram, HttpStatus.CREATED);
	}
	
	/** Delete Program**/
	@DeleteMapping("/programs/{id}")
	public void deleteProgram(@PathVariable int id) throws Exception{
		programService.deleteProgram(id);
		
	}
	
}
