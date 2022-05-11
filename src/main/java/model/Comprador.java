package model;

public class Comprador {
	private int id;
	private String name;
	private String address;
	private String email;
	private User user;
	
	public Comprador() {
		this(0);
	}
	public Comprador(int id) {
		this.id = id;
		setName("");
		setAddress("");
		setEmail("");
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getId() {
		return id;
	}
	
	
}
