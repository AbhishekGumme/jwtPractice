package com.jwtpractice.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jwtpractice.Models.User;

@Service
public class UserService {

	private List<User> store=new ArrayList<>();
	
	public UserService()
	{
		store.add(new User(UUID.randomUUID().toString(), "abhishek", "abhi@gmail.com"));
		store.add(new User(UUID.randomUUID().toString(), "rushi", "rushi@gmail.com"));
		store.add(new User(UUID.randomUUID().toString(), "prasad", "prasad@gmail.com"));
		store.add(new User(UUID.randomUUID().toString(), "aniket", "aniket@gmail.com"));
		store.add(new User(UUID.randomUUID().toString(), "rutu", "rutu@gmail.com"));
	}
	
	public List<User> getUsers()
	{
		return this.store;
	}
}
