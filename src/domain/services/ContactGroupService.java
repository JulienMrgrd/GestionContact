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
	
	public void updateContactGroupe(long id, String nameGroupe){
		dao.updateContactGroupe(id, nameGroupe);
	}
}
