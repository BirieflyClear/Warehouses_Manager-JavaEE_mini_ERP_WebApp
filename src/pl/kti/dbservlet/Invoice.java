// tutorial 1
package pl.kti.dbservlet;

public class Invoice {
	
	private int iID;
	private int clientID;
	private String client_name;
	private int order_ID;
	private float netValue;
	private float grossValue;
	
	public Invoice(int clientID, String client_name, int order_ID) {
		this.clientID = clientID;
		this.client_name = client_name;
		this.order_ID = order_ID;
	}

	public Invoice(int iID, int clientID, String client_name, int order_ID) {
		
		this.iID = iID;
		this.clientID = clientID;
		this.client_name = client_name;
		this.order_ID = order_ID;
	}

	
	
	
	public Invoice(int clientID, String client_name, int order_ID, float netValue, float grossValue) {
		super();
		this.clientID = clientID;
		this.client_name = client_name;
		this.order_ID = order_ID;
		this.netValue = netValue;
		this.grossValue = grossValue;
	}

	public Invoice(int iID2, int client_ID, String client_name2, int order_ID2, float netValue2, float grossValue2) {
		this.iID = iID2;
		this.clientID = client_ID;
		this.client_name = client_name2;
		this.order_ID = order_ID2;
		this.netValue = netValue2;
		this.grossValue = grossValue2;
	}

	public float getNetValue() {
		return netValue;
	}

	public void setNetValue(float netValue) {
		this.netValue = netValue;
	}

	public float getGrossValue() {
		return grossValue;
	}

	public void setGrossValue(float grossValue) {
		this.grossValue = grossValue;
	}

	public int getiID() {
		return iID;
	}

	public void setiID(int iID) {
		this.iID = iID;
	}

	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public int getOrder_ID() {
		return order_ID;
	}

	public void setOrder_ID(int order_ID) {
		this.order_ID = order_ID;
	}

	@Override
	public String toString() {
		return "Invoice [iID=" + iID + ", clientID=" + clientID + ", client_name=" + client_name + ", order_ID="
				+ order_ID + ", netValue=" + netValue + ", grossValue=" + grossValue + "]";
	}


	

}
