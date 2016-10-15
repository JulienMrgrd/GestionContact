package domain.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.metier.Address;
import domain.metier.Contact;
import util.HibernateUtil;

public class AddressDAO {

	public AddressDAO(){
		
	}
	
	public boolean createAddress(String street, String city, String zip, String country){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Address a = new Address();
		a.setStreet(street);
		a.setCity(city);
		a.setZip(zip);
		a.setCountry(country);
		Transaction tx = session.beginTransaction();
		
		session.persist(a);
		tx.commit();
		System.out.println("createAddress réussi");
		return true;
	}
	
	public boolean updateAddress(long id, String street, String city, String zip, String country) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteAccount(long id) {
		// TODO Auto-generated method stub
		return false;
	}


	
}
