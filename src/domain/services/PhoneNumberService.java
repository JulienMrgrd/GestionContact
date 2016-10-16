package domain.services;

import domain.dao.PhoneNumberDAO;
import domain.metier.Contact;
import domain.metier.PhoneNumber;

public class PhoneNumberService {
	
private PhoneNumberDAO dao;
	
	public PhoneNumberService(){
		dao = new PhoneNumberDAO();
	}
	
	public PhoneNumber createPhoneNumber(String phoneKind, String phoneNumber, Contact contact){
		return dao.createPhoneNumber(phoneKind, phoneNumber, contact);
	}
	
	public void updatePhoneNumber(long id, String phoneKind, String phoneNumber){
		dao.updatePhoneNumber(id, phoneKind, phoneNumber);
	}
	
	public void deletePhoneNumber(long id){
		dao.deletePhoneNumber(id);
	}

}
