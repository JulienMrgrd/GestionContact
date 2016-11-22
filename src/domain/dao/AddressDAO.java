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

		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		session.save(address);
		tx.commit();
		
		return address;
	}

	@Override
	public boolean updateAddress(long id, String street, String city, String zip, String country) {
		try{
			Session session = getSessionFactory().getCurrentSession();
	
			Transaction tx = session.getTransaction();
			if(!tx.isActive()) tx = session.beginTransaction();
			
			Address address = (Address) session.load(Address.class, id);
			if(street!=null && !street.isEmpty()) address.setStreet(street);
			if(city!=null && !city.isEmpty())address.setCity(city);
			if(zip!=null && !zip.isEmpty())address.setZip(zip);
			if(country!=null && !country.isEmpty())address.setCountry(country);
			
			tx.commit();
			
			return true;
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void deleteAddress(long id) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		Address address = (Address) session.load(Address.class, id);
		
		session = getSessionFactory().getCurrentSession();
		tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		session.delete(address);
		tx.commit();		
	}
}
