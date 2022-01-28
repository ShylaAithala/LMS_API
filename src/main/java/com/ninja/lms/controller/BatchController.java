package com.ninja.lms.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ninja.lms.entity.Batch;

import javax.validation.Valid;
import com.ninja.lms.service.BatchService;


@RestController
@RequestMapping("/")
public class BatchController {

	@Autowired
	BatchService batchService;
	

	@GetMapping("/batches")
	public ResponseEntity<List<Batch>> getAllBatches() {
		
		List<Batch> batchList = batchService.getAllBatches();
		return new ResponseEntity<>(batchList, HttpStatus.OK);
	
		}
	
	 @GetMapping("/batches/{id}")
	 ResponseEntity<Batch> getBatchById(@PathVariable int id){
	
		Batch batch = batchService.getBatchUserWithId(id);
		return new ResponseEntity<>(batch, HttpStatus.OK);
		
		}
	 
	 @PostMapping("/batches")
	 ResponseEntity<Batch>  createBatch( @RequestBody Batch newBatch) throws URISyntaxException{
		Batch batch = batchService.insertBatch(newBatch);
		return ResponseEntity.created(new URI("/batches/" + batch.getBatch_id())).body(batch);
		}
	@PutMapping("/batches/{id}")
	public ResponseEntity<Batch> updateUser( @RequestBody Batch updateBatch, @PathVariable("id") String batchId) throws Exception {
			
			Batch batch = batchService.updateBatch(updateBatch, batchId);
			return new ResponseEntity<>(batch, HttpStatus.CREATED);
		}

}
