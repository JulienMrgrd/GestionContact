package domain.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.metier.ContactGroup;
import util.HibernateUtil;

public class ContactGroupDAO {

	public boolean createContactGroupe(String groupName) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		ContactGroup cG = new ContactGroup();
		cG.setGroupName(groupName);
		
		Transaction tx = session.beginTransaction();
		session.persist(cG);
		tx.commit();
		
		System.out.println("createContactGroupe réussi");
		return true;
		
	}

	public boolean setContactGroupe(String nameGroupe) {
		// TODO Auto-generated method stub
		return false;
	}

}
