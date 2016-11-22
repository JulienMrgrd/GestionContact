package domain.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import domain.dao.interfaces.IPhoneNumberDAO;
import domain.metier.Contact;
import domain.metier.PhoneNumber;

public class PhoneNumberDAO extends HibernateDaoSupport implements IPhoneNumberDAO {

	public PhoneNumberDAO(){ }
	
	@Override
	public PhoneNumber createPhoneNumber(String phoneKind, String phoneNumber, Contact contact) {
		Session session = getSessionFactory().getCurrentSession();
		PhoneNumber phoneNum = new PhoneNumber();
		phoneNum.setPhoneKind(phoneKind);
		phoneNum.setPhoneNumber(phoneNumber);
		phoneNum.setContact(contact);
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		session.save(phoneNum);
		tx.commit();
		return phoneNum;
	}

	@Override
	public PhoneNumber getPhoneNumberById(long id){
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		PhoneNumber phoneNumber = (PhoneNumber) session.load(PhoneNumber.class, id);
		return phoneNumber;
	}
	
	@Override
	public void updatePhoneNumber(long id, String phoneKind, String phoneNumber) {
		PhoneNumber phoneNum = this.getHibernateTemplate().load(PhoneNumber.class, id);
		phoneNum.setPhoneKind(phoneKind);
		phoneNum.setPhoneNumber(phoneNumber);
		
		this.getHibernateTemplate().save(phoneNum);
		
		System.out.println("updatePhoneNumber réussi");
	}

	@Override
	public void deletePhoneNumber(long id) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();

		PhoneNumber phoneNum = (PhoneNumber) session.load(PhoneNumber.class, id);
		tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		session.delete(phoneNum);
		tx.commit();
		System.out.println("deletePhoneNumber réussi");
	}

}
