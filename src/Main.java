import java.util.Scanner;

import Exceptions.DuplicateContactException;
import Exceptions.EmptyBookException;
import Exceptions.InvalidPhoneNumberException;
import Exceptions.UnknownContactException;
import cbook.ContactBook;
import cbook.ContactBookClass;
import cbook.Iterator;

/**
 * @author Tiago Guerreiro 53649
 * @author Bernardo Borda d'Agua 53648
 *
 */
public class Main {
	//Constantes que definem os comandos
	 private static final String ADD_CONTACT    = "AC";
	 private static final String REMOVE_CONTACT = "RC";
	 private static final String GET_PHONE      = "GP";
	 private static final String GET_EMAIL      = "GE";
	 private static final String SET_PHONE      = "SP";
	 private static final String SET_EMAIL      = "SE";
	 private static final String LIST_CONTACTS  = "LC";
	 private static final String QUIT           = "Q";

	 //Constantes que definem as mensagens para o utilizador
	 private static final String CONTACT_EXISTS = "Contact already exists.";
	 private static final String NAME_NOT_EXIST = "Contact does not exist.";
	 private static final String CONTACT_ADDED = "Contact added.";
	 private static final String CONTACT_REMOVED = "Contact removed.";
	 private static final String CONTACT_UPDATED = "Contact updated.";
	 private static final String BOOK_EMPTY = "Contact book empty.";
	 private static final String INVALID_NUMBER = "Not a valid phone number.";

	 public static void main(String[] args) {
	 	Scanner in = new Scanner(System.in);
		 ContactBook cBook = new ContactBookClass();
		 String comm = getCommand(in);
	  
		 while (!comm.equals(QUIT)){
			 switch (comm) {
			 case ADD_CONTACT: 
				 processAddContact(in,cBook);
				 break;
			 case REMOVE_CONTACT:
				 processDeleteContact(in,cBook);
				 break;
			 case GET_PHONE:
				 processGetPhone(in,cBook);
				 break;
			 case GET_EMAIL: 
				 processGetEmail(in,cBook);
				 break;
			 case SET_PHONE:
				 processSetPhone(in,cBook);
				 break;
			 case SET_EMAIL:
				 processSetEmail(in,cBook);
				 break;
			 case LIST_CONTACTS:
				 processListAllContacts(cBook);
				 break;
			 default:
				 System.out.println("ERRO");
			 }
			 System.out.println();
			 comm = getCommand(in);
		 }
		 System.out.println("Goodbye!");
		 System.out.println();
		 in.close();
	 }
	
	private static String getCommand(Scanner in) {
		String input;
		input = in.nextLine().toUpperCase();
		return input;
	}

	private static void processAddContact(Scanner in, ContactBook cBook) {
		try {
			addContact(cBook,in);
			System.out.println(CONTACT_ADDED);
		} catch (InvalidPhoneNumberException e){
			System.out.println(INVALID_NUMBER);
		}
		catch (DuplicateContactException e){
			System.out.println(CONTACT_EXISTS);
		}
	}

	private static void addContact(ContactBook cBook,Scanner in)throws InvalidPhoneNumberException, DuplicateContactException {
		String name = in.nextLine();
		String phone = in.nextLine();
		String email = in.nextLine();
		int phoneNumber;
		try{
			phoneNumber = Integer.parseInt(phone);
			if(cBook.hasContact(name))
				throw  new DuplicateContactException();
			cBook.addContact(name,phoneNumber,email);
		}catch(NumberFormatException e) {
			throw new InvalidPhoneNumberException();
		}
	}
	
	private static void processDeleteContact(Scanner in, ContactBook cBook) {
		String name;
		name = in.nextLine();
		try {
			deleteContact(name, cBook);
			System.out.println(CONTACT_REMOVED);
		} catch(UnknownContactException e){
			System.out.println(NAME_NOT_EXIST);
		}
	}

	private static void deleteContact(String name,ContactBook cBook) throws UnknownContactException {
		if (!cBook.hasContact(name)) throw new UnknownContactException();
		cBook.deleteContact(name);
	}

	private static void processGetPhone(Scanner in, ContactBook cBook) {
		String name;
		name = in.nextLine();

		try{
			getPhone(name,cBook);
		} catch (UnknownContactException e) {
			 System.out.println(NAME_NOT_EXIST);
		}
	}

	private static void getPhone(String name, ContactBook cBook) throws UnknownContactException {
		if (!cBook.hasContact(name))
			throw new UnknownContactException();
		System.out.println(cBook.getPhone(name));
	}
	
	private static void processGetEmail(Scanner in, ContactBook cBook) {
		String name;
		name = in.nextLine();
		try {
			getEmail(name,cBook);
		} catch (UnknownContactException e){
			System.out.println(NAME_NOT_EXIST);
		}
	}

	private static void getEmail(String name,ContactBook cBook) throws UnknownContactException{
		if (!cBook.hasContact(name))
			throw new UnknownContactException();
		System.out.println(cBook.getEmail(name));
	}
	
	private static void processSetPhone(Scanner in, ContactBook cBook) {
		try{
			setPhone(in,cBook);
		} catch (InvalidPhoneNumberException e){
			System.out.println(INVALID_NUMBER);
		}
		catch (UnknownContactException e){
			System.out.println(NAME_NOT_EXIST);
		}
	}

	private static void setPhone(Scanner in, ContactBook cBook) {
		String name;
		String phone;
		name = in.nextLine();
		phone = in.nextLine();
		try {
			int phoneNumber = Integer.parseInt(phone);
			if (!cBook.hasContact(name))
				throw new UnknownContactException();
			cBook.setPhone(name,phoneNumber);
			System.out.println(CONTACT_UPDATED);
		} catch (NumberFormatException e){
			throw new InvalidPhoneNumberException();
		}
	}

	private static void processSetEmail(Scanner in, ContactBook cBook) {
		String name;
		String email;
		name = in.nextLine();
		email = in.nextLine();
		try{
			setEmail(name,email,cBook);
		} catch (UnknownContactException e){
			System.out.println(NAME_NOT_EXIST);
		}
	}

	private static void setEmail(String name, String email,ContactBook cBook) throws UnknownContactException {
		if (!cBook.hasContact(name))
			throw new UnknownContactException();
		cBook.setEmail(name,email);
		System.out.println(CONTACT_UPDATED);
	}

	private static void processListAllContacts(ContactBook cBook) {
		try{
			listAllContacts(cBook);
		} catch (EmptyBookException e){
			System.out.println(BOOK_EMPTY);
		}
	}

	private static void listAllContacts(ContactBook cBook)throws EmptyBookException{
		if (cBook.getNumberOfContacts() == 0)
			throw new EmptyBookException();

		Iterator it = cBook.listContacts();
		while(it.hasNext())
			System.out.println(it.next());
	}
}
