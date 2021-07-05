package agent.application.productservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import agent.application.productservice.exception.ImageStorageException;
import agent.application.productservice.model.Product;
import agent.application.productservice.repository.ProductRepository;

import java.io.IOException;
import java.nio.file.*;
import org.springframework.util.StringUtils;

@Service
public class ImageStorageService {
	
	@Value("${file.upload-dir}")
	private String fileUploadDir;

	private final Path fileStorageLocation;
	private final ProductRepository productRepostory;

	@Autowired
	public ImageStorageService(ImageStorageProperties fileStorageProperties, ProductRepository productRepository)
			throws ImageStorageException {
		this.productRepostory = productRepository;
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new ImageStorageException("Could not create the directory where the uploaded files will be stored.",
					ex);
		}
	}

	public String storeImage(MultipartFile file, Integer id) throws ImageStorageException {
		String fileName = null;
		if (file.getOriginalFilename() != null) {
			String fileName2 = fileUploadDir +"/"+ file.getOriginalFilename();
			fileName = id + "_" + StringUtils.cleanPath(file.getOriginalFilename());
			Product product = productRepostory.getOne(id);
			product.setPicture(fileName2);
			productRepostory.save(product);
		} else {
			throw new ImageStorageException("Invalid file name!");
		}

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
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
