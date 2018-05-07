package cbook;

public class ContactBookClass implements ContactBook {
	static final int DEFAULT_SIZE = 100;

	private int counter;
	private ContactClass[] contacts;

	public ContactBookClass() {
		counter = 0;
		contacts = new ContactClass[DEFAULT_SIZE];
	}

	public boolean hasContact(String name) {
		return searchIndex(name) >= 0;
	}

	public int getNumberOfContacts() {
		return counter;
	}

	public void addContact(String name, int phone, String email) {
		if (counter == contacts.length) 
			resize();
		contacts[counter] = new ContactClass(name, phone, email);
		counter++;
	}

	public void deleteContact(String name) {
		contacts[searchIndex(name)] = contacts[counter-1];
		counter--;
	}

	public int getPhone(String name) {
		return contacts[searchIndex(name)].getPhone();
	}

	public String getEmail(String name) {
		return contacts[searchIndex(name)].getEmail();
	}

	public void setPhone(String name, int phone) {
		contacts[searchIndex(name)].setPhone(phone);
	}

	public void setEmail(String name, String email) {
		contacts[searchIndex(name)].setEmail(email);
	}

	public Iterator listContacts() {
		return new ContactIterator(contacts,counter);
	}
	
	private int searchIndex(String name) {
		int i = 0;
		int result = -1;
		boolean found = false;
		while (i<counter && !found)
			if (contacts[i].getName().equals(name))
				found = true;
			else
				i++;
		if (found) result = i;
		return result;
	}

	private void resize() {
		ContactClass tmp[] = new ContactClass[2*contacts.length];
		for (int i=0;i<counter; i++)
			tmp[i] = contacts[i];
		contacts = tmp;
	}

	
}
