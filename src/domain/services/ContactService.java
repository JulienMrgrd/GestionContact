package domain.services;

import java.util.ArrayList;
import java.util.List;

import domain.dao.interfaces.IContactDAO;
import domain.metier.Account;
import domain.metier.Address;
import domain.metier.Contact;
import domain.metier.PhoneNumber;
import domain.services.interfaces.IContactService;

public class ContactService implements IContactService {
	
	private IContactDAO dao;
	
	public ContactService(IContactDAO dao){
		this.dao = dao;
	}
	
	@Override
	public Contact createContact(String firstname, String lastname, String emailC, Address add, Account creator){
		try{
			return dao.createContact(firstname, lastname, emailC, add, creator);
		} catch (Exception e){
			return null;
		}
	}
	
	@Override
	public boolean updateContact(long id, String firstname, String lastname, String emailC, Address add){
		try{
			return dao.updateContact(id, firstname, lastname, emailC, add);
		} catch (Exception e){
			return false;
		}
	}
	
	@Override
	public boolean deleteContact(long id){
		try{
			return dao.deleteContact(id);
		} catch (Exception e){
			return false;
		}
	}
	
	@Override
	public List<Contact> searchContact(String search, Account acc){
		try{
			return dao.searchContact(search, acc);
		} catch (Exception e){
			return null;
		}
	}

	@Override
	public Contact getContactById(long id) {
		try{
			return dao.getContactById(id);
		} catch (Exception e){
			return null;
		}
	}

	@Override
	public List<Contact> getContactByCreator(Account acc) {
		try{
			List<Contact> list = new ArrayList<>();
			List<Contact> l = dao.getContactByCreator(acc);
			if(l!=null){
				for(Contact c : l){
					if(acc!=null){
						if(c.getCreator().getId()==(acc.getId())){
							list.add(c);
						}
					}
				}
			}
			return list;
		} catch (Exception e){
			return null;
		}
	}

	@Override
	public void deleteContactByCreator(Account acc) {
		try{
			dao.deleteContactByCreator(acc);
		} catch (Exception e){}
	}

	@Override
	public void addPhonesInContact(long idContact, PhoneNumber pn) {
		try{
			dao.addPhonesInContact(idContact, pn);
		} catch (Exception e){}
	}

	@Override
	public void addContactInGroup(long id_cont, long id_group) {
		try{
			dao.addContactInGroup(id_cont, id_group);
		} catch (Exception e){}
	}

	@Override
	public Address getAddress(long id){
		try{
			return dao.getAddress(id);
		} catch (Exception e){
			return null;
		}
	}
}
