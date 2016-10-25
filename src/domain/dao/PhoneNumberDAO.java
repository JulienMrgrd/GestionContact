package domain.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.dao.interfaces.IPhoneNumberDAO;
import domain.metier.Contact;
import domain.metier.PhoneNumber;
import util.HibernateUtil;

public class PhoneNumberDAO implements IPhoneNumberDAO {

	@Override
	public PhoneNumber createPhoneNumber(String phoneKind, String phoneNumber, Contact contact) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		PhoneNumber phoneNum = new PhoneNumber();
		phoneNum.setPhoneKind(phoneKind);
		phoneNum.setPhoneNumber(phoneNumber);
		phoneNum.setContact(contact);
		
		Transaction tx = session.beginTransaction();
		session.save(phoneNum);
		tx.commit();
		
		System.out.println("createPhoneNumber réussi");
		return phoneNum;
	}

	@Override
	public void updatePhoneNumber(long id, String phoneKind, String phoneNumber) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		PhoneNumber phoneNum = (PhoneNumber) session.load(PhoneNumber.class, id);
		phoneNum.setPhoneKind(phoneKind);
		phoneNum.setPhoneNumber(phoneNumber);
		
		Transaction tx = session.beginTransaction();
		session.save(phoneNum);
		tx.commit();
		
		System.out.println("updatePhoneNumber réussi");
	}

	@Override
	public void deletePhoneNumber(long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		PhoneNumber phoneNum = (PhoneNumber) session.load(PhoneNumber.class, id);
		Transaction tx = session.beginTransaction();
		session.delete(phoneNum);
		tx.commit();
		System.out.println("deletePhoneNumber réussi");
	}

}
