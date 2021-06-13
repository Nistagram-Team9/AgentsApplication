package agent.application.agentreports.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
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

import agent.application.agentreports.model.Product;
import agent.application.agentreports.model.ShoppingOrder;
import agent.application.agentreports.repository.ProductRepository;
import agent.application.agentreports.repository.ShoppingOrderRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AgentReportsControllerIntegrationTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ShoppingOrderRepository shoppingOrderRepository;

	@Before
	public void setUp() {
		Product product = new Product(1, "New product 1", 200.0, 5, null);
		productRepository.save(product);

		Product product2 = new Product(2, "New product 2", 600.0, 5, null);
		productRepository.save(product2);

		List<Product> orderedProducts = new ArrayList<>(Arrays.asList(product,product,product2));
		ShoppingOrder shoppingOrder = new ShoppingOrder(1, "John", "Doe", "Address1", 0.0, orderedProducts);
		shoppingOrderRepository.save(shoppingOrder);

	}
	
	@Test
	public void test_getMostPurchased__successful() {
		ResponseEntity<Product[]> responseEntity = testRestTemplate.getForEntity("/agentReports/mostSold",
				Product[].class);
		assertEquals("New product 1", responseEntity.getBody()[0].getName());

	}
	
	@Test
	public void test_getMostEarned__successful() {
		ResponseEntity<Product[]> responseEntity = testRestTemplate.getForEntity("/agentReports/mostEarned",
				Product[].class);
		assertEquals("New product 2", responseEntity.getBody()[0].getName());

	}

}
