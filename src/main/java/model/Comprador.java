package model;

public class Comprador {
	private int id;
	private String name;
	private String gender;
	private String email;
	
	public Comprador() {
		this(0);
	}
	
	public Comprador(int id) {
		this.id = id;
		setName("");
		setGender("");
		setEmail("");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}
}
