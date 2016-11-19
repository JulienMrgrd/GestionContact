package domain.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import domain.dao.interfaces.IAddressDAO;
import domain.metier.Address;

public class AddressDAO extends HibernateDaoSupport implements IAddressDAO{

	public AddressDAO(){

	}

	@Override
	public Address createAddress(String street, String city, String zip, String country){
		Session session = getSessionFactory().getCurrentSession();
		
		Address address = new Address();
		address.setStreet(street);
		address.setCity(city);
		address.setZip(zip);
		address.setCountry(country);

		Transaction tx = session.beginTransaction();
		session.save(address);
		tx.commit();
		
		System.out.println("createAddress réussi");
		return address;
	}

	@Override
	public boolean updateAddress(long id, String street, String city, String zip, String country) {
		Session session = getSessionFactory().getCurrentSession();

		Transaction tx = session.beginTransaction();
		
		Address address = (Address) session.load(Address.class, id);
		address.setStreet(street);
		address.setCity(city);
		address.setZip(zip);
		address.setCountry(country);
		
		tx.commit();
		
		System.out.println("updateAddress réussi");
		return true;
	}

	@Override
	public void deleteAddress(long id) {
		Session session = getSessionFactory().getCurrentSession();

		Address address = (Address) session.load(Address.class, id);
		session.delete(address);
		
		System.out.println("deleteAddress réussi");
	}
}
