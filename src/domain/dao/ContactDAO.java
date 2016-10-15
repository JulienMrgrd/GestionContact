package domain.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.metier.Account;
import domain.metier.Address;
import domain.metier.Contact;
import util.HibernateUtil;

public class ContactDAO{
	
	public ContactDAO(){
		
	}
	
	public Contact createContact(String firstname, String lastname, String emailC, Address add, Account creator){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Contact contact = new Contact();
		contact.setEmail(emailC);
		contact.setFirstName(firstname);
		contact.setLastName(lastname);
		contact.setAdd(add);
		contact.setCreator(creator);
		
		Transaction tx = session.beginTransaction();
		session.save(contact);
		session.saveOrUpdate(add);
		session.saveOrUpdate(creator);
		tx.commit();
		
		System.out.println("createContact réussi c="+contact.getId()); 
		return contact;
	}
	
	public boolean updateContact(long id, String firstName, String lastName, String emailC, Address add){
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		Transaction tx = session.beginTransaction();
		
		Contact contact = (Contact) session.load(Contact.class, id);
		contact.setFirstName(firstName);
		contact.setLastName(lastName);
		contact.setEmail(emailC);
		contact.setAdd(add);
		
		tx.commit();
		
		System.out.println("updateContact réussi");
		return true;
	}
	
	public void deleteContact(long id){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		Contact contact = (Contact) session.load(Contact.class, id);
		session.delete(contact);
		
		System.out.println("deleteContact réussi");
	}
	
	public List<Contact> searchContact(String firstname, String lastname, String emailC) {
		// Recherche avec tous les paramètres renseignés
		System.out.println("searchContact réussi");
		
		List<Contact> fakeList = new ArrayList<>();
		fakeList.add(new Contact(5, "julien", "m", "yopmail.com"));
		fakeList.add(new Contact(6, "felix", "l", "yopmail2.com"));
		return fakeList;
	}
	
	

	public static void main(String[] args){
		ContactDAO c = new ContactDAO();
		Account acc = new Account();
		Address add = new Address();

		Address add1 = new Address();
		
		Address add2 = new Address();

		c.createContact("Dupont", "ducon", "llll", add, acc);
		c.createContact("D", "ducon", "llll", add1, acc);
		c.createContact("Dup", "ducon", "llll", add2, acc);

		c.updateContact(1, "ddd", "d", "dodeoe",add);
		
		c.deleteContact(2);
		
	}
	
}
