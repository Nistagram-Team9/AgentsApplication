package agent.application.productservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import agent.application.productservice.exception.ImageStorageException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.time.LocalDate;
import org.springframework.util.StringUtils;



@Service
public class ImageStorageService {
	
	 private final Path fileStorageLocation;
	 
	@Autowired
	public ImageStorageService(ImageStorageProperties fileStorageProperties) throws ImageStorageException {
	    this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
	            .toAbsolutePath().normalize();
	
	    try {
	        Files.createDirectories(this.fileStorageLocation);
	    } catch (Exception ex) {
	        throw new ImageStorageException("Could not create the directory where the uploaded files will be stored.", ex);
	    }
	}

	 public String storeImage(MultipartFile file, Integer id) throws ImageStorageException {
	        // Normalize file name
	        String fileName = id + "_" + StringUtils.cleanPath(file.getOriginalFilename());

	        try {
	            // Check if the file's name contains invalid characters
	            if(fileName.contains("..")) {
	                throw new ImageStorageException("Sorry! Filename contains invalid path sequence " + fileName);
	            }

	            // Copy file to the target location (Replacing existing file with the same name)
	            Path targetLocation = this.fileStorageLocation.resolve(fileName);
	            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

	            return fileName;
	        } catch (IOException ex) {
	            throw new ImageStorageException("Could not store file " + fileName + ". Please try again!", ex);
	        }
	    }

}
