package com.eam.demo.controller.restController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<User>> mostrarList() {
        System.out.println("mostrarList rest");
        List<User> users = userRepository.findAll();
        System.out.println("Users " + users);
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("api/user/get/{id}")
    public ResponseEntity<Optional<User>> obtenerUser(@PathVariable Long id) {
        System.out.println("obtenerUser rest");
        Optional<User> user = userRepository.findById(id);
        System.out.println("user " + user);
        return new ResponseEntity<Optional<User>>(user, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("api/user/create")
    public ResponseEntity<String> crearUser(@RequestBody User user) {
        System.out.println("crearUser rest");
        userRepository.save(user);

        return new ResponseEntity<>("UserCreated", HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping("api/user/delete/{id}")
    public ResponseEntity<String> eliminarUser(@PathVariable Long id) {
        System.out.println("eliminarUser rest");
        Optional<User> userOptional = userRepository.findById(id);
        String answer = "";

        if (userOptional.isPresent()) {
            userRepository.deleteById(id);
            answer = "User Eliminado";
        } else {
            answer = "User No Encontrado";

        }

        return new ResponseEntity<String>(answer, HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping("api/user/update/{id}")
    public ResponseEntity<String> actualizarUser(@PathVariable Long id, @RequestBody User userActualizado) {
        System.out.println("actualizarUser rest");
        Optional<User> userOptional = userRepository.findById(id);
        String answer = "";

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

            answer = "Success";
        } else {
            answer = "Error";
        }

        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

}
