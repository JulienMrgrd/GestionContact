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
		try{
			return dao.createAddress(street, city, zip, country);
		} catch (Exception e){
			return null;
		}
	}
	
	@Override
	public boolean updateAddress(long id, String street, String city, String zip, String country){
		try{
			return dao.updateAddress(id, street, city, zip, country);
		} catch (Exception e){
			return false;
		}
	}
	
	@Override
	public void deleteAddress(long id){
		try{
			dao.deleteAddress(id);
		} catch (Exception e){}
	}
	
	@Override
	public Address getAddressById(long id){
		try{
			return dao.getAddressById(id);
		} catch (Exception e){
			return null;
		}
	}

}
