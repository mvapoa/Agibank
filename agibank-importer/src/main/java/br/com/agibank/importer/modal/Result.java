package br.com.agibank.importer.modal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {

	private Integer totalQuantityClients;
	private Integer totalQuantitySalemen;
	private Integer highestSale;
	private String worstSalesman;

}
