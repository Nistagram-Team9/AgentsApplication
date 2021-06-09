package agent.application.productservice.dto;

public class ProductDto {

private String name;
	
	private Double price;
	
	private Integer total;
	
	private String picture;
	
	public ProductDto() {
		
	}

	public ProductDto(String name, Double price, Integer total, String picture) {
		super();
		this.name = name;
		this.price = price;
		this.total = total;
		this.picture = picture;
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
	
	
	
	
}
