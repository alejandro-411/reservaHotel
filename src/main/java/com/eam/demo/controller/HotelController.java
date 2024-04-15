package com.eam.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import com.eam.demo.models.Amenities;
import com.eam.demo.models.Booking;
import com.eam.demo.models.City;
import com.eam.demo.models.ContactDetails;
import com.eam.demo.models.Department;
import com.eam.demo.models.Hotel;
import com.eam.demo.models.HotelAmenities;
import com.eam.demo.models.Image;
import com.eam.demo.models.Location;
import com.eam.demo.models.Review;
import com.eam.demo.models.Rol;
import com.eam.demo.models.User;
import com.eam.demo.repository.IAmenitiesRepository;
import com.eam.demo.repository.IBookingRepository;
import com.eam.demo.repository.ICityRepository;
import com.eam.demo.repository.IContactDetailsRepository;
import com.eam.demo.repository.IDepartmentRepository;
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
	private IDepartmentRepository departmentRepository;

	@Autowired
	private ICityRepository cityRepository;
	
	@Autowired
	private IImageRepository imageRepository;
	
	@Autowired
	private IAmenitiesRepository amenitiesRepository;

    @Autowired
	private IHotelRepository hotelRepository;

    @Autowired
	private IHotelAmenitiesRepository hotelAmenitiesRepository;

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

	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String saveHotel(Hotel hotel, RedirectAttributes ra,
			@RequestParam("amenities") Long[] amenitiesIds){
		System.out.println("ENTROOOO");
		System.out.println("amenities "+ amenitiesIds[0]);

		System.out.println("HOTEL");
		System.out.println("NOMNBRE "+ hotel.getHotelName());
		System.out.println("LOCATION getAddress "+ hotel.getLocation().getAddress());
		System.out.println("LOCATION getNeighborhood "+ hotel.getLocation().getNeighborhood());
		System.out.println("LOCATION getLocationId"+ hotel.getLocation().getLocationId());
		System.out.println("LOCATION City"+ hotel.getLocation().getCity().getCityName());
		System.out.println("LOCATION Department"+ hotel.getLocation().getCity().getDepartment().getDepartmentName());

		Department departmentCreated =departmentRepository.save(new Department(null, hotel.getLocation().getCity().getDepartment().getDepartmentName(), null));
		
		
		City cityCreated = cityRepository.save(new City(0, 
				hotel.getLocation().getCity().getCityName(), departmentCreated, null));
		
		
		Location locationSaved = locationRepository.save(new Location(0, hotel.getLocation().getAddress(),
				hotel.getLocation().getNeighborhood(), cityCreated));
		
		hotel.setLocation(locationSaved);
		
		Hotel hotelSaved = hotelRepository.save(hotel);
		
		Optional<Location> locationByID = locationRepository.findById(locationSaved.getLocationId());
		locationByID.ifPresent((location)-> {location.setHotel(hotelSaved);
		locationRepository.save(location);});
		

		List<Long> idsAmenities = Arrays.asList(amenitiesIds); // Ejemplo de lista de IDs de cursos

		List<Amenities> amenities = idsAmenities.stream()
			    .map(idCurso -> amenitiesRepository.findById(idCurso).orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + idCurso)))
			    .collect(Collectors.toList());
		
		// Crear instancias de EstudianteCurso y guardarlas
		List<HotelAmenities> hotelAmenities = amenities.stream()
		    .map(amenity -> new HotelAmenities(amenity, hotelSaved))
		    .collect(Collectors.toList());
		
		hotelAmenitiesRepository.saveAll(hotelAmenities);	
		
		
		ra.addFlashAttribute("message", "The hotel has ben created succesfully.");

		return "hotel/imagesform";
	}




}
