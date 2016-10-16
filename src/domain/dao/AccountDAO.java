package domain.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import domain.metier.Account;
import util.GestionContactUtils;
import util.HibernateUtil;

public class AccountDAO {

	public long createAccount(String login, String password) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Account acc = new Account();
		acc.setLogin(login);
		acc.setPwd(password);
		
		Transaction tx = session.beginTransaction();
		long id = (long) session.save(acc);
		tx.commit();
		System.out.println("createAccount réussi");
		return id;
	}
	
	public void deleteAccount(long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Account acc = (Account) session.load(Account.class, id);
		Transaction tx = session.beginTransaction();
		session.delete(acc);
		tx.commit();
		System.out.println("deleteAccount réussi");
	}

	public void updateContact(long id, String pwd) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		Account acc = (Account) session.load(Account.class, id);
		acc.setPwd(pwd);
		tx.commit();
		System.out.println("updateAccount réussi");
	}

	public boolean containsLogin(String login) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Account acc = (Account) session.createCriteria(Account.class)
				.add(Restrictions.eq("login", login) ).uniqueResult();
		return acc!=null;
	}

	/**
	 * Verifie que les identifiants sont OK
	 * @param login
	 * @param password
	 * @return
	 */
	public long checkConnection(String login, String password) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Account acc = (Account) session.createCriteria(Account.class)
				.add(Restrictions.eq("login", login) )
				.add(Restrictions.eq("pwd", password) ).uniqueResult();
		return acc==null ? GestionContactUtils.BAD_ID : acc.getId();
	}

	public long findAccountIdByLogin(String login) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Account acc = (Account) session.createCriteria(Account.class)
				.add(Restrictions.eq("login", login) ).uniqueResult();
		return acc==null ? GestionContactUtils.BAD_ID : acc.getId();
	}

}
