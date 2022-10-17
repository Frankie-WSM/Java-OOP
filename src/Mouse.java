
public class Mouse extends Product {

	private int buttons;
	private String prodType;
	
	public Mouse(int barcode, String type, String brand, String colour, String connec, int quant, double origPrc, double retaPrc, int buttons) {
		super(barcode, type, brand, colour, connec, quant, origPrc, retaPrc);
		this.buttons = buttons;
		this.prodType = "mouse";
	}
	
	public String getButtons() {
		return Integer.toString(buttons);
	}
	
	public String getProdType() {
		return prodType;
	}
	
	public String toAdminString() {
		return(getBarcode()+", "+getProdType()+", "+getType()+", "+getBrand()+", "+getColour()+", "+getConnec()+", "+getQuant()+", "+getOrigPrc()+", "+getRetaPrc()+", "+getButtons());
	}
	
	public String toCustString() {
		return(getBrand()+" "+getConnec()+" "+getType()+" "+getProdType()+" ("+getColour()+", "+getButtons()+" buttons)");
	}
}
