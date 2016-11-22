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
		try{
			return dao.createContactGroup(nameGroupe, a);
		} catch (Exception e){
			return null;
		}
	}
	
	@Override
	public boolean updateContactGroup(long id, String nameGroupe){
		try{
			return dao.updateContactGroup(id, nameGroupe);
		} catch (Exception e){
			return false;
		}
	}
	
	@Override
	public List<ContactGroup> findAll(Account a){
		try{
			return dao.findAll(a);
		} catch (Exception e){
			return null;
		}
	}
	
	@Override
	public void deleteContactGroup(long id){
		try{
			dao.deleteContactGroup(id);
		} catch (Exception e){}
	}
}
