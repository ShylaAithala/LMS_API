package com.ninja.lms.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TBL_LMS_PROGRAM")
public class Program {
	
	@Id
	@Column
	int program_id;
	@Column
	String program_name;
	@Column
	String program_description;
	@Column
	String program_status;
	@Column
	Timestamp creation_time;
	@Column
	Timestamp last_mod_time;
	
		
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
	
	
	

}
