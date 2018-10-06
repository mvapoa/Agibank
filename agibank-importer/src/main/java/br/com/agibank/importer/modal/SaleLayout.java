package br.com.agibank.importer.modal;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleLayout extends Layout {

	private Integer id;
	private List<Item> items;
	private String salesman;

	public SaleLayout() {
		super();
		this.items = new ArrayList<>();
	}

	public SaleLayout(String[] parts) {
		super(parts[0]);
		this.id = Integer.parseInt(parts[1]);
		this.items = createItems(parts[2]);
		this.salesman = parts[3];
	}

	public List<Item> createItems(String text) {
		String regex = "(\\w{1,9}-\\d{1,9}-\\d{1,9}[.]{0,1}\\d{0,4})";
		Pattern pattern = Pattern.compile("[\\[]" + regex + "[,]" + regex + "[,]" + regex + "[\\]]");
		Matcher matcher = pattern.matcher(text);
		matcher.find();

		List<Item> items = new ArrayList<>();
		for (int i = 1; i <= matcher.groupCount(); i++) {
			String[] item = matcher.group(i).split("-");
			items.add(new Item(item));
		}
		return items;
	}

	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for (Item item : items) {
			BigDecimal totalCost = item.getPrice().multiply(new BigDecimal(item.getQuantity()));
			total = total.add(totalCost);
		}
		return total;
	}

}
