package br.com.agibank.importer.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import br.com.agibank.importer.modal.ClientLayout;
import br.com.agibank.importer.modal.SaleLayout;
import br.com.agibank.importer.modal.SalesmanLayout;

public interface ManagementService {

	public String processFiles(File[] files) throws FileNotFoundException;

	public Integer getTotalQuantityClients(List<ClientLayout> clients);

	public Integer getTotalQuantitySalemen(List<SalesmanLayout> salesmen);

	public SaleLayout getHighestSale(List<SaleLayout> sales);

	public String getWorstSalesman(List<SaleLayout> sales);

}
