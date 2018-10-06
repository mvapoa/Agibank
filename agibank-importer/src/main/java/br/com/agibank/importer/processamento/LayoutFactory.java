package br.com.agibank.importer.processamento;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.agibank.importer.controller.ImporterController;
import br.com.agibank.importer.modal.ClientLayout;
import br.com.agibank.importer.modal.Layout;
import br.com.agibank.importer.modal.SaleLayout;
import br.com.agibank.importer.modal.SalesmanLayout;

public class LayoutFactory {

	private static final Logger log = LoggerFactory.getLogger(ImporterController.class);

	private final static String SPLIT = "ç";
	private final static String TEMPLATE_ONE = "001";
	private final static String TEMPLATE_TWO = "002";
	private final static String TEMPLATE_THREE = "003";

	public static Layout getLayout(String row) {
		try {
			String[] parts = row.split(SPLIT);
			switch (parts[0]) {
			case TEMPLATE_ONE:
				return new SalesmanLayout(parts);
			case TEMPLATE_TWO:
				return new ClientLayout(parts);
			case TEMPLATE_THREE:
				return new SaleLayout(parts);
			}
		} catch (Exception e) {
			log.error(">> formatted line is invalid {}", e.toString());
		}
		return null;
	}

}
