package domain.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import domain.dao.interfaces.IContactDAO;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> searchContact(String search, Account acc) {
		// Recherche avec tous les paramètres renseignés
		System.out.println("searchContact réussi");
		Session session = getSessionFactory().getCurrentSession();

		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		System.out.println(search);
		
		List<Contact> listContact = session.createCriteria(Contact.class).add(Restrictions.like("firstName", "%"+search+"%")).list();
		listContact.addAll(session.createCriteria(Contact.class).add(Restrictions.like("lastName", "%"+search+"%")).list());
		listContact.addAll(session.createCriteria(Contact.class).add(Restrictions.like("email", "%"+search+"%")).list());
		Set<Contact> setContact = new HashSet<>();
		setContact.addAll(listContact);
		listContact.clear();
		listContact.addAll(setContact);
		List<Contact> newListContact = new ArrayList<Contact>();
		for(Contact c: listContact){
			if(c.getCreator().getId()==acc.getId()) newListContact.add(c);
		}
		session.getTransaction().commit();
		return newListContact;
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
		
		Contact c= new Contact();
		c.setCreator(acc);
		@SuppressWarnings("unchecked")
		List<Contact> listContact = session.createCriteria(Contact.class)
			    .add( Example.create(c) )
			    .list();
		
		tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		tx.commit();
		return listContact;
	}

	@Override
	public void deleteContactByCreator(Account acc){
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		
		Contact contact= new Contact();
		contact.setCreator(acc);
		@SuppressWarnings("unchecked")
		List<Contact> listContact = session.createCriteria(Contact.class)
			    .add( Example.create(contact) )
			    .list();
		for(Contact c : listContact){
			deleteContact(c.getId());
		}
	}
	
	@Override
	public void addContactInGroup(long id_cont, long id_group){
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		Contact contact = (Contact) session.load(Contact.class, id_cont);
		ContactGroup cg = (ContactGroup) session.load(ContactGroup.class, id_group);
		Set<ContactGroup> set = contact.getBooks();
		set.add(cg);
		contact.setBooks(set);
		tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		tx.commit();
	}
}
