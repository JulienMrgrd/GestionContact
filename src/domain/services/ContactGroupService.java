package domain.services;

import domain.dao.interfaces.IContactGroupDAO;
import domain.metier.ContactGroup;
import domain.services.interfaces.IContactGroupService;

public class ContactGroupService implements IContactGroupService {

	private IContactGroupDAO dao;
	
	public ContactGroupService(IContactGroupDAO dao){
		this.dao = dao;
	}
	
	@Override
	public ContactGroup createContactGroup(String nameGroupe){
		return dao.createContactGroup(nameGroupe);
	}
	
	@Override
	public void updateContactGroup(long id, String nameGroupe){
		dao.updateContactGroup(id, nameGroupe);
	}
}
