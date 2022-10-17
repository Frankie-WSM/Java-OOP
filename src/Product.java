
public class Product {

	private int barcode;
	private String type;
	private String brand;
	private String colour;
	private String connec;
	private int quant;
	private double origPrc;
	private double retaPrc;
	
	public Product(int barcode, String type, String brand, String colour, String connec, int quant, double origPrc, double retaPrc) {
		
		this.barcode = barcode;
		this.type = type;
		this.brand = brand;
		this.colour = colour;
		this.connec = connec;
		this.quant = quant;
		this.origPrc = origPrc;
		this.retaPrc = retaPrc;
	}
	
	public String getBarcode() {
		return Integer.toString(barcode);
	}
	
	public String getType() {
		return type;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public String getColour() {
		return colour;
	}
	
	public String getConnec() {
		return connec;
	}
	
	public String getQuant() {
		return Integer.toString(quant);
	}
	
	public String getOrigPrc() {
		return Double.toString(origPrc);
	}
	
	public String getRetaPrc() {
		return Double.toString(retaPrc);
	}
	
	public void setQuant(int quant) {
		this.quant = quant;
	}
	
}
