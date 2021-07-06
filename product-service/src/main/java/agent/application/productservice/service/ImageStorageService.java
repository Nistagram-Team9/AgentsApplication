package agent.application.productservice.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.Permission;

import agent.application.productservice.exception.ImageStorageException;
import agent.application.productservice.model.DriveQuickstart;
import agent.application.productservice.model.Product;
import agent.application.productservice.repository.ProductRepository;

@Service
public class ImageStorageService {
	
	@Value("${file.upload-dir}")
	private String fileUploadDir;
	
	private static final String APPLICATION_NAME = "Google Drive API Java Quickstart";
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

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

	public String storeImage(MultipartFile file, Integer id) throws ImageStorageException, IOException, GeneralSecurityException {
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		List<String> webLinks=new ArrayList<String>();
		File item = new File();
		Permission permission = new Permission();
		permission.setRole("reader");
		permission.setType("anyone");
		List<Permission> permis = new ArrayList<Permission>();
		File fileMetadata = new File();
		fileMetadata.setName(file.getName());
		java.io.File filePath = new java.io.File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream( filePath );
        fos.write( file.getBytes() );
        fos.close();
		if (!filePath.exists()) {
			throw new ImageStorageException("Invalid file name!");
		} else {
			FileContent mediaContent = new FileContent("image/jpeg",filePath);
			Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY,
					DriveQuickstart.getCredentials(HTTP_TRANSPORT)).setApplicationName(APPLICATION_NAME).build();

			item = service.files().create(fileMetadata, mediaContent).setFields("id,webViewLink").execute();
			Permission perm = service.permissions().create(item.getId(), permission).execute();
			permis.add(perm);
			System.out.println("File ID: " + item.getWebViewLink());
			item.setPermissions(permis);
			webLinks.add(item.getWebViewLink());
			Product product = productRepostory.getOne(id);
			String[] els = item.getWebViewLink().split("/");
			product.setPicture(els[0]+"//"+els[2]+"/"+"uc?export=view&id="+els[5]);
			productRepostory.save(product);
			
		}
		return file.getName();
	}

}
