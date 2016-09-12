package com.sonata.raj.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sonata.raj.service.UserService;

@Controller
@RequestMapping(value = "/sampleService")
public class SamepleController {

	private UserService service;

	@Autowired
	public SamepleController(UserService service) {
		this.service = service;
	}
	@RequestMapping(value = "/add", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
	@ResponseBody
	public ResponseEntity<Integer>  add(@RequestParam int a,@RequestParam int b) {
		int add = service.add(a,b);
		ResponseEntity<Integer> result = new ResponseEntity<Integer>(
					add, HttpStatus.OK);
		return result;
	}
}
