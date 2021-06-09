package agent.application.productservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import agent.application.productservice.dto.ProductDto;

@Entity
public class Product {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	private String name;
	
	private Double price;
	
	private Integer total;
	
	private String picture;
	
	public Product() {
		
	}
	
	public Product(ProductDto productDto) {
		this.name = productDto.getName();
		this.price = productDto.getPrice();
		this.total = productDto.getTotal();
		this.picture = productDto.getPicture();
	}
	
	public Product(String name, Double price, Integer total) {
		this.name = name;
		this.price = price;
		this.total = total;
	}
	
	

}
