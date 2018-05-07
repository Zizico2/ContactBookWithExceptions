package cbook;

public class ContactIterator implements Iterator {
	private Contact[] contacts;
	private int counter;
	private int current;
	
	public ContactIterator(Contact[] contacts, int counter) {
		this.contacts = contacts;
		this.counter = counter;
		rewind();
	}
	
	public void rewind() {
		current = 0;
	}

	public boolean hasNext() {
		return current < counter;
	}

	public Contact next() {
		return contacts[current++];
	}
}
