package domain.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.metier.Address;
import util.HibernateUtil;

public class AddressDAO{

	public AddressDAO(){

	}

	public Address createAddress(String street, String city, String zip, String country){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
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

	public boolean updateAddress(long id, String street, String city, String zip, String country) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

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

	public void deleteAddress(long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		Address address = (Address) session.load(Address.class, id);
		session.delete(address);
		
		System.out.println("deleteAddress réussi");
	}
}
