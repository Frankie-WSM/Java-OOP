import java.io.*;
import java.util.*;

public class Customer extends User {

	private ArrayList<BasketItem> shoppingBasket = new ArrayList<>();
	
	public Customer(int userID, String userName, String name, Address address, ArrayList<BasketItem> shoppingBasket) {
		super(userID, userName, name, address);
		this.shoppingBasket = shoppingBasket;
	}
	
	public ArrayList<BasketItem> getShoppingBasket() {
		return shoppingBasket;
	}
	
	public double total_amount() { //gets total price of customers basket
		double amount = 0;
		for (BasketItem product : shoppingBasket) {
			for (int i = 0; i < product.getQuant(); i++) {
				amount += Double.parseDouble(product.getProd().getRetaPrc());
			}
		}
		return Double.parseDouble(String.format("%.2f", amount));
	}
	
	public ArrayList<Product> takeStock(ArrayList<Product> productList) { //function to alter a products quantity
		PrintWriter pw;
		try {
			pw = new PrintWriter("Stock.txt");//clear the file
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		File stockFile = new File("Stock.txt");
		try {
			Writer output = new BufferedWriter(new FileWriter(stockFile, true));
			for (BasketItem item : shoppingBasket) {
				for (Product product : productList) {
					if (item.getProd().getBarcode().equals(product.getBarcode())) {
						int newQuant = Integer.parseInt(product.getQuant()) - item.getQuant();
						product.setQuant(newQuant); //change quantity
					}
				}
			}
			for (Product product : productList) { //re-write the file
				if (product instanceof Keyboard) {
					output.append(((Keyboard) product).toAdminString()+"\n");
				} else {
					output.append(((Mouse) product).toAdminString()+"\n");
				}
			}
			output.close();
		} catch (Exception e0) {
			e0.printStackTrace();
		}
		this.shoppingBasket.clear();
		return productList;
	}
	
	public void add_item(BasketItem item) {
		shoppingBasket.add(item);
	}
	
	public boolean check_basket(Product product, int quant) { //checks if a product can be added to the basket depending on quantity
		for (BasketItem item : this.shoppingBasket) {
			if (product.getBarcode().equals(item.getProd().getBarcode())) {
				if (Integer.parseInt(product.getQuant()) >= quant + item.getQuant()) {
					item.setQuant(quant + item.getQuant());
					return true;
				} else {
					return false;
				}
			}
		}
		BasketItem item = new BasketItem(quant, product);
		this.add_item(item);
		return true;
	}
}
