package agent.application.agentreports.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import agent.application.agentreports.model.Product;
import agent.application.agentreports.service.AgentReportsService;

@RestController
@RequestMapping(value = "/agentReports")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class AgentReportsController {

	private AgentReportsService agentReportsService;

	public AgentReportsController(AgentReportsService agentReportsService) {
		this.agentReportsService = agentReportsService;
	}

	@GetMapping(value = "/mostSold")
	public ResponseEntity<List<Product>> mostSold() {
		return new ResponseEntity<>(agentReportsService.mostSold(), HttpStatus.OK);
	}

	@GetMapping(value = "/mostEarned")
	public ResponseEntity<List<Product>> mostEarned() {
		return new ResponseEntity<>(agentReportsService.mostEarned(), HttpStatus.OK);
	}

}
