package agent.application.productservice.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import agent.application.productservice.dto.MessageDto;
import agent.application.productservice.dto.ProductDto;
import agent.application.productservice.exception.ImageStorageException;
import agent.application.productservice.model.Product;
import agent.application.productservice.service.ProductService;

@RestController
@RequestMapping(value = "products")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class ProductController {

	private ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping
	public ResponseEntity<MessageDto> create(@RequestParam @Valid String name, @RequestParam @Valid Double price,
			@RequestParam @Valid Integer total, @RequestParam MultipartFile file) {
		Product product = null;
		try {
			product = productService.create(name, price, total, file);
		} catch (ImageStorageException e) {
			return new ResponseEntity<>(new MessageDto("Error", "Error while creating the product."),
					HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(new MessageDto("Success", "Product successfully created."), HttpStatus.CREATED);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> get(@PathVariable Integer id) {
		Product product = productService.findById(id);
		if (product != null) {
			return new ResponseEntity<>(product, HttpStatus.OK);
		} else {
			return ResponseEntity.badRequest().body(new Product());
		}
	}

	@GetMapping
	public ResponseEntity<List<Product>> getAll() {
		List<Product> products = productService.getAll();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<MessageDto> update(@PathVariable Integer id, @RequestParam @Valid String name,
			@RequestParam @Valid Double price, @RequestParam @Valid Integer total, @RequestParam MultipartFile file)
			throws ImageStorageException {
		Product product = productService.update(id, name, price, total, file);
		if (product != null) {
			return new ResponseEntity<>(new MessageDto("Success", "Product succesfully updated."), HttpStatus.OK);
		} else {
			return ResponseEntity.badRequest().body(new MessageDto("Error", "Error while updating product."));
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<MessageDto> delete(@PathVariable Integer id) {
		Product product = productService.delete(id);
		if (product != null) {
			return new ResponseEntity<>(new MessageDto("Success", "Product successfully deleted."), HttpStatus.OK);
		} else {
			return ResponseEntity.badRequest().body(new MessageDto("Failure", "Error while deleting product."));
		}

	}

}
