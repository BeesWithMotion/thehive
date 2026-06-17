package com.beeswithmotion.thehive.image;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public ResponseEntity<Image> getImageById(Long imageId) {
        Image image = imageRepository.findByImageId(imageId);
        return ResponseEntity.ok(image);
    }

    public ResponseEntity<String> saveImage(Image image) {
        imageRepository.save(image);
        return ResponseEntity.ok("Image saved successfully.");
    }
}
