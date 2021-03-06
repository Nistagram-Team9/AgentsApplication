package agent.application.productservice.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import agent.application.productservice.dto.ProductDto;
import agent.application.productservice.exception.ImageStorageException;
import agent.application.productservice.model.Product;
import agent.application.productservice.repository.ProductRepository;

@Service
public class ProductService {

	private final ProductRepository productRepository;
	private final ImageStorageService imageStorageService;

	public ProductService(ProductRepository productRepository, ImageStorageService imageStorageService) {
		this.productRepository = productRepository;
		this.imageStorageService = imageStorageService;
	}
	
	public Product create(String name, Double price, Integer total, MultipartFile file) throws Exception {
		Product newProduct = productRepository.save(new Product(null, name, price, total, null));
		String savedImagePath = imageStorageService.storeImage(file, newProduct.getId());
		newProduct.setPicture(savedImagePath);
		return newProduct;
	}
	
	public Product findById(Integer id) {
		return productRepository.findById(id).orElse(null);
	}

	public List<Product> getAll() {
		return productRepository.findAll();
	}
	
	public Product update(Integer id, String name, Double price, Integer total, MultipartFile file) throws Exception{
		imageStorageService.storeImage(file, id);
		Product product = this.findById(id);
		if (product != null) {
			product.setName(name);
			product.setPrice(price);
			product.setTotal(total);
		}
		productRepository.save(product);
		return product;
	}
	
	public Product delete(Integer id) {
		Product product = this.findById(id);
		if (product != null) {
			productRepository.delete(product);
		}
		return product;
		
	}
	
}
