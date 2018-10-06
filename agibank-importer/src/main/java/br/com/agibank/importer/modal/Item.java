package br.com.agibank.importer.modal;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {

	private Integer id;
	private Integer quantity;
	private BigDecimal price;

	public Item(String[] parts) {
		super();
		this.id = Integer.parseInt(parts[0]);
		this.quantity = Integer.parseInt(parts[1]);
		this.price = new BigDecimal(parts[2]);
	}

}
