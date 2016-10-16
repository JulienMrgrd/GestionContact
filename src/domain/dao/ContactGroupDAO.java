package domain.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.metier.ContactGroup;
import util.HibernateUtil;

public class ContactGroupDAO {

	public ContactGroup createContactGroupe(String groupName) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		ContactGroup cG = new ContactGroup();
		cG.setGroupName(groupName);
		
		Transaction tx = session.beginTransaction();
		session.persist(cG);
		tx.commit();
		
		System.out.println("createContactGroupe réussi");
		return cG;
		
	}

	public void updateContactGroupe(long id,String groupName) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		Transaction tx = session.beginTransaction();
		ContactGroup cG = (ContactGroup) session.load(ContactGroup.class, id);
		cG.setGroupName(groupName);
		tx.commit();
		
		System.out.println("updateContactGroupe réussi");
	}

}
