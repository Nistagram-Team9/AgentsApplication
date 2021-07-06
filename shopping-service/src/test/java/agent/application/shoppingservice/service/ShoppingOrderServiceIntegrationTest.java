package agent.application.shoppingservice.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
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

import agent.application.shoppingservice.dto.ShoppingOrderDto;
import agent.application.shoppingservice.exception.ProductNotAvailable;
import agent.application.shoppingservice.model.Product;
import agent.application.shoppingservice.model.ShoppingOrder;
import agent.application.shoppingservice.repository.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
public class ShoppingOrderServiceIntegrationTest {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	ShoppingOrderService shoppingOrderService;

	@Test
	public void createShoppingOrder_availableProduct_successful() throws ProductNotAvailable {
		Product product = new Product(null, "New product 1", 200.0, 5, null);
		Product available = productRepository.save(product);

		Product notAvailable = new Product(null, "New product 2", 200.0, 0, null);
		productRepository.save(notAvailable);
		List<Integer> productsIds = new ArrayList<Integer>();
		productsIds.add(available.getId());
		ShoppingOrderDto shoppingOrderDto = new ShoppingOrderDto("John", "Doe", "Address1", productsIds);
		ShoppingOrder shoppingOrder = shoppingOrderService.create(shoppingOrderDto);
		assertEquals("John", shoppingOrder.getCustomerName());
		assertEquals("Doe", shoppingOrder.getCustomerLastName());
		assertEquals("Address1", shoppingOrder.getAddress());
	}

	@Test(expected = ProductNotAvailable.class)
	public void createShoppingOrder_productNotAvailable_unsuccessful() throws ProductNotAvailable {
		Product product = new Product(null, "New product 1", 200.0, 5, null);
		productRepository.save(product);

		Product notAvailable = new Product(null, "New product 2", 200.0, 0, null);
		productRepository.save(notAvailable);
		List<Integer> productsIds = new ArrayList<Integer>();
		productsIds.add(notAvailable.getId());
		ShoppingOrderDto shoppingOrderDto = new ShoppingOrderDto("Jonh", "Doe", "Address1", productsIds);
		shoppingOrderService.create(shoppingOrderDto);

	}
}