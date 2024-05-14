package com.eam.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.eam.demo.models.Hotel;
import com.eam.demo.models.Image;
import com.eam.demo.repository.IHotelRepository;
import com.eam.demo.repository.IImageRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private IImageRepository imageRepository;

    @Autowired
    private HotelService hotelService;

    public List<Image> findAllImages() {
        return imageRepository.findAll();
    }

    public Optional<Image> findImageById(Long id) {
        return imageRepository.findById(id);
    }

    public List<Image> findAllImageDataByHotelId(Long id) {
        List<Image> images = imageRepository.findByHotelHotelId(id);

        return images;

    }

    public String findImageDataById(Long id) {
        Optional<Image> imageOptional = imageRepository.findById(id);
        if (imageOptional.isPresent()) {
            Image image = imageOptional.get();
            return image.getImageLocation();
        } else {
            return null;
        }
    }

    public Image uploadImage(String imageData, String imageName, Long hotelId) {
        Hotel hotel = hotelService.findHotelByID(hotelId).get();
        Image image = new Image();
        image.setImageLocation(imageData);
        image.setImageName(imageName);
        image.setHotel(hotel);
        return imageRepository.save(image);
    }

    public Image createImage(Image image) {
        return imageRepository.save(image);
    }

    public Image updateImage(Long id, Image imageDetails) {
        return imageRepository.findById(id)
                .map(image -> {
                    image.setImageLocation(imageDetails.getImageLocation());
                    image.setImageName(imageDetails.getImageName());
                    return imageRepository.save(image);
                })
                .orElseThrow(() -> new RuntimeException("Image not found with id " + id));
    }

    public boolean deleteImage(Long id) {
        return imageRepository.findById(id)
                .map(image -> {
                    imageRepository.delete(image);
                    return true;
                })
                .orElse(false);
    }
}
