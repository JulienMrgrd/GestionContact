package domain.services;

import java.util.List;

import domain.dao.ContactDAO;
import domain.metier.Contact;

public class ContactService {
	
	private ContactDAO dao;
	
	public ContactService(){
		dao = new ContactDAO();
	}
	
	public boolean addContact(String firstname, String lastname, String emailC){
		return dao.addContact(firstname, lastname, emailC);
	}
	
	public boolean updateContact(long id, String firstname, String lastname, String emailC){
		return dao.updateContact(id, firstname, lastname, emailC);
	}
	
	public boolean deleteContact(long id){
		return dao.deleteContact(id);
	}
	
	public List<Contact> searchContact(String firstname, String lastname, String emailC){
		return dao.searchContact(firstname, lastname, emailC);
	}

}
