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
		try{
			return dao.createPhoneNumber(phoneKind, phoneNumber, contact);
		} catch (Exception e){ 
			return null;
		}
	}
	
	@Override
	public void updatePhoneNumber(long id, String phoneKind, String phoneNumber, Contact contact){
		try{
			dao.updatePhoneNumber(id, phoneKind, phoneNumber, contact);
		} catch (Exception e){ }
	}
	
	@Override
	public void deletePhoneNumber(long id){
		try{
			dao.deletePhoneNumber(id);
		} catch (Exception e){ }
	}

}
