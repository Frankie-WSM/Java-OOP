import java.util.Comparator;

public class ProductComparator implements Comparator<Product>{
	
	//compares retail prices so product objects can be sorted accordingly 
	public int compare(Product p1, Product p2) {
		return Double.compare(Double.parseDouble(p1.getRetaPrc()), Double.parseDouble(p2.getRetaPrc()));
	}
}
