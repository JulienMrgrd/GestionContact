package domain.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.metier.Contact;
import util.HibernateUtil;

public class ContactDAO{
	
	public ContactDAO(){
		
	}
	
	public boolean addContact(String firstname, String lastname, String emailC){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Contact c = new Contact();
		c.setEmail(emailC);
		c.setFirstName(firstname);
		c.setLastName(lastname);
		
		Transaction tx = session.beginTransaction();
		session.persist(c);
		tx.commit();
		
		System.out.println("addContact réussi");
		return true;
	}
	
	public boolean updateContact(long id, String firstname, String lastname, String emailC){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		Transaction tx = session.beginTransaction();
		Contact c = (Contact) session.load(Contact.class, id);
		c.setEmail("toto");
		tx.commit();
		
		System.out.println("updateContact réussi");
		return true;
	}
	
	public boolean deleteContact(long id){
		System.out.println("deleteContact réussi");
		return true;
	}
	
	public List<Contact> searchContact(String firstname, String lastname, String emailC) {
		// Recherche avec tous les paramètres renseignés
		System.out.println("searchContact réussi");
		
		List<Contact> fakeList = new ArrayList<>();
		fakeList.add(new Contact(5, "julien", "m", "yopmail.com"));
		fakeList.add(new Contact(6, "felix", "l", "yopmail2.com"));
		return fakeList;
	}

}
