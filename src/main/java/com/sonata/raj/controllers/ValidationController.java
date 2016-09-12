package com.sonata.raj.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sonata.raj.command.UserDetails;
import com.sonata.raj.service.UserService;

@Controller
public class ValidationController {

	@Autowired
	private UserService userSerivce;

	@RequestMapping(value = "displayForm", method = RequestMethod.GET)
	public String helloWorld(ModelMap model) {
		UserDetails userDetails = new UserDetails();
		userDetails.setEmail("SomeTest");
		model.addAttribute("userDetails", userDetails);
		return "loginPage";
	}
	
	@RequestMapping(value = "displayForm", method = RequestMethod.GET)
	public String helloWorld1(ModelMap model) {
		UserDetails userDetails = new UserDetails();
		userDetails.setEmail("SomeTest");
		model.addAttribute("userDetails", userDetails);
		return "loginPage";
	}

	@RequestMapping("/login")
	public String loginCheck(UserDetails userDetails, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "loginPage";
		} else {
			model.addAttribute("lfobj", userDetails);
			return "success";
		}
	}

	@RequestMapping(value = "/userexists", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE
			+ ";charset=utf-8")
	@ResponseBody
	public ResponseEntity<Boolean> isUserExists(@RequestParam String email) {
		return new ResponseEntity<Boolean>(userSerivce.isUserExist(email), HttpStatus.OK);

	}

}
