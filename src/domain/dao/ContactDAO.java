package domain.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import domain.dao.interfaces.IAccountDAO;
import domain.dao.interfaces.IContactDAO;
import domain.dao.interfaces.IEntrepriseDAO;
import domain.dao.interfaces.IPhoneNumberDAO;
import domain.metier.Account;
import domain.metier.Address;
import domain.metier.Contact;
import domain.metier.ContactGroup;
import domain.metier.PhoneNumber;

public class ContactDAO extends HibernateDaoSupport implements IContactDAO{
	
	public ContactDAO(){}
	
	@Override
	public Contact createContact(String firstname, String lastname, String emailC, Address add, Account creator){
		Session session = getSessionFactory().getCurrentSession();
		
		Contact contact = new Contact();
		contact.setEmail(emailC);
		contact.setFirstName(firstname);
		contact.setLastName(lastname);
		contact.setAdd(add);
		contact.setCreator(creator);
		contact.setPhones(new HashSet<PhoneNumber>());
		contact.setBooks(new HashSet<ContactGroup>());
		
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		session.saveOrUpdate(add);
		session.saveOrUpdate(creator);
		session.save(contact);
		tx.commit();
		
		System.out.println("createContact réussi c="+contact.getId()); 
		return contact;
	}
	
	@Override
	public boolean updateContact(long id, String firstName, String lastName, String emailC, Address add){
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		Contact contact = (Contact) session.load(Contact.class, id);
		if(firstName!= null && !firstName.isEmpty()) contact.setFirstName(firstName);
		if(lastName!= null && !lastName.isEmpty()) contact.setLastName(lastName);
		if(emailC!= null && !emailC.isEmpty()) contact.setEmail(emailC);
		if(add!= null) contact.setAdd(add);
		
		tx.commit();
	
		System.out.println("updateContact réussi");
		return true;
	}
	
	@Override
	public void deleteContact(long id){
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		
		Contact contact = (Contact) session.load(Contact.class, id);
		IPhoneNumberDAO phoneNumberDAO = new PhoneNumberDAO();
		for(PhoneNumber phone: contact.getPhones()) phoneNumberDAO.deletePhoneNumber(phone.getId());
		
		//On réouvre la session car delete deletePhoneNumber
		session = getSessionFactory().getCurrentSession();
		tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		session.delete(contact);
		
		//On réouvre la session car delete adresse la ferme
		session = getSessionFactory().getCurrentSession();
		tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		tx.commit();
		System.out.println("deleteContact réussi");
	}
	
	@Override
	public List<Contact> searchContact(String firstname, String lastname, String emailC) {
		// Recherche avec tous les paramètres renseignés
		System.out.println("searchContact réussi");
		Session session = getSessionFactory().getCurrentSession();

		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Contact> listContact= session.createCriteria(Contact.class).add(Restrictions.like("firstName", "%"+firstname+"%"))
																		.add(Restrictions.like("lastName", "%"+lastname+"%"))
																		.add(Restrictions.like("email", "%"+emailC+"%")).list();
		
		session.getTransaction().commit();
		return listContact;
	}
	
	@Override
	public Contact getContactById(long id){
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		Contact contact = (Contact) session.load(Contact.class, id);
		return contact;
	}

	@Override
	public void addPhonesInContact(long idContact, PhoneNumber pn){
		Session session = getSessionFactory().getCurrentSession();

		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();

		Contact contact = (Contact) session.load(Contact.class, idContact);
		Set<PhoneNumber> phoneNumbers = contact.getPhones();
		phoneNumbers.add(pn);
		
		tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		tx.commit();
	}
	
	@Override
	public List<Contact> getContactByCreator(Account acc){
		Session session = getSessionFactory().getCurrentSession();

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
		Session session = getSessionFactory().getCurrentSession();
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
		IEntrepriseDAO e = new EntrepriseDAO();
		Account acc = a.createAccount("login", "password");Account acc2 = a.createAccount("login2", "password");
		Address add = new Address();Address add1 = new Address();Address add2 = new Address();
		
		add.setStreet("street");
		add.setCity("city");
		add.setCountry("country");
		add.setZip("zip");
		
		IPhoneNumberDAO pnDAO = new PhoneNumberDAO();
		Contact contact = c.createContact("Dupont", "duton", "llll", add, acc);
		pnDAO.createPhoneNumber("0605040808", "047892463", contact);
		c.createContact("D", "duron", "llll", add1, acc2);
		c.createContact("Dup", "dumon", "llll", add2, acc);
		c.updateContact(2, "firstName", "lastName", "emailC", add1);
		e.createEntreprise("D", "duron", "llll", 15555, acc);
		//c.deleteContact(2);
		c.getContactByCreator(acc);
		List<Contact> listContact = c.searchContact("up", "du", "");
		for(Contact cont : listContact){
			System.out.println(cont.getFirstName());
		}
		//c.deleteContactByCreator(acc);
		//a.deleteAccount(acc.getId());
	}
	
}
