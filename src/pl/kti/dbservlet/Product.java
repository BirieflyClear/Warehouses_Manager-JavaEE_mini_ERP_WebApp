// tutorial 1
package pl.kti.dbservlet;

public class Product {
	
	private int pID;
	private String name;
	private String type;
	private float NET_price;
	private float GROSS_price;
	private int warehouse_ID;
	private float quantity;
	
	public Product(String name, String type, float NET_price, float GROSS_price, int warehouse_ID, float quantity) {
		super();
		this.name = name;
		this.type = type;
		this.NET_price = NET_price;
		this.GROSS_price = GROSS_price;
		this.warehouse_ID = warehouse_ID;
		this.quantity = quantity;
	}



	public Product(int pID, String name, String type, float NET_price, float GROSS_price, int warehouse_ID,
			float quantity) {
		
		this.pID = pID;
		this.name = name;
		this.type = type;
		this.NET_price = NET_price;
		this.GROSS_price = GROSS_price;
		this.warehouse_ID = warehouse_ID;
		this.quantity = quantity;
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

	public void setNET_price(float NET_price) {
		this.NET_price = NET_price;
	}

	public float getGROSS_price() {
		return GROSS_price;
	}

	public void setGROSS_price(float GROSS_price) {
		this.GROSS_price = GROSS_price;
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
		return "Product [pID=" + pID + ", name=" + name + ", type=" + type + ", NET_price=" + NET_price
				+ ", GROSS_price=" + GROSS_price + ", warehouse_ID=" + warehouse_ID + ", quantity=" + quantity + "]";
	}

}
