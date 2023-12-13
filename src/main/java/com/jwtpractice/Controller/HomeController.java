package com.jwtpractice.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwtpractice.Models.User;
import com.jwtpractice.Service.UserService;

@RestController
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private UserService userService;
	
	//http://localhost:8081/home/users
	@GetMapping("/users")
	public List<User> getUser()
	{
		System.out.println("geting users");
		return userService.getUsers();
	}
}
