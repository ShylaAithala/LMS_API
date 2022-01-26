package com.ninja.lms.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ninja.lms.entity.Program;
import com.ninja.lms.exception.DataNotFoundException;
import com.ninja.lms.exception.FieldValidationException;
import com.ninja.lms.repository.ProgramRepository;

@Service
public class ProgramService {
	
	@Resource
	ProgramRepository programRepo;
	
	
	public List<Program>getAllPrograms(){
		return programRepo.findAll();
	}
	

	public Program createPrograms(Program newProgram) {
		
		Date utilDate = new Date();
		newProgram.setCreation_time(new Timestamp(utilDate.getTime()));
		newProgram.setLast_mod_time(new Timestamp(utilDate.getTime()));
		
		programRepo.save(newProgram);
		return newProgram;
		
	}
	
	/**
	 * @param updatedProgram
	 * @param programId
	 * @return
	 * @throws Exception
	 */
	public Program updateProgram(Program updatedProgram, int programId) throws Exception {
		
		//check if id exists or not
		//pass program name - not null
		
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
		
		 System.out.println("updatedProgram.getProgram_name()" + updatedProgram.getProgram_name());
		 System.out.println("existingProgram.getProgram_name()" + existingProgram.getProgram_name());
		  if((updatedProgram.getProgram_name() != null) && (updatedProgram.getProgram_name() != existingProgram.getProgram_name())) {
				  //.equals(existingProgram.getProgram_name())
			  	//check if program Name already exists
			  System.out.println("checking program name for duplicates");
			  boolean isProgramNameExists = checkDuplicateProgramName(programList, updatedProgram.getProgram_name());
			  if(isProgramNameExists) {
				  throw new FieldValidationException("Failed to update existing Program details as Program Name already exists !!");
			  }
		  }
		  else {
			  updatedProgram.setProgram_name(existingProgram.getProgram_name()); 
		  }
		updatedProgram.setProgram_id(existingProgram.getProgram_id());
		
		//updatedProgram.setProgram_name(existingProgram.getProgram_name());
		
		Date utilDate = new Date();
		updatedProgram.setCreation_time(new Timestamp(utilDate.getTime()));
		updatedProgram.setLast_mod_time(new Timestamp(utilDate.getTime()));
		
		programRepo.save(updatedProgram);
		return updatedProgram;
		
	}

	
	public void deleteProgram(int programId) throws Exception{
		boolean isProgramIdExists = programRepo.existsById(programId);
		if(!isProgramIdExists) {
			throw new DataNotFoundException("ProgramId" + programId + "Not Found !!");
		}
		else {
			programRepo.deleteById(programId);
		}

	}
	
	
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
	
	private boolean checkDuplicateProgramName(List<Program> programList, String programName){
		boolean isPresent = false;
		System.out.println("inside checkDuplicateProgramName --- programName" + programName);
		
		for(Program program : programList) {
			System.out.println("inside for : program.getProgram_id()" + program.getProgram_id() + "Program Name" + program.getProgram_name());
			if(program.getProgram_name().equals(programName)) {
				System.out.println("passed programName" + programName);
				System.out.println("program.getProgram_name() " + program.getProgram_name() );
				isPresent = true;
				break;
			}
			
		}
		System.out.println("isPresent duplicate program Name" + isPresent);
		return isPresent;
		
	}
	
}
