package com.beeswithmotion.thehive.image;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.net.MalformedURLException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/images")
public class ImageController {
    private final ImageService imageService;
    private final Path uploadPath;

    public ImageController(ImageService imageService, @Value("${thehive.upload-dir}") String uploadDir) {
        this.imageService = imageService;
        this.uploadPath = Paths.get(uploadDir);
    }

    @GetMapping("/files/{storedFileName}")
    public ResponseEntity<Resource> getImageFile(@PathVariable String storedFileName) {
        Image image = imageService.getImageByStoredFileName(storedFileName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Image not found!"));

        try {
            Path imagePath = uploadPath.resolve(image.getStoredFileName()).normalize();
            Resource resource = new UrlResource(imagePath.toUri());

            if(!resource.exists() || !resource.isReadable()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image file not found!");
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(image.getContentType()))
                    .cacheControl(CacheControl.maxAge(30, TimeUnit.DAYS))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + image.getOriginalFileName() + "\"")
                    .body(resource);
        } catch (MalformedURLException exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to load image file!");
        }
    }
}
