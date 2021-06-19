package agent.application.shoppingservice.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import agent.application.shoppingservice.dto.ShoppingOrderDto;
import agent.application.shoppingservice.model.Product;
import agent.application.shoppingservice.repository.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@Transactional
public class ShoppingOrderControllerIntegrationTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private ProductRepository productRepository;

	@Before
	public void setUp() {
		Product product = new Product(1, "New product 1", 200.0, 5, null);
		productRepository.save(product);

		Product notAvailable = new Product(2, "New product 2", 200.0, 0, null);
		productRepository.save(notAvailable);

	}

	@Test
	public void createShoppingOrder_availableProduct_successful() {
		List<Integer> productsIds = new ArrayList<Integer>();
		productsIds.add(1);
		ShoppingOrderDto shoppingOrderDto = new ShoppingOrderDto("Jonh", "Doe", "Address1", productsIds);
		ResponseEntity<String> responseEntity = testRestTemplate.postForEntity("/orders", shoppingOrderDto,
				String.class);
		assertEquals("{\"header\":\"Success\",\"message\":\"New order is created successfully.\"}", responseEntity.getBody());

	}

	@Test
	public void createShoppingOrder_productNotAvailable_unsuccessful() {
		List<Integer> productsIds = new ArrayList<Integer>();
		productsIds.add(2);
		ShoppingOrderDto shoppingOrderDto = new ShoppingOrderDto("Jonh", "Doe", "Address1", productsIds);
		ResponseEntity<String> responseEntity = testRestTemplate.postForEntity("/orders", shoppingOrderDto,
				String.class);
		assertEquals("Product is not available.", responseEntity.getBody().substring(12, 37));

	}

}
