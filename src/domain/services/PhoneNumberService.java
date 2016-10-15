package domain.services;

import domain.dao.PhoneNumberDAO;

public class PhoneNumberService {
	
private PhoneNumberDAO dao;
	
	public PhoneNumberService(){
		dao = new PhoneNumberDAO();
	}
	
	public boolean createPhoneNumber(String phoneKind, String phoneNumber, long id_cont){
		return dao.createPhoneNumber(phoneKind, phoneNumber, id_cont);
	}
	
	public boolean updatePhoneNumber(long id, String phoneKind, String phoneNumber, String id_cont){
		return dao.updatePhoneNumber(id, phoneKind, phoneNumber, id_cont);
	}
	
	public boolean deletePhoneNumber(long id){
		return dao.deletePhoneNumber(id);
	}

}
