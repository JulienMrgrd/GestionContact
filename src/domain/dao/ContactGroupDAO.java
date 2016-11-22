package domain.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import domain.dao.interfaces.IContactGroupDAO;
import domain.metier.Account;
import domain.metier.ContactGroup;

public class ContactGroupDAO extends HibernateDaoSupport implements IContactGroupDAO {

	@Override
	public ContactGroup createContactGroup(String groupName, Account acc) {
		Session session = getSessionFactory().getCurrentSession();
		
		ContactGroup cG = new ContactGroup();
		cG.setGroupName(groupName);
		cG.setCreator(acc);
		
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		session.persist(cG);
		tx.commit();
		
		System.out.println("createContactGroup réussi");
		return cG;
		
	}

	@Override
	public boolean updateContactGroup(long id,String groupName) {
		try {
			Session session = getSessionFactory().getCurrentSession();
	
			Transaction tx = session.getTransaction();
			if(!tx.isActive()) tx = session.beginTransaction();
			ContactGroup cG = (ContactGroup) session.load(ContactGroup.class, id);
			if(groupName!=null && !groupName.isEmpty()) cG.setGroupName(groupName);
			tx.commit();
			return true;
		} catch(Exception e){
			return false;
		}
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
	public boolean deleteContactGroup(long id) {
		try{
			Session session = getSessionFactory().getCurrentSession();
			Transaction tx = session.getTransaction();
			if(!tx.isActive()) tx = session.beginTransaction();
	
			ContactGroup contactGroup = (ContactGroup) session.load(ContactGroup.class, id);
			tx = session.getTransaction();
			if(!tx.isActive()) tx = session.beginTransaction();
			session.delete(contactGroup);
			tx.commit();
			return true;
		} catch(Exception e){
			return false;
		}
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
	public List<ContactGroup> findAll(Account acc) {
		Session session = getSessionFactory().getCurrentSession();

		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		
		@SuppressWarnings("unchecked")
		List<ContactGroup> results = session.createQuery("select contactGroup from ContactGroup contactGroup").list();
		List<ContactGroup> listContact = new ArrayList<>();
		if(results!=null){
			for(ContactGroup cg: results){
				if(cg.getCreator().getId()==(acc.getId()))
				listContact.add(cg);
			}
		}
		tx.commit();
		return listContact;
	}
}
