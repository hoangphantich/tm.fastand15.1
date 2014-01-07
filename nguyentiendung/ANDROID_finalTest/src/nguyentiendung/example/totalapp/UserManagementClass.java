package nguyentiendung.example.totalapp;
/////////////////////for User Management ///////////////////////////////
public class UserManagementClass {
	public int id;
	public String username;
	public String email;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public UserManagementClass(int id, String username, String email) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
	}
	public UserManagementClass() {
		super();
	}
	
}
