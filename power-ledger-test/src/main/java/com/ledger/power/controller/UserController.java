package com.ledger.power.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ledger.power.bean.PostCodeRangeResponse;
import com.ledger.power.model.User;
import com.ledger.power.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;


	@GetMapping("/user/{postcode}")
	public List<String> getUserNamesByPostcode(@PathVariable("postcode") int postcode) {
		return userService.getAllNameByPostCode(postcode);
	}

	@GetMapping("/user/postcode")
	public @ResponseBody PostCodeRangeResponse getUserNamesWithInPostCodeRange(@RequestParam("startRange") int startRange, @RequestParam("endRange") int endRange) {
		
		List<String> names = userService.getNamesWithinPostCodeRange(startRange, endRange);
		Long totalWordCount = names.stream().collect(Collectors.summingLong(name->name.length()));
		
		PostCodeRangeResponse postCodeRangeResponse= new PostCodeRangeResponse(names, totalWordCount);
		return postCodeRangeResponse;
	}

	@PostMapping("/user")
	public List<Long> saveUser(@RequestBody List<User> users) {
		return userService.saveUsers(users);
	}

}
