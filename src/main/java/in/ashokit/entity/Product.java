package in.ashokit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Product {

	@Id
	@GeneratedValue
	private Integer pid;
	
	@Size(min = 3, max = 15, message = "Name must contain 3-15 charcters")
	@NotBlank(message = "name is mandatory")
	private String name;
	
	@NotNull(message = "price is mandatory")
	@Positive(message = "Price should be positive")
	private Double price;
	
	@NotNull(message = "Quantity is mandatory")
	@Positive(message = "Quantity should be positive")
	private Integer qty;
}
