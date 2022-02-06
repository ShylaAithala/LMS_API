package com.ninja.lms.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

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

import com.ninja.lms.entity.Batch;
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
	 ResponseEntity<Batch> getBatchById(@PathVariable("id") int id) throws Exception{
	
		Batch batch = batchService.getBatchUserWithId(id);
		return new ResponseEntity<>(batch, HttpStatus.OK);
		
		}
	 
	  @GetMapping("/program/{programId}/batches")
	  public ResponseEntity<List<Batch>> getAllBatchesByProgramlId(@PathVariable(value = "programId") int programId) throws Exception {
	   
		  List<Batch> batches=batchService.getBatchByProgramId(programId);
		  return new ResponseEntity<>(batches, HttpStatus.OK);
		  
			  }
	 
	 @PostMapping("/batches")
	 ResponseEntity<Batch>  createBatch(@Valid @RequestBody Batch newBatch) throws Exception{
		Batch batch = batchService.insertBatch(newBatch);
		return ResponseEntity.created(new URI("/batches/" + batch.getBatch_id())).body(batch);
		}
	 
	@PutMapping("/batches/{id}")
	public ResponseEntity<Batch> updateUser(@Valid @RequestBody Batch updateBatch, @PathVariable("id") int batchId) throws Exception {
			
			Batch batch = batchService.updateBatch(updateBatch, batchId);
			return new ResponseEntity<>(batch, HttpStatus.CREATED);
			}


	@DeleteMapping("/batches/{id}")
	public String deleteBatch(@PathVariable("id") int id) throws Exception {
		batchService.deleteUserById(id);

		return "Batch "+ id +" Deleted successfully ";
	}
}
