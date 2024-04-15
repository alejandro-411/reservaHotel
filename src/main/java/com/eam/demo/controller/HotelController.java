package com.eam.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eam.demo.models.Amenities;
import com.eam.demo.models.Booking;
import com.eam.demo.models.ContactDetails;
import com.eam.demo.models.Hotel;
import com.eam.demo.models.HotelAmenities;
import com.eam.demo.models.Image;
import com.eam.demo.models.Location;
import com.eam.demo.models.Review;
import com.eam.demo.models.Rol;
import com.eam.demo.models.User;
import com.eam.demo.repository.IAmenitiesRepository;
import com.eam.demo.repository.IBookingRepository;
import com.eam.demo.repository.IContactDetailsRepository;
import com.eam.demo.repository.IHotelAmenitiesRepository;
import com.eam.demo.repository.IHotelRepository;
import com.eam.demo.repository.IImageRepository;
import com.eam.demo.repository.IReviewRepository;
import com.eam.demo.repository.IRolRepository;
import com.eam.demo.repository.IUserRepository;
import com.eam.demo.repository.IlocationRepository;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/hotel")
public class HotelController {

	@Autowired
	private IlocationRepository locationRepository;



	@Autowired
	private IImageRepository imageRepository;
	
	@Autowired
	private IAmenitiesRepository amenitiesRepository;

    @Autowired
	private IHotelRepository hotelRepository;

	

	@GetMapping("")
	public String mostrarFormulario() {
		return "hotelform";
	}

	@GetMapping("/hotelform")
	public String mostrarFormularioHotel(Model model) {
		   // Crea un nuevo objeto Hotel y agrégalo al modelo
        Hotel newHotel = new Hotel();
        newHotel.setLocation(new Location());  // Inicializa el objeto Location
        newHotel.setImages(new ArrayList<Image>());  // Inicializa la lista de imágenes

        List<Amenities> amenitiesAvaliable = this.amenitiesRepository.findAll();
        
        System.out.println(amenitiesAvaliable.get(0).getAmenitiesName());
        System.out.println(amenitiesAvaliable.size());
        

        model.addAttribute("amenitiesAvaliable", amenitiesAvaliable);

        model.addAttribute("hotel", newHotel);

        return "hotel/hotelform";  // Devuelve el nombre de la vista del formulario de hotel
	}

	@GetMapping("/list")
	public String mostrarList(Hotel hotel, Model model) {
		System.out.println("mostrarList " );
		List<Hotel> hotels = hotelRepository.findAll();
		System.out.println("Hotel " + hotels );
		model.addAttribute("hotel",hotels);
		return "hotellist";
	}
/*
	@PostMapping("")
	public String guardarEmpleado(User user) {
		System.out.println("HOLAAAAAAA");
		userRepository.save(user);
		return "redirect:/user";
	}*/


	@PostMapping("/save")
	public String saveHotel(Hotel hotel, RedirectAttributes ra){
		hotelRepository.save(hotel);
		ra.addFlashAttribute("message", "The hotel has ben created succesfully.");
		return "redirect:/hotel";
	}




}
