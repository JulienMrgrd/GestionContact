package domain.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import domain.dao.interfaces.IAccountDAO;
import domain.metier.Account;
import util.GestionContactUtils;

public class AccountDAO extends HibernateDaoSupport implements IAccountDAO {
	
	@Override
	public Account createAccount(String login, String password) {
		SessionFactory fact = getSessionFactory();
		fact = getHibernateTemplate().getSessionFactory();
		Session session = getSessionFactory().getCurrentSession();
		Account acc = new Account();
		acc.setLogin(login);
		acc.setPwd(password);
		
		Transaction tx = session.beginTransaction();
		session.save(acc);
		tx.commit();
		System.out.println("createAccount réussi");
		return acc;
		
	}
	
	@Override
	public void deleteAccount(long id) {
		Session session = getSessionFactory().getCurrentSession();
		Account acc = (Account) session.load(Account.class, id);
		Transaction tx = session.beginTransaction();
		session.delete(acc);
		tx.commit();
		System.out.println("deleteAccount réussi");
	}

	@Override
	public void updateContact(long id, String pwd) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		Account acc = (Account) session.load(Account.class, id);
		acc.setPwd(pwd);
		tx.commit();
		System.out.println("updateAccount réussi");
	}

	@Override
	public boolean containsLogin(String login) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		Account acc = (Account) session.createCriteria(Account.class)
				.add(Restrictions.eq("login", login) ).uniqueResult();
		tx.commit();
		System.out.println("containsLogin réussi");
		return acc!=null;
	}

	@Override
	public Account checkConnection(String login, String password) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		Account acc = (Account) session.createCriteria(Account.class)
				.add(Restrictions.eq("login", login) )
				.add(Restrictions.eq("pwd", password) ).uniqueResult();
		tx.commit();
		System.out.println("checkConnection réussi");
		return acc;
	}

	@Override
	public long findAccountIdByLogin(String login) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.beginTransaction();
		Account acc = (Account) session.createCriteria(Account.class)
				.add(Restrictions.eq("login", login) ).uniqueResult();
		tx.commit();
		System.out.println("findAccountIdByLogin réussi");
		return acc==null ? GestionContactUtils.BAD_ID : acc.getId();
	}

}
