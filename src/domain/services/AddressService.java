package domain.services;

import domain.dao.AddressDAO;

public class AddressService {
	
	private AddressDAO dao;
	
	public AddressService(){
		dao = new AddressDAO();
	}
	
	public boolean createAddress(String street, String city, String zip, String country){
		return dao.createAddress(street, city, zip, country);
	}
	
	public boolean updateAddress(long id, String street, String city, String zip, String country){
		return dao.updateAddress(id, street, city, zip, country);
	}
	
	public boolean deleteAddress(long id){
		return dao.deleteAccount(id);
	}

}
