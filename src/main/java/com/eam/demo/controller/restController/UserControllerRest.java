package com.eam.demo.controller.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eam.demo.models.User;

import com.eam.demo.repository.IUserRepository;

@RestController
public class UserControllerRest {
    	@Autowired
	private IUserRepository userRepository;

	@CrossOrigin
    @GetMapping("api/user/list")
	public List<User> mostrarList() {
		System.out.println("mostrarList rest" );
		List<User> users = userRepository.findAll();
		System.out.println("Users " + users );		
		return users;
	}
}
