package domain.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import domain.dao.interfaces.IAccountDAO;
import domain.dao.interfaces.IContactDAO;
import domain.metier.Account;
import domain.metier.Address;
import util.GestionContactUtils;

public class AccountDAO extends HibernateDaoSupport implements IAccountDAO {

	public AccountDAO(){}
	
	@Override
	public Account createAccount(String login, String password) {
		Session session = getSessionFactory().getCurrentSession();
		Account acc = new Account();
		acc.setLogin(login);
		acc.setPwd(password);
		
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		session.save(acc);
		tx.commit();
		System.out.println("createAccount réussi");
		return acc;
		
	}
	
	@Override
	public void deleteAccount(long id) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();

		Account acc = (Account) session.load(Account.class, id);
		IContactDAO contactDAO = new ContactDAO();
		contactDAO.deleteContactByCreator(acc);
		
		//On relance la session car deleteContactByCreator l'a fermé 
		session = getSessionFactory().getCurrentSession();
		tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		session.delete(acc);
		tx.commit();
		System.out.println("deleteAccount réussi");
	}

	@Override
	public void updateContact(long id, String pwd) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		Account acc = (Account) session.load(Account.class, id);
		acc.setPwd(pwd);
		tx.commit();
		System.out.println("updateAccount réussi");
	}

	@Override
	public boolean containsLogin(String login) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		Account acc = (Account) session.createCriteria(Account.class)
				.add(Restrictions.eq("login", login) ).uniqueResult();
		tx.commit();
		System.out.println("containsLogin réussi");
		return acc!=null;
	}

	@Override
	public Account checkConnection(String login, String password) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		
		String query = "from Account as account where account.login = :login and account.pwd = :password";
		Account acc = (Account) session.createQuery(query).setString("login", login).setString("password", password).uniqueResult();
		tx.commit();
		System.out.println("checkConnection réussi");
		//retourne null si pas de compte trouvé avec le login et le password
		return acc;
	}

	@Override
	public long findAccountIdByLogin(String login) {
		Session session = getSessionFactory().getCurrentSession();
		
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		
		Account acc = (Account) session.createCriteria(Account.class)
				.add(Restrictions.eq("login", login) ).uniqueResult();
		tx.commit();
		System.out.println("findAccountIdByLogin réussi");
		return acc==null ? GestionContactUtils.BAD_ID : acc.getId();
	}
}
