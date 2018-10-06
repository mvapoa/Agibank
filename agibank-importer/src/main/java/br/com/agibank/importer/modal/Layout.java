package br.com.agibank.importer.modal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Layout {

	private String number;

	public Layout() {
		super();
	}

	public Layout(String number) {
		super();
		this.number = number;
	}

}
