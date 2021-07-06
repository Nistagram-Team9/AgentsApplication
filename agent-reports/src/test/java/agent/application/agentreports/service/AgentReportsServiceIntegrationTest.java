package agent.application.agentreports.service;

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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import agent.application.agentreports.model.Product;
import agent.application.agentreports.model.ShoppingOrder;
import agent.application.agentreports.repository.ProductRepository;
import agent.application.agentreports.repository.ShoppingOrderRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext
@Transactional
public class AgentReportsServiceIntegrationTest {

	@Autowired
	private AgentReportsService agentReportsService;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ShoppingOrderRepository shoppingOrderRepository;


	@Test
	public void test_getMostPurchased__successful() {
		Product product = new Product(null, "New product 1", 200.0, 5, null);
		productRepository.save(product);

		Product product2 = new Product(null, "New product 2", 600.0, 5, null);
		productRepository.save(product2);

		List<Product> orderedProducts = new ArrayList<>(Arrays.asList(product, product, product2));
		ShoppingOrder shoppingOrder = new ShoppingOrder(null, "John", "Doe", "Address1", 0.0, orderedProducts);
		shoppingOrderRepository.save(shoppingOrder);
		List<Product> mostPurchased = agentReportsService.mostSold();
		assertEquals("New product 1", mostPurchased.get(0).getName());
	}

	@Test
	public void test_getMostEarned__successful() {
		Product product = new Product(null, "New product 3", 200.0, 5, null);
		productRepository.save(product);

		Product product2 = new Product(null, "New product 4", 600.0, 5, null);
		productRepository.save(product2);

		List<Product> orderedProducts = new ArrayList<>(Arrays.asList(product, product, product2));
		ShoppingOrder shoppingOrder = new ShoppingOrder(null, "John", "Doe", "Address1", 0.0, orderedProducts);
		shoppingOrderRepository.save(shoppingOrder);
		List<Product> mostEarned = agentReportsService.mostEarned();
		assertEquals("New product 4", mostEarned.get(0).getName());

	}

}
