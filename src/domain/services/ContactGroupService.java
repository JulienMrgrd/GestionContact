package domain.services;

import domain.dao.ContactGroupDAO;

public class ContactGroupService {

	private ContactGroupDAO dao;
	
	public ContactGroupService(){
		dao = new ContactGroupDAO();
	}
	
	public boolean createContactGroupe(String nameGroupe){
		return dao.createContactGroupe(nameGroupe);
	}
	
	public boolean setContactGroupe(String nameGroupe){
		return dao.setContactGroupe(nameGroupe);
	}
}
