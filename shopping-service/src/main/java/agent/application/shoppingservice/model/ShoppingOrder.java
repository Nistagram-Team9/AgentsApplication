package agent.application.shoppingservice.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ShoppingOrder {

	@Id
	@GeneratedValue
	private Integer id;

	private String customerName;

	private String customerLastName;

	private String address;

	private Double total;

	@ManyToMany
	private List<Product> product = new ArrayList<Product>();

}
