
public class Keyboard extends Product{
	
	private String layout;
	private String prodType;
	
	public Keyboard(int barcode, String type, String brand, String colour, String connec, int quant, double origPrc, double retaPrc, String layout) {
		super(barcode, type, brand, colour, connec, quant, origPrc, retaPrc);
		this.layout = layout;
		this.prodType = "keyboard";
	}
	
	public String getLayout() {
		return layout;
	}
	
	public String getProdType() {
		return prodType;
	}
	
	public String toAdminString() {
		return(getBarcode()+", "+getProdType()+", "+getType()+", "+getBrand()+", "+getColour()+", "+getConnec()+", "+getQuant()+", "+getOrigPrc()+", "+getRetaPrc()+", "+getLayout());
	}
	
	public String toCustString() {
		return(getBrand()+" "+getConnec()+" "+getType()+" "+getProdType()+" ("+getColour()+", "+getLayout()+" layout)");
	}
}
