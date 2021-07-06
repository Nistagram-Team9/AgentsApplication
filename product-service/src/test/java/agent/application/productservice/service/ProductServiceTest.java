package agent.application.productservice.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import agent.application.productservice.dto.ProductDto;
import agent.application.productservice.exception.ImageStorageException;
import agent.application.productservice.model.Product;
import agent.application.productservice.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class ProductServiceTest {

	@Autowired
	private ProductService productService;
	
	@MockBean
	private ProductRepository productRepositoryMock;
	
	@MockBean
	private ImageStorageService imageStorageServiceMock;
	
	@Test
	public void create_successful() throws Exception {
		Product newProduct = new Product(1, "aa", 2000.0, 20, null);
		Mockito.when(productRepositoryMock.save(Mockito.any())).thenReturn(newProduct);
		Mockito.when(imageStorageServiceMock.storeImage(null,1)).thenReturn("putanja");

		Product returnedProduct = productService.create("aa", 2000.0, 20, null);
		assertEquals("putanja", returnedProduct.getPicture());
	}
	
	@Test(expected=ImageStorageException.class)
	public void create_unsuccessful() throws Exception {
		Product newProduct = new Product(1, "aa", 2000.0, 20, null);
		Mockito.when(productRepositoryMock.save(Mockito.any())).thenReturn(newProduct);
		Mockito.when(imageStorageServiceMock.storeImage(null,1)).thenThrow(ImageStorageException.class);
		Product returnedProduct = productService.create("aa", 2000.0, 20, null);
		
	}

	@Test
	public void findById_successful() {
		Optional<Product> newProduct = Optional.of(new Product(1, "aa", 2000.0, 20, null));
		Mockito.when(productRepositoryMock.findById(1)).thenReturn(newProduct);
		Product returnedProduct = productService.findById(1);
		assertEquals(Integer.valueOf(1), returnedProduct.getId());

	}
	
	@Test
	public void delete_successful() {
		Optional<Product> newProduct = Optional.of(new Product(1, "aa", 2000.0, 20, null));
		Mockito.when(productRepositoryMock.findById(1)).thenReturn(newProduct);
		Product returnedProduct = productService.delete(1);
		assertEquals(Integer.valueOf(1), returnedProduct.getId());

	}
	
	
	@Test
	public void getAll_successful() {
		List<Product> products = new ArrayList<Product>();
		Product newProduct =new Product(1, "aa", 2000.0, 20, null);
		products.add(newProduct);
		Mockito.when(productRepositoryMock.findAll()).thenReturn(products);
		List<Product> returnedProducts = productService.getAll();
		assertEquals(1, returnedProducts.size());

	}

	@Test
	public void update_successful() throws Exception {
		Optional<Product> newProduct = Optional.of(new Product(1, "aa", 2000.0, 20, null));
		Mockito.when(productRepositoryMock.findById(1)).thenReturn(newProduct);
		Mockito.when(imageStorageServiceMock.storeImage(null,1)).thenReturn("putanja");
		Product returnedProduct = productService.update(1, "novo ime", 2000.0, 20, null);
		assertEquals("novo ime", returnedProduct.getName());

	}
	
	
	

	



}