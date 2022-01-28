package com.ninja.lms.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ninja.lms.entity.Program;
import com.ninja.lms.exception.DataNotFoundException;
import com.ninja.lms.exception.FieldValidationException;
import com.ninja.lms.repository.ProgramRepository;

/**
 * Service class for Program
 * With Get, Post, Update, Delete methods and validations
 * @author vidsdn
 *
 */

@Service
public class ProgramService {
	
	@Resource
	ProgramRepository programRepo;
	
	/**Get all programs**/
	public List<Program> getAllPrograms(){
		return programRepo.findAll();
	}
	
	/**
	 * Get Program details for a single Program using ProgramId
	 * @param programId
	 */
	public Optional<Program> getProgram(int programId) {
		return programRepo.findById(programId);
	}

	/**
	 * Create new Program 
	 * @param accepts required program fields. 
	 */
	public Program createPrograms(Program newProgram) {
		
		//Check if Program Name is null or blank
		if (newProgram.getProgram_name() ==null || newProgram.getProgram_name().isBlank()) {
			throw new FieldValidationException("Cannot create Program as Program Name cannot be null or blank");
		}
		
		//Check if Program Name already exists
		List<Program> programList = programRepo.findAll();
		if(programList.size() > 0) 
		{
			boolean isProgramNameExists = checkDuplicateProgramName(programList, newProgram.getProgram_name());
			if(isProgramNameExists) 
			{
				throw new FieldValidationException("Cannot create Program as Program Name already exists");
			}
		}
		
		Date utilDate = new Date();
		newProgram.setCreation_time(new Timestamp(utilDate.getTime()));
		newProgram.setLast_mod_time(new Timestamp(utilDate.getTime()));
		
		programRepo.save(newProgram);
		return newProgram;
		
	}
	
	/**
	 * Updates a Program with given details.
	 * 
	 * @param updatedProgram - from request body
	 * @param programId
	 * @return
	 * @throws Exception
	 */
	public Program updateProgram(Program updatedProgram, int programId) throws Exception {
		
		/**Check if there is programs available in the db.**/
		List<Program> programList = programRepo.findAll();
		if(programList.size() == 0) {
			throw new DataNotFoundException("No program data available !!");
    	}
		
		/** Checking for existing Program ID **/
		boolean isProgramIdExists = checkForExistingProgramId(programList, programId);
		if(!isProgramIdExists) {
			throw new DataNotFoundException("Program ID-> " + programId + " Not Found !!");
		}

		Program existingProgram = new Program();
		for(Program itr : programList) {
			if(itr.getProgram_id() == programId) {
				existingProgram = itr;
			}
		}
		if((updatedProgram.getProgram_name() != null) && (updatedProgram.getProgram_name() != existingProgram.getProgram_name()))
		{
			  /**check if program Name already exists */
			  boolean isProgramNameExists = checkDuplicateProgramName(programList, updatedProgram.getProgram_name());
			  if(isProgramNameExists) 
			  {
				  throw new FieldValidationException("Failed to update existing Program details as Program Name already exists !!");
			  }
		  }
		  else 
		  {
			  updatedProgram.setProgram_name(existingProgram.getProgram_name()); 
		  }
		
		updatedProgram.setProgram_id(existingProgram.getProgram_id());
		
		Date utilDate = new Date();
		updatedProgram.setCreation_time(new Timestamp(utilDate.getTime()));
		updatedProgram.setLast_mod_time(new Timestamp(utilDate.getTime()));

		programRepo.save(updatedProgram);
		return updatedProgram;
		
	}


	/**
	 * Delete Program for a given ProgramId
	 * @param programId - from Request URL
	 * @throws Exception
	 */
	public void deleteProgram(int programId) throws Exception{
		boolean isProgramIdExists = programRepo.existsById(programId);
		if(!isProgramIdExists) {
			throw new DataNotFoundException("ProgramId" + programId + "Not Found !!");
		}
		else {
			programRepo.deleteById(programId);
		}

	}
	
	/**
	 * Check if ProgramId exists or not
	 * @param programList
	 * @param programId
	 * @return
	 */
	private boolean checkForExistingProgramId(List<Program> programList, int programId) {
    	boolean isExists = false;
    	
    	for(Program program : programList) {
    		if(program.getProgram_id() == programId) {
    			isExists = true;
    			break;
    		}
    	}
    	return isExists;
    }
	
	
	/**
	 * Checks if Duplicate Program Name exists in DB (program Name is Unique)
	 * @param programList
	 * @param programName
	 * @return
	 */
	private boolean checkDuplicateProgramName(List<Program> programList, String programName){
		boolean isPresent = false;
		for(Program program : programList) {
			if(program.getProgram_name().equals(programName)) {
				isPresent = true;
				break;
			}
		}
		return isPresent;
		
	}



}
