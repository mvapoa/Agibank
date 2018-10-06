package br.com.agibank.importer.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import br.com.agibank.importer.modal.ClientLayout;
import br.com.agibank.importer.modal.Layout;
import br.com.agibank.importer.modal.Result;
import br.com.agibank.importer.modal.SaleLayout;
import br.com.agibank.importer.modal.SalesmanLayout;
import br.com.agibank.importer.processamento.LayoutFactory;

@Service
public class ManagementServiceImpl implements ManagementService {

	public String processFiles(File[] files) throws FileNotFoundException {
		List<ClientLayout> clients = new ArrayList<>();
		List<SalesmanLayout> salesmen = new ArrayList<>();
		List<SaleLayout> sales = new ArrayList<>();

		for (File file : files) {
			Scanner body = new Scanner(file);
			while (body.hasNextLine()) {
				String row = body.nextLine();
				Layout layout = LayoutFactory.getLayout(row);

				if (layout instanceof ClientLayout) {
					clients.add((ClientLayout) layout);
				} else if (layout instanceof SalesmanLayout) {
					salesmen.add((SalesmanLayout) layout);
				} else if (layout instanceof SaleLayout) {
					sales.add((SaleLayout) layout);
				}
			}
			body.close();
		}

		Result result = new Result();
		result.setTotalQuantityClients(getTotalQuantityClients(clients));
		result.setTotalQuantitySalemen(getTotalQuantitySalemen(salesmen));
		result.setHighestSale(getHighestSale(sales).getId());
		result.setWorstSalesman(getWorstSalesman(sales));
		return new Gson().toJson(result);
	}

	public Integer getTotalQuantityClients(List<ClientLayout> clients) {
		List<String> total = new ArrayList<>();
		for (ClientLayout client : clients) {
			if (!total.contains(client.getCnpj())) {
				total.add(client.getCnpj());
			}
		}
		return total.size();
	}

	public Integer getTotalQuantitySalemen(List<SalesmanLayout> salesmen) {
		List<String> total = new ArrayList<>();
		for (SalesmanLayout salesman : salesmen) {
			if (!total.contains(salesman.getCpf())) {
				total.add(salesman.getCpf());
			}
		}
		return total.size();
	}

	public SaleLayout getHighestSale(List<SaleLayout> sales) {
		SaleLayout highestSale = new SaleLayout();
		for (SaleLayout sale : sales) {
			BigDecimal totalSale = sale.getTotal();
			BigDecimal totalHighestSale = highestSale.getTotal();
			if (totalSale.compareTo(totalHighestSale) > 0) {
				highestSale = sale;
			}
		}
		return highestSale;
	}

	public String getWorstSalesman(List<SaleLayout> sales) {
		HashMap<String, BigDecimal> totalSalesBySalesman = new HashMap<>();
		HashMap<String, List<SaleLayout>> groupSalesBySalesman = toGroupSalesBySalesman(sales);
		groupSalesBySalesman.forEach((key, value) -> {
			BigDecimal totalAllSales = getTotalAllSales(value);
			totalSalesBySalesman.put(key, totalAllSales);
		});
		return findWorstSalesman(totalSalesBySalesman);
	}

	private HashMap<String, List<SaleLayout>> toGroupSalesBySalesman(List<SaleLayout> sales) {
		HashMap<String, List<SaleLayout>> group = new HashMap<>();
		for (SaleLayout sale : sales) {
			String salesman = sale.getSalesman();
			if (!group.containsKey(salesman)) {
				group.put(salesman, new ArrayList<>());
			}
			group.get(salesman).add(sale);
		}
		return group;
	}

	private BigDecimal getTotalAllSales(List<SaleLayout> sales) {
		BigDecimal total = BigDecimal.ZERO;
		for (SaleLayout sale : sales) {
			total = total.add(sale.getTotal());
		}
		return total;
	}

	private String findWorstSalesman(HashMap<String, BigDecimal> totalSalesBySalesman) {
		AtomicReference<String> name = new AtomicReference<>();
		AtomicReference<BigDecimal> total = new AtomicReference<>();
		totalSalesBySalesman.forEach((key, value) -> {
			if (total.get() == null) {
				name.set(key);
				total.set(value);
			} else {
				if (value.compareTo(total.get()) < 0) {
					name.set(key);
					total.set(value);
				}
			}
		});
		return name.get();
	}

}
