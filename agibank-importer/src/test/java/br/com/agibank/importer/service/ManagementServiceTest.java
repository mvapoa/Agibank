package br.com.agibank.importer.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.agibank.importer.modal.ClientLayout;
import br.com.agibank.importer.modal.SaleLayout;
import br.com.agibank.importer.modal.SalesmanLayout;
import br.com.agibank.importer.processamento.LayoutFactory;
import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagementServiceTest extends TestCase {

	@Autowired
	public ManagementService service;

	@Test
	public void getTotalQuantityClientsTest() {
		String client1 = "002ç2345675434544345çJose da SilvaçRural";
		String client2 = "002ç2345675433444345çEduardo PereiraçRural";
		String client3 = "002ç2345675433444345çEduardo PereiraçCidade";
		String client4 = "002ç2340243920345959çJoão CarlosçCidade";

		List<ClientLayout> clients = new ArrayList<>();
		clients.add((ClientLayout) LayoutFactory.getLayout(client1));
		clients.add((ClientLayout) LayoutFactory.getLayout(client2));
		clients.add((ClientLayout) LayoutFactory.getLayout(client3));
		clients.add((ClientLayout) LayoutFactory.getLayout(client4));

		Integer result = service.getTotalQuantityClients(clients);
		assertEquals(3, result.intValue());
	}

	@Test
	public void getTotalQuantitySalemenTest() {
		String salesman1 = "001ç1234567891234çPedroç50000";
		String salesman2 = "001ç3245678865434çPauloç40000.99";
		String salesman3 = "001ç3245678865434çPauloç40000.99";
		String salesman4 = "001ç3394853848394çJoséç60000";
		String salesman5 = "001ç1234567891234çPedroç50000";
		String salesman6 = "001ç1234534567234çMarcosç521.00";

		List<SalesmanLayout> salesmen = new ArrayList<>();
		salesmen.add((SalesmanLayout) LayoutFactory.getLayout(salesman1));
		salesmen.add((SalesmanLayout) LayoutFactory.getLayout(salesman2));
		salesmen.add((SalesmanLayout) LayoutFactory.getLayout(salesman3));
		salesmen.add((SalesmanLayout) LayoutFactory.getLayout(salesman4));
		salesmen.add((SalesmanLayout) LayoutFactory.getLayout(salesman5));
		salesmen.add((SalesmanLayout) LayoutFactory.getLayout(salesman6));

		Integer result = service.getTotalQuantitySalemen(salesmen);
		assertEquals(4, result.intValue());
	}

	@Test
	public void getHighestSaleTest() {
		String sale1 = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro";
		String sale2 = "003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo";
		String sale3 = "003ç65ç[1-10-100,2-30-2.50,3-40-3.11]çAndreza";
		String sale4 = "003ç34ç[1-34-10,2-33-1.50,3-40-0.1]çCarlos";
		String sale5 = "003ç35ç[1-34-10,2-33-1.50,3-40-0.1]çCarlos";
		String sale6 = "003ç60ç[1-34-10,2-33-1.50,3-40-0.11]çPaulo";

		List<SaleLayout> sales = new ArrayList<>();
		sales.add((SaleLayout) LayoutFactory.getLayout(sale1));
		sales.add((SaleLayout) LayoutFactory.getLayout(sale2));
		sales.add((SaleLayout) LayoutFactory.getLayout(sale3));
		sales.add((SaleLayout) LayoutFactory.getLayout(sale4));
		sales.add((SaleLayout) LayoutFactory.getLayout(sale5));
		sales.add((SaleLayout) LayoutFactory.getLayout(sale6));

		SaleLayout result = service.getHighestSale(sales);
		assertEquals(65, result.getId().intValue());
	}

	@Test
	public void getWorstSalesmanTest() {
		String sale1 = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro";
		String sale2 = "003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo";
		String sale3 = "003ç65ç[1-10-100,2-30-2.50,3-40-3.11]çAndreza";
		String sale4 = "003ç34ç[1-34-10,2-33-1.50,3-40-0.1]çCarlos";
		String sale5 = "003ç35ç[1-34-10,2-33-1.50,3-40-0.1]çCarlos";
		String sale6 = "003ç60ç[1-34-10,2-33-1.50,3-40-0.11]çPaulo";

		List<SaleLayout> sales = new ArrayList<>();
		sales.add((SaleLayout) LayoutFactory.getLayout(sale1));
		sales.add((SaleLayout) LayoutFactory.getLayout(sale2));
		sales.add((SaleLayout) LayoutFactory.getLayout(sale3));
		sales.add((SaleLayout) LayoutFactory.getLayout(sale4));
		sales.add((SaleLayout) LayoutFactory.getLayout(sale5));
		sales.add((SaleLayout) LayoutFactory.getLayout(sale6));

		String result = service.getWorstSalesman(sales);
		assertEquals("Carlos", result);
	}
}
