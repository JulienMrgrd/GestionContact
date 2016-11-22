package domain.services;

import domain.dao.interfaces.IPhoneNumberDAO;
import domain.metier.Contact;
import domain.metier.PhoneNumber;
import domain.services.interfaces.IPhoneNumberService;

public class PhoneNumberService implements IPhoneNumberService {
	
private IPhoneNumberDAO dao;
	
	public PhoneNumberService(IPhoneNumberDAO dao){
		this.dao = dao;
	}
	
	@Override
	public PhoneNumber createPhoneNumber(String phoneKind, String phoneNumber, Contact contact){
		return dao.createPhoneNumber(phoneKind, phoneNumber, contact);
	}
	
	@Override
	public void updatePhoneNumber(long id, String phoneKind, String phoneNumber, Contact contact){
		dao.updatePhoneNumber(id, phoneKind, phoneNumber, contact);
	}
	
	@Override
	public void deletePhoneNumber(long id){
		dao.deletePhoneNumber(id);
	}

}
