package domain.services;

import java.util.List;

import domain.dao.ContactDAO;
import domain.metier.Account;
import domain.metier.Address;
import domain.metier.Contact;

public class ContactService {
	
	private ContactDAO dao;
	
	public ContactService(){
		dao = new ContactDAO();
	}
	
	public Contact createContact(String firstname, String lastname, String emailC, Address add, Account creator){
		return dao.createContact(firstname, lastname, emailC, add, creator);
	}
	
	public boolean updateContact(long id, String firstname, String lastname, String emailC, Address add){
		return dao.updateContact(id, firstname, lastname, emailC, add);
	}
	
	public void deleteContact(long id){
		dao.deleteContact(id);
	}
	
	public List<Contact> searchContact(String firstname, String lastname, String emailC){
		return dao.searchContact(firstname, lastname, emailC);
	}

}
