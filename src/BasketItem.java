
public class BasketItem {
	
	private int quant;
	private Product prod;
	
	public BasketItem (int quant, Product prod) {
		this.prod = prod;
		this.quant = quant;
	}
	
	public Product getProd() {
		return prod;
	}
	
	public int getQuant() {
		return quant;
	}
	
	public void setQuant(int quant) {
		this.quant = quant;
	}
		
}
