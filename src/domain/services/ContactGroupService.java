package domain.services;

import java.util.List;

import domain.dao.interfaces.IContactGroupDAO;
import domain.metier.Account;
import domain.metier.ContactGroup;
import domain.services.interfaces.IContactGroupService;

public class ContactGroupService implements IContactGroupService {

	private IContactGroupDAO dao;
	
	public ContactGroupService(IContactGroupDAO dao){
		this.dao = dao;
	}
	
	@Override
	public ContactGroup createContactGroup(String nameGroupe, Account a){
		return dao.createContactGroup(nameGroupe, a);
	}
	
	@Override
	public void updateContactGroup(long id, String nameGroupe){
		dao.updateContactGroup(id, nameGroupe);
	}
	
	@Override
	public List<ContactGroup> findAll(Account a){
		return dao.findAll(a);
	}
}
