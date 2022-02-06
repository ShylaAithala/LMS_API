package com.ninja.lms.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="TBL_LMS_PROGRAM")
public class Program {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column
	int program_id;
	@Column
	String program_name;
	@Column
	String program_description;
	@Column
	String program_status;
	@Column
	@JsonIgnore
	Timestamp creation_time;
	@Column
	@JsonIgnore
	Timestamp last_mod_time;
	
	@OneToMany(cascade = {CascadeType.DETACH, CascadeType.PERSIST}, mappedBy = "program") 
	@JsonIgnore
	List<Batch> Batch;


	public Program(int program_id, String program_name, String program_description, String program_status,
			Timestamp creation_time, Timestamp last_mod_time, List<com.ninja.lms.entity.Batch> batch) {
		super();
		this.program_id = program_id;
		this.program_name = program_name;
		this.program_description = program_description;
		this.program_status = program_status;
		this.creation_time = creation_time;
		this.last_mod_time = last_mod_time;
		Batch = batch;
	}


	public Program() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getProgram_id() {
		return program_id;
	}
	public void setProgram_id(int program_id) {
		this.program_id = program_id;
	}
	public String getProgram_name() {
		return program_name;
	}
	public void setProgram_name(String program_name) {
		this.program_name = program_name;
	}
	public String getProgram_description() {
		return program_description;
	}
	public void setProgram_description(String program_description) {
		this.program_description = program_description;
	}
	public String getProgram_status() {
		return program_status;
	}
	public void setProgram_status(String program_status) {
		this.program_status = program_status;
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

	public List<Batch> getBatch() {
		return Batch;
	}


	public void setBatch(List<Batch> batch) {
		Batch = batch;
	}

	 
	
	
	

}
