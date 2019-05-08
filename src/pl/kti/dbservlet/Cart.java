// tutorial 1
package pl.kti.dbservlet;

public class Cart {
	
	private int ID;
	private int pID;
	private String name;
	private String type;
	private float NET_price;
	private float GROSS_price;
	private int warehouse_ID;
	private float quantity;
	
	
	
	public Cart(int ID, int pID, String name, float nET_price, float gROSS_price) {
		super();
		this.ID = ID;
		this.pID = pID;
		this.name = name;
		NET_price = nET_price;
		GROSS_price = gROSS_price;
		
	}
	
	public Cart(int pID2, String name2, float nET_price2, float gROSS_price2) {
		this.pID = pID2;
		this.name = name2;
		NET_price = nET_price2;
		GROSS_price = gROSS_price2;
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getpID() {
		return pID;
	}
	public void setpID(int pID) {
		this.pID = pID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public float getNET_price() {
		return NET_price;
	}
	public void setNET_price(float nET_price) {
		NET_price = nET_price;
	}
	public float getGROSS_price() {
		return GROSS_price;
	}
	public void setGROSS_price(float gROSS_price) {
		GROSS_price = gROSS_price;
	}
	public int getWarehouse_ID() {
		return warehouse_ID;
	}
	public void setWarehouse_ID(int warehouse_ID) {
		this.warehouse_ID = warehouse_ID;
	}
	public float getQuantity() {
		return quantity;
	}
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "Cart [ID=" + ID + ", pID=" + pID + ", name=" + name + ", type=" + type + ", NET_price=" + NET_price
				+ ", GROSS_price=" + GROSS_price + ", warehouse_ID=" + warehouse_ID + ", quantity=" + quantity + "]";
	}
	
	
	
	

}
