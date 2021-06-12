package agent.application.shoppingservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import agent.application.shoppingservice.dto.ShoppingOrderDto;
import agent.application.shoppingservice.exception.ProductNotAvailable;
import agent.application.shoppingservice.model.ShoppingOrder;
import agent.application.shoppingservice.model.Product;
import agent.application.shoppingservice.repository.ShoppingOrderRepository;
import agent.application.shoppingservice.repository.ProductRepository;

@Service
public class ShoppingOrderService {

	private final ShoppingOrderRepository orderRepository;
	private final ProductRepository productRepository;

	public ShoppingOrderService(ShoppingOrderRepository orderRepository, ProductRepository productRepository) {
		this.orderRepository = orderRepository;
		this.productRepository = productRepository;
	}

	public ShoppingOrder create(ShoppingOrderDto orderDto) throws ProductNotAvailable {
		List<Product> products = new ArrayList<Product>();
		Double total = 0.0;
		for (Integer id : orderDto.getProductIds()) {
			Product product = productRepository.getOne(id);
			if (product.getTotal() > 0) {
				total += product.getPrice();
				product.setTotal(product.getTotal() - 1);
				productRepository.save(product);
				products.add(productRepository.getOne(id));
			} else {
				throw new ProductNotAvailable("Product is not available.");
			}
		}
		ShoppingOrder order = new ShoppingOrder(null, orderDto.getCustomerName(), orderDto.getCustomerLastName(),
				orderDto.getAddress(), total, products);
		return orderRepository.save(order);

	}

}
