package agent.application.productservice.controller;

import java.awt.PageAttributes.MediaType;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import agent.application.productservice.dto.ProductDto;
import agent.application.productservice.exception.ImageStorageException;
import agent.application.productservice.model.Product;
import agent.application.productservice.service.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

	private ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@PostMapping
	public ResponseEntity<Product> create(@RequestParam @Valid String name, @RequestParam @Valid Double price, @RequestParam @Valid Integer total, 
			@RequestParam MultipartFile file) {
		System.out.println("Uslo");
		Product product = null;
		try {
			product = productService.create(name, price, total, file);
		} catch (ImageStorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(null, HttpStatus.CREATED);
	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> get(@PathVariable Integer id) {
		Product product = productService.findById(id);
		if (product != null) {
			return new ResponseEntity<>(product, HttpStatus.OK);
		} else {
			return ResponseEntity.badRequest().body("Product with given id does not exist.");
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> getAll() {
		List<Product> products = productService.getAll();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody @Valid ProductDto productDto) {
		Product product = productService.update(id, productDto);
		if (product != null) {
			return new ResponseEntity<>(product, HttpStatus.OK);
		} else {
			return ResponseEntity.badRequest().body("Product with given id does not exist.");
		}
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		Product product = productService.delete(id);
		if (product != null) {
			return new ResponseEntity<>(product, HttpStatus.OK);
		} else {
			return ResponseEntity.badRequest().body("Product with given id does not exist.");
		}


	}
	
	

}
