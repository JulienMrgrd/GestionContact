package domain.services.interfaces;

import domain.metier.Address;

public interface IAddressService {

	Address createAddress(String street, String city, String zip, String country);

	boolean updateAddress(long id, String street, String city, String zip, String country);

	void deleteAddress(long id);

}