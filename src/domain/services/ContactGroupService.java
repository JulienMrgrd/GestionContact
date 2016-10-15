package domain.services;

import domain.dao.ContactGroupDAO;
import domain.metier.ContactGroup;

public class ContactGroupService {

	private ContactGroupDAO dao;
	
	public ContactGroupService(){
		dao = new ContactGroupDAO();
	}
	
	public ContactGroup createContactGroupe(String nameGroupe){
		return dao.createContactGroupe(nameGroupe);
	}
	
	public boolean setContactGroupe(long id, String nameGroupe){
		return dao.setContactGroupe(id, nameGroupe);
	}
}
