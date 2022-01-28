package com.ninja.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ninja.lms.entity.Batch;
@Repository
public interface BatchRepository extends JpaRepository<Batch, Integer> {

	List<Batch> findByBatchNameAndBatchPId(String batchName, int batchPId);

	
}
