package com.eam.demo.controller.restController;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@CrossOrigin
    @GetMapping("api/user/get/{id}")
	public Optional<User> obtenerUser(@PathVariable Long id) {
		System.out.println("obtenerUser rest" );
		Optional<User> user = userRepository.findById(id);
		System.out.println("user " + user );		
		return user;
	}

	@CrossOrigin
    @PostMapping("api/user/create")
	public HashMap<String, String> crearUser(@RequestBody User user) {
		System.out.println("crearUser rest" );
		userRepository.save(user);
        HashMap<String, String> answer = new HashMap<>();
        answer.put("User Creado", "Success");

		return answer;
	}

@CrossOrigin
@DeleteMapping("api/user/delete/{id}")
public HashMap<String, String> eliminarUser(@PathVariable Long id) {
    System.out.println("eliminarUser rest" );
    Optional<User> userOptional = userRepository.findById(id);
    HashMap<String, String> answer = new HashMap<>();

    if (userOptional.isPresent()) {
        userRepository.deleteById(id);
        answer.put("Success","User Eliminado");
    } else {
        answer.put( "Error","User No Encontrado");
    }

    return answer;
}

@CrossOrigin
@PutMapping("api/user/update/{id}")
public HashMap<String, String> actualizarUser(@PathVariable Long id, @RequestBody User userActualizado) {
    System.out.println("actualizarUser rest" );
    Optional<User> userOptional = userRepository.findById(id);
    HashMap<String, String> answer = new HashMap<>();

    if (userOptional.isPresent()) {
        User user = userOptional.get();
        // Actualiza los campos del usuario con los nuevos datos proporcionados
        user.setUserName(userActualizado.getUserName());
        user.setUserPassword(userActualizado.getUserPassword());
        user.setUserPhoneNumber(userActualizado.getUserPhoneNumber());
        user.setContactDetails(userActualizado.getContactDetails());
        user.setRol(userActualizado.getRol());

        // Guarda el usuario actualizado en la base de datos
        userRepository.save(user);

        answer.put("User Actualizado", "Success");
    } else {
        answer.put("User No Encontrado", "Error");
    }

    return answer;
}


}
