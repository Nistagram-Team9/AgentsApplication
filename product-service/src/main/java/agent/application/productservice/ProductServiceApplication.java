package agent.application.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownHostException;


@SpringBootApplication
@EnableFeignClients
@RestController
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	class Dto {
		public String field;

		public Dto(String field){
			this.field = field;
		}

	}

	@GetMapping("/verify")
	public ResponseEntity<Dto> get() throws UnknownHostException {
		System.out.println("Verification");
		return new ResponseEntity<Dto>(new Dto("verfiied"), HttpStatus.OK);
	}
}