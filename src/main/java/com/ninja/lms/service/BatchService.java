package com.ninja.lms.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ninja.lms.entity.Batch;
import com.ninja.lms.exception.DataNotFoundException;
import com.ninja.lms.exception.FieldValidationException;
import com.ninja.lms.repository.BatchRepository;


@Service
public class BatchService {
	

	@Resource
	BatchRepository batchRepo;
	

	public List<Batch> getAllBatches() {
		return batchRepo.findAll();
	}


	public Batch getBatchUserWithId(int id) {
		 return batchRepo.findById(id).orElseThrow();
	}


	public Batch insertBatch(Batch newBatch) {
		Date utilDate = new Date();
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

	public Batch updateBatch(Batch updateBatch, String batchId) {
		return null;
	}
	
	private List<Batch>  checkProgramBatchExists(Batch batch) {
		String batchName=batch.getBatch_name();
		int batchPId=batch.getBatch_program_id();
		
	return batchRepo.findByBatchNameAndBatchPId(batchName,batchPId);
	}
	}

