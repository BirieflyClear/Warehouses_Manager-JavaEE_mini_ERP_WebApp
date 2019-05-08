package pl.kti.dbservlet;

public class ProductType {

	private int tID;
	private String typeName;
	
	
	public ProductType(int tID, String typeName) {
		super();
		this.tID = tID;
		this.typeName = typeName;
	}


	public int gettID() {
		return tID;
	}


	public void settID(int tID) {
		this.tID = tID;
	}


	public String getTypeName() {
		return typeName;
	}


	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


	@Override
	public String toString() {
		return "ProductType [tID=" + tID + ", typeName=" + typeName + "]";
	}
	
	
	
}
