package com.ninja.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ninja.lms.entity.Batch;
@Repository
public interface BatchRepository extends JpaRepository<Batch, Integer> {

}
