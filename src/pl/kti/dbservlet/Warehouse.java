// tutorial 1
package pl.kti.dbservlet;

public class Warehouse {
	
	private int wID;
	private String city;
	private String products_type;
	private float capacity;
	
	public Warehouse(String city, String products_type, float capacity) {
		super();
		this.city = city;
		this.products_type = products_type;
		this.capacity = capacity;
	}


	public Warehouse(int wID, String city, String products_type, float capacity) {
		
		this.wID = wID;
		this.city = city;
		this.products_type = products_type;
		this.capacity = capacity;
	}

	
	public int getwID() {
		return wID;
	}

	public void setwID(int wID) {
		this.wID = wID;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProducts_type() {
		return products_type;
	}

	public void setProducts_type(String products_type) {
		this.products_type = products_type;
	}

	public float getCapacity() {
		return capacity;
	}

	public void setCapacity(float capacity) {
		this.capacity = capacity;
	}

	
	@Override
	public String toString() {
		return "Warehouse [wID=" + wID + ", city=" + city + ", products_type=" + products_type + ", capacity="
				+ capacity + "]";
	}
	

}
