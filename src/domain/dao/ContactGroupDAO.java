package domain.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.dao.interfaces.IContactGroupDAO;
import domain.metier.ContactGroup;
import util.HibernateUtil;

public class ContactGroupDAO implements IContactGroupDAO {

	@Override
	public ContactGroup createContactGroup(String groupName) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
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
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		Transaction tx = session.beginTransaction();
		ContactGroup cG = (ContactGroup) session.load(ContactGroup.class, id);
		cG.setGroupName(groupName);
		tx.commit();
		
		System.out.println("updateContactGroup réussi");
	}

}
