package domain.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.metier.Contact;
import domain.metier.PhoneNumber;
import util.HibernateUtil;

public class PhoneNumberDAO {

	public PhoneNumber createPhoneNumber(String phoneKind, String phoneNumber, Contact contact) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		PhoneNumber phoneNum = new PhoneNumber();
		phoneNum.setPhoneKind(phoneKind);
		phoneNum.setPhoneNumber(phoneNumber);
		phoneNum.setContact(contact);
		
		Transaction tx = session.beginTransaction();
		session.save(phoneNum);
		tx.commit();
		
		System.out.println("createAddress réussi");
		return phoneNum;
	}

	public boolean updatePhoneNumber(long id, String phoneKind, String phoneNumber) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deletePhoneNumber(long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
