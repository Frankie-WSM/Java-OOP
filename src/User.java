
public class User {

	private int userID;
	private String userName;
	private String name;
	private Address address;
	
	public User(int userID, String userName, String name, Address address) {
		
		this.userID = userID;
		this.userName = userName;
		this.name = name;
		this.address = address;
		
	}

	public int getUserID() {
		return(userID);
	}
	
	public String getUserName() {
		return(userName);
	}
	
	public String getName() {
		return(name);
	}
	
	public String getAddress() {
		return(address.toString());
	}
	
	
	public String toString() {
		return(userName + ", " + name + ", " + address.toString());
	}
	
}
