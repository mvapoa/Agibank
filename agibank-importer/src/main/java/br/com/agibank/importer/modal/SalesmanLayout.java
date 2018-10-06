package br.com.agibank.importer.modal;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalesmanLayout extends Layout {

	private String cpf;
	private String name;
	private BigDecimal salary;

	public SalesmanLayout(String[] parts) {
		super(parts[0]);
		this.cpf = parts[1];
		this.name = parts[2];
		this.salary = new BigDecimal(parts[3]);
	}

}
