package com.ninja.lms.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="TBL_LMS_BATCH")
public class Batch {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(hidden=true)
	int batch_id;
	@Column(name="batch_name")
	@NotBlank (message = "Batch Name is mandatory")
	String batchName;
	@Column
	String batch_description;
	@Column
	String batch_status;
	@Column(name="batch_program_id")
	@NotNull(message = "Program Id is required")
	int batchPId;
	@Column
	int batch_no_of_classes;
	
	@ApiModelProperty(hidden=true)
	@JsonIgnore
	@Column
	Timestamp creation_time;
	
	@ApiModelProperty(hidden=true)
	@JsonIgnore
	@Column
	Timestamp last_mod_time;
	

	@ManyToOne(cascade=  {CascadeType.DETACH, CascadeType.PERSIST})
	@JoinColumn(name = "batch_program_id",insertable = false, updatable = false)
	@JsonBackReference 
	private Program program;
	
/*
	 @ManyToOne(fetch = FetchType.LAZY, optional = false)
	 @JoinColumn(name = "batch_program_id",insertable = false,updatable =false, nullable = false)
	 @JsonIgnore
	 private Program program;
	 */
	

    public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	public Batch(int batch_id, String batchName,
			String batch_description, String batch_status,int batchPId,
			int batch_no_of_classes, Timestamp creation_time, Timestamp last_mod_time) {
		super();
		this.batch_id = batch_id;
		this.batchName = batchName;
		this.batch_description = batch_description;
		this.batch_status = batch_status;
		this.batchPId = batchPId;
		this.batch_no_of_classes = batch_no_of_classes;
		this.creation_time = creation_time;
		this.last_mod_time = last_mod_time;
		}

	public int getBatch_id() {
		return batch_id;
	}

	public void setBatch_id(int batch_id) {
		this.batch_id = batch_id;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public String getBatch_description() {
		return batch_description;
	}

	public void setBatch_description(String batch_description) {
		this.batch_description = batch_description;
	}

	public String getBatch_status() {
		return batch_status;
	}

	public void setBatch_status(String batch_status) {
		this.batch_status = batch_status;
	}

	public int getBatchPId() {
		return batchPId;
	}

	public void setBatchPId(int batchPId) {
		this.batchPId = batchPId;
	}

	public int getBatch_no_of_classes() {
		return batch_no_of_classes;
	}

	public void setBatch_no_of_classes(int batch_no_of_classes) {
		this.batch_no_of_classes = batch_no_of_classes;
	}

	public Timestamp getCreation_time() {
		return creation_time;
	}

	public void setCreation_time(Timestamp creation_time) {
		this.creation_time = creation_time;
	}

	public Timestamp getLast_mod_time() {
		return last_mod_time;
	}

	public void setLast_mod_time(Timestamp last_mod_time) {
		this.last_mod_time = last_mod_time;
	}
	
	

}
