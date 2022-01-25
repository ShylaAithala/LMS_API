package com.ninja.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ninja.lms.entity.Batch;
import com.ninja.lms.repository.BatchRepository;

@RestController
@RequestMapping("/")
public class BatchController {

	@Autowired
	BatchRepository batchRepo;
	

	@GetMapping("/batches")
	public List<Batch> getAllBatches() {
		return batchRepo.findAll();
	}
}
