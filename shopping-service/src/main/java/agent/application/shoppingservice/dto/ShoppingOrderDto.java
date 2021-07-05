package agent.application.shoppingservice.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingOrderDto {

	private String customerName;

	private String customerLastName;

	private String address;

	private List<Integer> productIds = new ArrayList<>();

}
