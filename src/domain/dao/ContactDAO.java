package domain.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import org.hibernate.criterion.Restrictions;

import domain.dao.interfaces.IAccountDAO;
import domain.dao.interfaces.IContactDAO;
import domain.metier.Account;
import domain.metier.Address;
import domain.metier.Contact;
import util.HibernateUtil;

public class ContactDAO extends HibernateDaoSupport implements IContactDAO{
	
	public ContactDAO(){
		
	}
	
	@Override
	public Contact createContact(String firstname, String lastname, String emailC, Address add, Account creator){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		Contact contact = new Contact();
		contact.setEmail(emailC);
		contact.setFirstName(firstname);
		contact.setLastName(lastname);
		contact.setAdd(add);
		contact.setCreator(creator);
		
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		session.save(contact);
		session.saveOrUpdate(add);
		session.saveOrUpdate(creator);
		tx.commit();
		
		System.out.println("createContact réussi c="+contact.getId()); 
		return contact;
	}
	
	@Override
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
	
	@Override
	public void deleteContact(long id){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		Contact contact = (Contact) session.load(Contact.class, id);
		session.delete(contact);
		System.out.println("deleteContact réussi");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> searchContact(String firstname, String lastname, String emailC) {
		// Recherche avec tous les paramètres renseignés
		System.out.println("searchContact réussi");
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();

		List<Contact> listContact= session.createCriteria(Contact.class).add(Restrictions.like("firstName", "%"+firstname+"%"))
																		.add(Restrictions.like("lastName", "%"+lastname+"%"))
																		.add(Restrictions.like("email", "%"+emailC+"%")).list();
		
		session.getTransaction().commit();
		return listContact;
	}
	
	@Override
	public Contact getContactById(long id){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		Contact contact = (Contact) session.load(Contact.class, id);
		return contact;
	}
	
	@Override
	public List<Contact> getContactByCreator(Account acc){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		
		@SuppressWarnings("unchecked")
		List<Contact> listContactBis= session.createCriteria(Contact.class).list();
		List<Contact> listContact = new ArrayList<>();
		for(Contact c : listContactBis){
			if(c.getCreator().getId() == acc.getId()) listContact.add(c);
		}
		
		session.getTransaction().commit();
		return listContact;
	}

	@Override
	public void deleteContactByCreator(Account acc){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		
		@SuppressWarnings("unchecked")
		List<Contact> listContact= session.createCriteria(Contact.class).list();
		for(Contact c : listContact){
			if(c.getCreator().getId() == acc.getId()) deleteContact(c.getId());
		}
		
	}
	public static void main(String[] args){
		IContactDAO c = new ContactDAO();
		IAccountDAO a = new AccountDAO();
		Account acc = a.createAccount("login", "password");
		Address add = new Address();

		Address add1 = new Address();
		
		Address add2 = new Address();
		
		add.setStreet("street");
		add.setCity("city");
		add.setCountry("country");
		add.setZip("zip");
		
		c.createContact("Dupont", "duton", "llll", add, acc);
		c.createContact("D", "duron", "llll", add1, acc);
		c.createContact("Dup", "dumon", "llll", add2, acc);
		c.deleteContact(2);
		c.getContactByCreator(acc);
		List<Contact> listContact = c.searchContact("up", "du", "");
		for(Contact cont : listContact){
			System.out.println(cont.getFirstName());
		}
	}
	
}
