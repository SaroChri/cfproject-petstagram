package gr.aueb.cf.finalprojpets.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
@Service
public class ImageService {

    @Value("${upload.path}")
    private String uploadPath;

    /**
     * Saves the provided image file with the specified image name.
     *
     * @param imageFile the image file to be saved
     * @param imageName the desired name for the image
     * @throws IOException if an I/O error occurs during the file operations
     */
    public void saveImage(MultipartFile imageFile, String imageName) throws IOException {

        // Check if the image file is empty
        if (imageFile.isEmpty()) {
            throw new IllegalArgumentException("Image file is empty");
        }

        // Get the file extension of the image
        String fileExtension = StringUtils.getFilenameExtension(imageFile.getOriginalFilename());
        // Check if the file extension is valid (allowed image types: JPG, JPEG, PNG, GIF)
        if (fileExtension == null || !fileExtension.matches("(?i)^(jpg|jpeg|png|gif)$")) {
            throw new IllegalArgumentException("Not valid image extension. Please try JPG, JPEG, PNG and GIF.");
        }

        // Get the absolute path of the upload directory
        // Create the upload directory if it doesn't exist
        Path uploadDir = Path.of(uploadPath).toAbsolutePath().normalize();
        Files.createDirectories(uploadDir);

        // Construct the file name with the desired image name and file extension
        String fileName = imageName + "." + fileExtension;

        // Resolve the file path within the upload directory
        Path filePath = uploadDir.resolve(fileName);
        // Copy the image file's input stream to the specified file path, preserving existing file if it already exists
        Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.COPY_ATTRIBUTES);
    }

    /**
     * Generates a unique file name based on the specified image name and file extension.
     *
     * @param imageName     the desired name for the image
     * @param fileExtension the file extension of the image
     * @return a unique file name
     */
    private String generateUniqueFileName(String imageName, String fileExtension) {
        long timestamp = System.currentTimeMillis();
        return imageName + "_" + timestamp + "." + fileExtension;
    }
}

