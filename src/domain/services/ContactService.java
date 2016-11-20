package domain.services;

import java.util.List;

import domain.dao.interfaces.IContactDAO;
import domain.metier.Account;
import domain.metier.Address;
import domain.metier.Contact;
import domain.services.interfaces.IContactService;

public class ContactService implements IContactService {
	
	private IContactDAO dao;
	
	public ContactService(IContactDAO dao){
		this.dao = dao;
	}
	
	@Override
	public Contact createContact(String firstname, String lastname, String emailC, Address add, Account creator){
		return dao.createContact(firstname, lastname, emailC, add, creator);
	}
	
	@Override
	public boolean updateContact(long id, String firstname, String lastname, String emailC, Address add){
		return dao.updateContact(id, firstname, lastname, emailC, add);
	}
	
	@Override
	public void deleteContact(long id){
		dao.deleteContact(id);
	}
	
	@Override
	public List<Contact> searchContact(String firstname, String lastname, String emailC){
		return dao.searchContact(firstname, lastname, emailC);
	}

	@Override
	public Contact getContactById(long id) {
		return dao.getContactById(id);
	}

	@Override
	public List<Contact> getContactByCreator(Account acc) {
		return dao.getContactByCreator(acc);
	}

	@Override
	public void deleteContactByCreator(Account acc) {
		dao.deleteContactByCreator(acc);
	}

}
