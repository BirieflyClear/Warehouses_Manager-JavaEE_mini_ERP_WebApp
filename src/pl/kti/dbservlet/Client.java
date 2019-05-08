package pl.kti.dbservlet;

public class Client {
	
	private int cID;
	private String name;
	private String city;
	private String username;
	
	public Client(String name, String city, String username) {
		super();
		this.name = name;
		this.city = city;
		this.username = username;
	}

	public Client(int cID, String name, String city, String username) {
		
		this.cID = cID;
		this.name = name;
		this.city = city;
		this.username = username;
	}

	public int getcID() {
		return cID;
	}

	public void setcID(int cID) {
		this.cID = cID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String getUsername() {
		return username;
	}

	
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Client [cID=" + cID + ", name=" + name + ", city=" + city + ", username=" + username + "]";
	}

}