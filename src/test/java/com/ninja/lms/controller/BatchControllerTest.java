package com.ninja.lms.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninja.lms.entity.Batch;
import com.ninja.lms.entity.Program;
import com.ninja.lms.repository.BatchRepository;
import com.ninja.lms.repository.ProgramRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;

@WebMvcTest(value = BatchController.class)

public class BatchControllerTest {
	
		@Autowired
	    MockMvc mockMvc;
	    @Autowired
	    ObjectMapper mapper;
	    
	    @MockBean
	    BatchRepository batchRepository;
	    
	    @MockBean
	    ProgramRepository pgmRepository;
	    
	    Date utilDate = new Date();
		
	    Batch RECORD_1 = new Batch(22, "Java", "Java for beginners", "Active", 1, 5,  new Timestamp(utilDate.getTime()), new Timestamp(utilDate.getTime()));
	    Batch RECORD_2 = new Batch(22, "Selenium", "Selenium IT", "Active", 2, 5,  new Timestamp(utilDate.getTime()), new Timestamp(utilDate.getTime()));
	    Batch RECORD_3 = new Batch(23, "Python", "Programming", "In Person" ,8, 15,  new Timestamp(utilDate.getTime()), new Timestamp(utilDate.getTime()));
	    
	    
	    @Test
	    @WithMockUser(username="username", password="password")
	    public void getAllRecords_success() throws Exception {
	        List<Batch> batches = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));
	        
	        Mockito.when(batchRepository.findAll()).thenReturn(batches);
	        
	        mockMvc.perform(MockMvcRequestBuilders
	                .get("/batches")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$", hasSize(3)))
	                .andExpect(jsonPath("$[2].batchName", is("Python")));
	    }

	    @Test
	    @WithMockUser(username="username", password="password")
	    public void getBatchById_success() throws Exception {
	        Mockito.when(batchRepository.findById(RECORD_1.getBatch_id())).thenReturn(java.util.Optional.of(RECORD_1));

	        mockMvc.perform(MockMvcRequestBuilders
	                .get("/batches/22")
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$", notNullValue()))
	                .andExpect(jsonPath("$.batchName", is("Java")));
	    }
	    
	  /*  @Test
	    @WithMockUser(username="username", password="password")
	    public void createBatch_success() throws Exception {
	    	 List<Batch> batches = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));
		       
	    	Program p= new Program(8, "Python", "New Batch", "Remote", new Timestamp(utilDate.getTime()), new Timestamp(utilDate.getTime()),batches);
	    	RECORD_3.setProgram(p );
	    	Mockito.when(pgmRepository.save(p)).thenReturn(p);
	        Mockito.when(batchRepository.save(RECORD_3)).thenReturn(RECORD_3);

	        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/batches")
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON)
	                .content(this.mapper.writeValueAsString(RECORD_3));

	        mockMvc.perform(mockRequest)
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$", notNullValue()))
	                .andExpect(jsonPath("$.batchName", is("Python")));
	        }*/
}