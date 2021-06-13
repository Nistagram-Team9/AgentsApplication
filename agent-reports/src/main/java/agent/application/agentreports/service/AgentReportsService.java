package agent.application.agentreports.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import agent.application.agentreports.model.Product;
import agent.application.agentreports.model.ShoppingOrder;
import agent.application.agentreports.repository.ProductRepository;
import agent.application.agentreports.repository.ShoppingOrderRepository;

@Service
public class AgentReportsService {

	private final ShoppingOrderRepository orderRepository;
	private final ProductRepository productRepository;

	public AgentReportsService(ShoppingOrderRepository orderRepository, ProductRepository productRepository) {
		this.orderRepository = orderRepository;
		this.productRepository = productRepository;
	}

	public List<Product> mostSold() {
		Map<Integer, Integer> productNumTimesSold = productNumTimesSold();
		Stream<Map.Entry<Integer, Integer>> sorted = productNumTimesSold.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()));
		Iterator<Map.Entry<Integer, Integer>> iterator = sorted.iterator();
		List<Product> products = new ArrayList<>();
		while (iterator.hasNext()) {
			products.add(productRepository.getOne(iterator.next().getKey()));
		}
		return products;
	}

	public List<Product> mostEarned() {
		Map<Integer, Integer> productNumTimesSold = productNumTimesSold();
		List<Product> productsFromDB = productRepository.findAll();
		Map<Integer, Double> productEarned = new HashMap<>();
		for (Product p : productsFromDB) {
			productEarned.put(p.getId(), productNumTimesSold.get(p.getId()) * p.getPrice());
		}

		Stream<Map.Entry<Integer, Double>> sorted = productEarned.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()));
		Iterator<Map.Entry<Integer, Double>> iterator = sorted.iterator();
		List<Product> products = new ArrayList<>();
		while (iterator.hasNext()) {
			products.add(productRepository.getOne(iterator.next().getKey()));
		}
		return products;
	}

	public Map<Integer, Integer> productNumTimesSold() {
		Map<Integer, Integer> mapOfProducts = new HashMap<>();
		List<Product> products = productRepository.findAll();
		for (Product p : products) {
			mapOfProducts.put(p.getId(), 0);
		}
		List<ShoppingOrder> orders = orderRepository.findAll();
		for (ShoppingOrder so : orders) {
			List<Product> orderProducts = so.getProduct();
			for (Product p : orderProducts) {
				mapOfProducts.put(p.getId(), mapOfProducts.get(p.getId()) + 1);
			}

		}
		return mapOfProducts;
	}

}
