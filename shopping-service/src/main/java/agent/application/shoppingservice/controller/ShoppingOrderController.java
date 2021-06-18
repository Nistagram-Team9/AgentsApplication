package agent.application.shoppingservice.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import agent.application.shoppingservice.dto.ShoppingOrderDto;
import agent.application.shoppingservice.exception.ProductNotAvailable;
import agent.application.shoppingservice.service.ShoppingOrderService;

@RestController
@RequestMapping(value = "/orders")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class ShoppingOrderController {

	private ShoppingOrderService orderService;

	public ShoppingOrderController(ShoppingOrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping
	public ResponseEntity<String> create(@RequestBody @Valid ShoppingOrderDto orderDto) throws ProductNotAvailable {
		orderService.create(orderDto);
		return new ResponseEntity<>("New order successfully created.", HttpStatus.CREATED);
	}

}
