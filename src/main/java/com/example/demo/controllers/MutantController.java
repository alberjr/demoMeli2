package com.example.demo.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.models.input.DnaRequest;
import com.example.demo.models.output.StatMutant;
import com.example.demo.services.MutantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

/**
 * @author alberb
 *
 */
@RestController
@CrossOrigin
@RequestMapping(value = "${spring.data.rest.context}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MutantController {

	@Autowired
	private MutantService mutantService;
	

	/**
	 * 
	 * @return
	 */
	@PostMapping(value = "/mutant", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Object> isMutant(
			@RequestBody(required = true) DnaRequest dnaRequest) {
		try {
			if(mutantService.isMutant(dnaRequest.getDna())) {
				return new ResponseEntity<>(HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);				
			}
		} catch (Exception e) {			
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	/**
	 * 
	 * @return
	 */
	@GetMapping(value = "/stats")
	public @ResponseBody StatMutant stats() {
			StatMutant stats= mutantService.stats();
			return stats;
	}

}
