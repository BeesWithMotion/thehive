package com.beeswithmotion.thehive.image;

import com.beeswithmotion.thehive.image.dto.ImageResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImageService {
    private final ImageRepository imageRepository;
    private final Path uploadPath;

    public ImageService(ImageRepository imageRepository, @Value("${thehive.upload-dir}") String uploadDir) {
        this.imageRepository = imageRepository;
        this.uploadPath = Paths.get(uploadDir);
    }

    public ImageResponse saveImage(Long postId, MultipartFile file) {
        if(file == null || file.isEmpty()) {
            return null;
        }

        validateImage(file);

        try {
            Files.createDirectories(uploadPath);

            String originalFileName = file.getOriginalFilename();
            String extension = getFileExtension(originalFileName);
            String storedFileName = UUID.randomUUID() + extension;

            Path targetPath = uploadPath.resolve(storedFileName);
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            Image image = new Image();
            image.setPostId(postId);
            image.setOriginalFileName(originalFileName);
            image.setStoredFileName(storedFileName);
            image.setContentType(file.getContentType());
            image.setFileSize(file.getSize());
            image.setUploadDateTime(java.time.LocalDateTime.now());

            Image savedImage = imageRepository.save(image);

            return toResponse(savedImage);
        } catch (IOException exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to store image!");
        }
    }

    public Optional<Image> getImageByStoredFileName(String storedFileName) {
        return imageRepository.findByStoredFileName(storedFileName);
    }

    private void validateImage(MultipartFile file) {
        String contentType = file.getContentType();

        if(contentType == null || !isAllowedImageType(contentType)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid image type!");
        }
    }

    private boolean isAllowedImageType(String contentType) {
        return contentType.equals("image/jpeg") || contentType.equals("image/png") || contentType.equals("image/gif") || contentType.equals("image/webp");
    }

    private String getFileExtension(String originalFileName) {
        if (originalFileName == null || !originalFileName.contains(".")) {
            return "";
        }
        return originalFileName.substring(originalFileName.lastIndexOf("."));
    }

    public ImageResponse getImageResponseByPostId(Long postId) {
        return imageRepository.findByPostId(postId)
                .map(this::toResponse)
                .orElse(null);
    }

    private ImageResponse toResponse(Image image) {
        return new ImageResponse(image.getImageId(), image.getPostId(), image.getOriginalFileName(), image.getContentType(), image.getFileSize(), image.getUploadDateTime(), "/images/files/" + image.getStoredFileName());
    }
}
