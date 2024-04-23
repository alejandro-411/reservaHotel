package com.eam.demo.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import com.eam.demo.repository.*;
import com.eam.demo.services.HotelService;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.eam.demo.models.Room;
import com.eam.demo.models.RoomType;
import com.eam.demo.models.User;

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

    @Autowired
	private IRoomTypeRepository roomTypeRepository;

	@Autowired
	private IRoomRepository roomRepository;
    
	private HotelService hotelService;


	@GetMapping("")
	public String mostrarFormulario() {
		return "hotelform";
	}

	@GetMapping("/hotelform")
	public String mostrarFormularioHotel(Model model) {
		   // Crea un nuevo objeto Hotel y agrégalo al modelo
        Hotel newHotel = new Hotel();
        newHotel.setLocation(new Location());  // Inicializa el objeto Location

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

	@PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
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

		Long idHotel = hotelService.saveHotel(hotel, amenitiesIds);

			
		 
		ra.addFlashAttribute("message", "The hotel has ben created succesfully.");
		ra.addFlashAttribute("hotelId", idHotel);

		return "redirect:/hotel/imagesform";
	}
	
	@GetMapping("/imagesform")
	public String showImagesForm(Model model, @ModelAttribute("hotelId") Long hotelId,RedirectAttributes ra) {
		System.out.println("Entro");
	    System.out.println("Hotel ID: " + hotelId);
		ra.addFlashAttribute("hotelId2", hotelId);

	    // Lógica para cargar datos en el modelo
	    return "hotel/imagesform";
	}
	
	@PostMapping(value = "/uploadImages", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String uploadImages(@RequestParam("imageFiles") MultipartFile[] imageFiles, RedirectAttributes ra,
			@RequestParam("hotelId2") Long hotelId) throws IOException, SerialException, SQLException {
	    // Lógica para guardar las imágenes recibidas en el repositorio
		System.out.println("Hotel IDDDDDD " + hotelId);
		Optional<Hotel> hotel = hotelRepository.findById(hotelId);
	    for (MultipartFile file : imageFiles) {
	    	System.out.println(file.getOriginalFilename());
	        byte[] image = Base64.encodeBase64(file.getBytes(), false);
	       imageRepository.save(new Image(0, new SerialBlob(image), file.getOriginalFilename(), hotel.get()));
	    }
	    ra.addFlashAttribute("message", "Images uploaded successfully.");
		ra.addFlashAttribute("hotelId3", hotelId);

		return "redirect:/hotel/roomform";  // Redirige a la lista de hoteles u otra página según tu flujo
	}


	@GetMapping("/roomform")
	public String mostrarFormularioRoom(Model model,@ModelAttribute("hotelId3") Long hotelId,RedirectAttributes ra) {
		  

        List<RoomType> roomTypes = this.roomTypeRepository.findAll();
        
        System.out.println(roomTypes.get(0).getRoomTypeName());
        System.out.println(roomTypes.size());
        

        model.addAttribute("roomTypes", roomTypes);

        model.addAttribute("room", new Room());
		System.out.println("HOTEL 444444 " + hotelId);
	    model.addAttribute("hotelId", hotelId); // Agregar hotelId al modelo

ra.addFlashAttribute("hotelId4", hotelId);
        return "hotel/roomform";  // Devuelve el nombre de la vista del formulario de hotel
	}

	@PostMapping(value = "/createroom", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String createRoomHotel( RedirectAttributes ra,Room room,
			@RequestParam("hotelId4") Long hotelId)  {
		// Lógica para guardar las imágenes recibidas en el repositorio
		System.out.println("Hotel 4 ID" + hotelId);
		Optional<Hotel> hotel = hotelRepository.findById(hotelId);
		room.setHotel(hotel.get());
		roomRepository.save(room);

		ra.addFlashAttribute("message", "ROOM uploaded successfully.");
		return "redirect:/hotel/hotels";  // Redirige a la lista de hoteles u otra página según tu flujo
	}
	
    @GetMapping("/hotels")
    public String showHotelList(Model model) {
        List<Hotel> hotels = this.hotelRepository.findAll(); // Obtener la lista de hoteles desde el repositorio
System.out.println(hotels.size());
		System.out.println(hotels.get(0).getLocation().getAddress());
		System.out.println(hotels.get(0).getLocation().getNeighborhood());
		System.out.println(hotels.get(0).getLocation().getCity().getCityName());

        model.addAttribute("hotels", hotels); // Agregar la lista de hoteles al modelo

        return "hotel/hotellist"; // Renderizar el template hotelList.html en Thymeleaf
    }


}
