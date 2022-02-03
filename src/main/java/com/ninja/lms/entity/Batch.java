package com.ninja.lms.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import io.swagger.annotations.ApiModelProperty;



@Entity
@Table(name="TBL_LMS_BATCH")
public class Batch {
	
    /**
	 * 
	 */
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(hidden=true)
	int batch_id;
	@Column(name="batch_name")
	String batchName;
	@Column
	String batch_description;
	@Column
	String batch_status;
	@Column(name="batch_program_id")
	int batchPId;
	@Column
	int batch_no_of_classes;
	
	@ApiModelProperty(hidden=true)
	@Column
	Timestamp creation_time;
	
	@ApiModelProperty(hidden=true)
	@Column
	Timestamp last_mod_time;
	
	
	public int getBatch_id() {
		return batch_id;
	}
	public void setBatch_id(int batch_id) {
		this.batch_id = batch_id;
	}
	public String getBatch_name() {
		return batchName;
	}
	public void setBatch_name(String batch_name) {
		this.batchName = batch_name;
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
	public int getBatch_program_id() {
		return batchPId;
	}
	public void setBatch_program_id(int batch_program_id) {
		this.batchPId = batch_program_id;
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
