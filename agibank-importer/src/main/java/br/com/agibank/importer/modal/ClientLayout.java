package br.com.agibank.importer.modal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientLayout extends Layout {

	private String cnpj;
	private String name;
	private String businessArea;

	public ClientLayout(String[] parts) {
		super(parts[0]);
		this.cnpj = parts[1];
		this.name = parts[2];
		this.businessArea = parts[3];
	}

}
