package cbook;

public class ContactClass implements Contact {
	private String name;
	private int phone;
	private String email;

	public ContactClass(String name, int phone, String email) {
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPhone() {
		return phone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setPhone(int phone) {
		this.phone = phone;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean equals(Object obj) {
		if (this == obj) return true; 
		
		if (obj == null) return false;
		
		if (!(obj instanceof Contact)) return false;
			
		Contact other = (Contact) obj; 
		
		if (name == null) {
			if (other.getName() != null) return false;
			else return true;
		}
		else return name.equals(other.getName());
	}
	
	public String toString() {
		return getName() + "; " + getEmail() + "; " + getPhone();
	}
}
