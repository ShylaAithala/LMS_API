package com.ninja.lms.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ninja.lms.entity.Batch;
import com.ninja.lms.exception.AlreadyExistsValidationException;
import com.ninja.lms.exception.DataNotFoundException;
import com.ninja.lms.repository.BatchRepository;
import com.ninja.lms.repository.ProgramRepository;


@Service
public class BatchService {
	

	@Resource
	BatchRepository batchRepo;
	
	@Autowired
	ProgramRepository programRepo;
	Date utilDate = new Date();
		

	public List<Batch> getAllBatches() {
		return batchRepo.findAll();
	}


	public Batch getBatchUserWithId(int id) throws Exception {
		 return batchRepo.findById(id).orElseThrow(()->new DataNotFoundException("Batch id "+id+" not found "));

	}


	public Batch insertBatch(Batch newBatch) throws Exception {
		newBatch.setCreation_time(new Timestamp(utilDate.getTime()));
		newBatch.setLast_mod_time(new Timestamp(utilDate.getTime()));
		
		if ((batchRepo.findBybatchPId(newBatch.getBatch_program_id()).isEmpty())) {
			throw new DataNotFoundException("Program id "+newBatch.getBatch_program_id() +" not found ");
		}
		else {
		List<Batch> l= checkProgramBatchExists(newBatch);
		if(l.size()==0)
		{
			return batchRepo.save(newBatch);
		}
		else
		{	throw new AlreadyExistsValidationException("Batch Name:"+newBatch.getBatch_name()+" Program ID:"+newBatch.getBatch_program_id()+" Already Exists !!");
	
		}
		}
	}

	public Batch updateBatch(Batch updateBatch, int batchId) throws DataNotFoundException {

		if ((batchRepo.findBybatchPId(updateBatch.getBatch_program_id()).isEmpty())) {
			throw new DataNotFoundException("Program id "+updateBatch.getBatch_program_id() +" not found ");
		}
		else {
			
		try {
		Batch batchDataFromDb= batchRepo.findById(batchId).orElseThrow(()->new DataNotFoundException("Batch id "+batchId+" not found "));
		updateBatch.setCreation_time(batchDataFromDb.getCreation_time());
		updateBatch.setBatch_id(batchId);
		updateBatch.setLast_mod_time(new Timestamp(utilDate.getTime()));
		return batchRepo.save(updateBatch);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException(e.getMessage());
			}
		}
	}
	private List<Batch>  checkProgramBatchExists(Batch batch) {
		String batchName=batch.getBatch_name();
		int batchPId=batch.getBatch_program_id();
		
	return batchRepo.findByBatchNameAndBatchPId(batchName,batchPId);
	}


	public void deleteUserById(int id) throws Exception {
		boolean exists = batchRepo.existsById(id);
		if(!exists)
			throw new DataNotFoundException("Batch id- " + id + " Not Found !!");
		else
			batchRepo.deleteById(id);
		
	}


	public List<Batch> getBatchByProgramId(int programId) throws Exception {
		  if (!programRepo.existsById(programId)) {
		      throw new DataNotFoundException("Not found Program  with id = " + programId);
		    }
		    List<Batch> batches = batchRepo.findBybatchPId(programId);
		    return batches;
	}
	}

