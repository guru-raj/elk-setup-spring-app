package com.elk.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elk.model.MessageTemplate;

@RestController
@RequestMapping(value = "/elk")
public class ELKController {
	
	@GetMapping(value="/message/{messageVal}")
	public ResponseEntity<String> getMessageIdValue(@PathVariable ("messageVal") String messageVal) {
		try {
			MessageTemplate msg = new MessageTemplate(messageVal, "1");
			return new ResponseEntity<>("Message Id: 1" , HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
