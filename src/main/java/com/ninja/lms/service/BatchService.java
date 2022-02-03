package com.ninja.lms.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ninja.lms.entity.Batch;
import com.ninja.lms.exception.DataNotFoundException;
import com.ninja.lms.exception.FieldValidationException;
import com.ninja.lms.repository.BatchRepository;


@Service
public class BatchService {
	

	@Resource
	BatchRepository batchRepo;
	Date utilDate = new Date();
		

	public List<Batch> getAllBatches() {
		return batchRepo.findAll();
	}


	public Batch getBatchUserWithId(int id) {
		 return batchRepo.findById(id).orElseThrow();
	}


	public Batch insertBatch(Batch newBatch) {
		newBatch.setCreation_time(new Timestamp(utilDate.getTime()));
		newBatch.setLast_mod_time(new Timestamp(utilDate.getTime()));
		List<Batch> l= checkProgramBatchExists(newBatch);
		if(l.size()==0)
		{
			return batchRepo.save(newBatch);
		}
		else
		{	throw new FieldValidationException("Batch Name:"+newBatch.getBatch_name()+" Program ID:"+newBatch.getBatch_program_id()+" Already Exists !!");
	
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
	}

