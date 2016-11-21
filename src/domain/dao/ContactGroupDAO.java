package domain.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import domain.dao.interfaces.IContactGroupDAO;
import domain.metier.Account;
import domain.metier.Contact;
import domain.metier.ContactGroup;

public class ContactGroupDAO extends HibernateDaoSupport implements IContactGroupDAO {

	@Override
	public ContactGroup createContactGroup(String groupName) {
		Session session = getSessionFactory().getCurrentSession();
		
		ContactGroup cG = new ContactGroup();
		cG.setGroupName(groupName);
		
		Transaction tx = session.beginTransaction();
		session.persist(cG);
		tx.commit();
		
		System.out.println("createContactGroup réussi");
		return cG;
		
	}

	@Override
	public void updateContactGroup(long id,String groupName) {
		Session session = getSessionFactory().getCurrentSession();

		Transaction tx = session.beginTransaction();
		ContactGroup cG = (ContactGroup) session.load(ContactGroup.class, id);
		cG.setGroupName(groupName);
		tx.commit();
		
		System.out.println("updateContactGroup réussi");
	}

	@Override
	public ContactGroup getContactGroupById(long id){
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		ContactGroup contact = (ContactGroup) session.load(ContactGroup.class, id);
		return contact;
	}

	@Override
	public void deleteContactGroup(long id) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();

		ContactGroup contactGroup = (ContactGroup) session.load(ContactGroup.class, id);
		tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		session.delete(contactGroup);
		tx.commit();
		System.out.println("deletePhoneNumber réussi");
	}

	@Override
	public void deleteContactInGroup(long idGroup, long idContact) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();

		ContactGroup contactGroup = (ContactGroup) session.load(ContactGroup.class, idGroup);
		tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		session.delete(contactGroup);
		tx.commit();
		System.out.println("deletePhoneNumber réussi");
	}

	@Override
	public List<ContactGroup> findAll(long id) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		
		Account acc = new Account();
		acc.setId(id);
		Contact contact= new Contact();
		contact.setCreator(acc);
		
		@SuppressWarnings("unchecked")
		List<Contact> listContact = session.createCriteria(Contact.class)
			    .add( Example.create(contact) )
			    .list();
		List<ContactGroup> listContactGroup = new ArrayList<>();
		for(Contact c : listContact){
			if(c.getBooks()!=null) listContactGroup.addAll(c.getBooks());
		}
		Set<ContactGroup> setContactGroup = new HashSet<ContactGroup>(listContactGroup);
		listContactGroup.clear();
		listContactGroup.addAll(setContactGroup);
		return listContactGroup;
	}
}
