import java.io.IOException;
import java.util.Scanner;

import Exceptions.DuplicateContactException;
import Exceptions.EmptyBookException;
import Exceptions.InvalidPhoneNumberException;
import cbook.ContactBook;
import cbook.ContactBookClass;
import cbook.Iterator;

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
				 deleteContact(in,cBook);
				 break;
			 case GET_PHONE:
				 getPhone(in,cBook);
				 break;
			 case GET_EMAIL: 
				 getEmail(in,cBook);
				 break;
			 case SET_PHONE:
				 setPhone(in,cBook);
				 break;
			 case SET_EMAIL:
				 setEmail(in,cBook);
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
			System.out.println("Not a valid phone number.");
		}
		catch (DuplicateContactException e){
			System.out.println(CONTACT_EXISTS);
		}
	}

	private static void addContact(ContactBook cBook,Scanner in)throws InvalidPhoneNumberException,
																		DuplicateContactException {
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
	
	private static void deleteContact(Scanner in, ContactBook cBook) {
		String name;
		name = in.nextLine();


		if (cBook.hasContact(name)) {
			cBook.deleteContact(name);
			System.out.println(CONTACT_REMOVED);
		}
		else System.out.println(NAME_NOT_EXIST);
	}
	
	private static void getPhone(Scanner in, ContactBook cBook) {
		String name;
		name = in.nextLine();
		if (cBook.hasContact(name)) {
			System.out.println(cBook.getPhone(name));
		}
		else System.out.println(NAME_NOT_EXIST);
	}
	
	private static void getEmail(Scanner in, ContactBook cBook) {
		String name;
		name = in.nextLine();
		if (cBook.hasContact(name)) {
			System.out.println(cBook.getEmail(name));
		}
		else System.out.println(NAME_NOT_EXIST);
	}
	
	private static void setPhone(Scanner in, ContactBook cBook) {
		String name;
		int phone;
		name = in.nextLine();
		phone = in.nextInt(); in.nextLine();
		if (cBook.hasContact(name)) {
			cBook.setPhone(name,phone);
			System.out.println(CONTACT_UPDATED);
		}
		else System.out.println(NAME_NOT_EXIST);
	}
	
	private static void setEmail(Scanner in, ContactBook cBook) {
		String name;
		String email;
		name = in.nextLine();
		email = in.nextLine();
		if (cBook.hasContact(name)) {
			cBook.setEmail(name,email);
			System.out.println(CONTACT_UPDATED);
		}
		else System.out.println(NAME_NOT_EXIST);
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
