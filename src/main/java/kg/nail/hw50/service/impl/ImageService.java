package kg.nail.hw50.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@RequiredArgsConstructor
public class ImageService {


    public String uploadImage(MultipartFile file) throws IOException {
        Path uploadPath = Paths.get("images");

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        try (InputStream inputStream = file.getInputStream()) {
            Path filePath = uploadPath.resolve(file.getOriginalFilename());
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + file.getOriginalFilename(), ioe);
        }
        return file.getOriginalFilename();
    }

    public String getImageUrl(String imageName) {
        return "A:/Nail/homework/hw-50/src/main/resources/static/images/" + imageName;
    }
}
