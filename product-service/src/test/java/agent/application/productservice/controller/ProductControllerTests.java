package agent.application.productservice.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import agent.application.productservice.model.Product;
import agent.application.productservice.service.ProductService;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ProductControllerTests {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@MockBean
	private ProductService productServiceMock;

	@Test
	public void getProduct_successfull(){
		Product product = new Product();
		when(productServiceMock.findById(1)).thenReturn(product);
		HttpEntity<Object> httpEntity = null;
		ResponseEntity<Product> responseEntity = testRestTemplate.exchange("/products/1", HttpMethod.GET, httpEntity, Product.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	@Test
	public void getProduct_unsuccessfull(){
		Product product = new Product();
		when(productServiceMock.findById(1)).thenReturn(null);
		HttpEntity<Object> httpEntity = null;
		ResponseEntity<Product> responseEntity = testRestTemplate.exchange("/products/1", HttpMethod.GET, httpEntity, Product.class);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
	}
	
}

