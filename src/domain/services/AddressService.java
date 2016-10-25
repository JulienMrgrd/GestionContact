package domain.services;

import domain.dao.interfaces.IAddressDAO;
import domain.metier.Address;
import domain.services.interfaces.IAddressService;

public class AddressService implements IAddressService {
	
	private IAddressDAO dao;
	
	public AddressService(IAddressDAO dao){
		this.dao = dao;
	}
	
	@Override
	public Address createAddress(String street, String city, String zip, String country){
		return dao.createAddress(street, city, zip, country);
	}
	
	@Override
	public boolean updateAddress(long id, String street, String city, String zip, String country){
		return dao.updateAddress(id, street, city, zip, country);
	}
	
	@Override
	public void deleteAddress(long id){
		dao.deleteAddress(id);
	}

}
