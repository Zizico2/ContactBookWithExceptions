package cbook;

public interface Contact {
	/**
	 * Devolve o nome do contacto
	 * @return nome do contacto
	 */
	String getName();
	
	/**
	 * Devolve o numero de telefone do contacto
	 * @return telefone do contacto
	 */
	int getPhone();
	
	/**
	 * Devolve o email do contacto
	 * @return email do contacto
	 */
	String getEmail();
	
	/**
	 * Altera o numero de telefone do contacto
	 * @param phone novo numero de telefone
	 */
	void setPhone(int phone);
	
	/**
	 * Altera o email do contacto
	 * @param email novo email de telefone
	 */
	void setEmail(String email);
}