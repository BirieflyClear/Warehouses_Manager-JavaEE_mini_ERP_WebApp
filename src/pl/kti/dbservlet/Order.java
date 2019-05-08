// tutorial 1
package pl.kti.dbservlet;

public class Order {
	
	private int oID;
	private int client_ID;
	private String client_name;
	private float NET_price;
	private float GROSS_price;
	private String client_city;
	private int warehouse_ID;
	private String status;
	private String invoice_status;
	
	public Order(int oID, int client_ID, String client_name, float nET_price, float gROSS_price, String client_city,
			int warehouse_ID, String status, String invoice_status) {

		this.oID = oID;
		this.client_ID = client_ID;
		this.client_name = client_name;
		NET_price = nET_price;
		GROSS_price = gROSS_price;
		this.client_city = client_city;
		this.warehouse_ID = warehouse_ID;
		this.status = status;
		this.invoice_status = invoice_status;
	}

	public int getoID() {
		return oID;
	}

	public void setoID(int oID) {
		this.oID = oID;
	}

	public int getClient_ID() {
		return client_ID;
	}

	public void setClient_ID(int client_ID) {
		this.client_ID = client_ID;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
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

	public String getClient_city() {
		return client_city;
	}

	public void setClient_city(String client_city) {
		this.client_city = client_city;
	}

	public int getWarehouse_ID() {
		return warehouse_ID;
	}

	public void setWarehouse_ID(int warehouse_ID) {
		this.warehouse_ID = warehouse_ID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInvoice_status() {
		return invoice_status;
	}

	public void setInvoice_status(String invoice_status) {
		this.invoice_status = invoice_status;
	}

	@Override
	public String toString() {
		return "Order [oID=" + oID + ", client_ID=" + client_ID + ", client_name=" + client_name + ", NET_price="
				+ NET_price + ", GROSS_price=" + GROSS_price + ", client_city=" + client_city + ", warehouse_ID="
				+ warehouse_ID + ", status=" + status + ", invoice_status=" + invoice_status + "]";
	}
	
	

}
