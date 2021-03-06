package agent.application.shoppingservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {
	
	@Id
	@GeneratedValue
	private Integer id;

	private String name;
	
	private Double price;
	
	private Integer total;
	
	private String picture;	

}

