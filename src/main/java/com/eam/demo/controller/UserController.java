package com.eam.demo.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eam.demo.models.ContactDetails;
import com.eam.demo.models.Rol;
import com.eam.demo.models.User;
import com.eam.demo.repository.IContactDetailsRepository;
import com.eam.demo.repository.IRolRepository;
import com.eam.demo.repository.IUserRepository;




@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IRolRepository rolRepository;

	@Autowired
	private IContactDetailsRepository contactDetailsRepository;

	@GetMapping("")
	public String mostrarFormulario() {
		return "userHome";
	}

	@GetMapping("/userform")
	public String mostrarFormularioUsuario(Model model) {
		// Crea un nuevo objeto User y agrégalo al modelo
		User newUser = new User();
		newUser.setRol(new Rol());  // Inicializa el objeto Rol
		newUser.setContactDetails(new ContactDetails());

		model.addAttribute("user", newUser);

		// Agrega roles al modelo (asume que rolesRepository.findAll() devuelve una lista de roles)
		List<Rol> roles = rolRepository.findAll();
		System.out.println("roles " + roles.get(0).getRolName());
		System.out.println("roles " + roles.get(1).getRolName());

		model.addAttribute("roles", roles);

		// Agrega detalles de contacto al modelo (asume que contactDetailsRepository.findAll() devuelve una lista de detalles de contacto)
		List<ContactDetails> contactDetails = contactDetailsRepository.findAll();
	//	System.out.println("contactDetails "+ contactDetails.get(0).getContactName());
		//model.addAttribute("contactDetails", contactDetails);

		// Retorna el nombre de la plantilla (userform.html)
		return "userform";
	}

	@GetMapping("/list")
	public String mostrarList(User user, Model model) {
		System.out.println("mostrarList " );
		List<User> users = userRepository.findAll();
		System.out.println("Users " + users );
		model.addAttribute("user",users);
		return "userlist";
	}

	@PostMapping("")
	public String guardarEmpleado(User user) {
		System.out.println("HOLAAAAAAA");
		userRepository.save(user);
		return "redirect:/user";
	}

	@PostMapping("/login")
	public String procesarInicioSesion(Model model, String userEmail, String password) {

		System.out.println("procesarInicioSesion");

		User findUser = userRepository.findByUserEmail(userEmail);
		if(findUser == null) {
			model.addAttribute("error", true);
			return "userHome";
		}

		if (!findUser.getUserPassword().equals(password)) {
			model.addAttribute("error", true);
			return "userHome";
		}
        model.addAttribute("user", findUser);  // Puedes pasar más información según sea necesario
        return "userdetail";
	}
	@GetMapping("/userdetail")
    public String mostrarDetallesUsuario(Model model) {
        // Lógica para obtener detalles del usuario si es necesario
        return "userdetail";
    }

}
