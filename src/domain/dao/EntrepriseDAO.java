package domain.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import domain.dao.interfaces.IEntrepriseDAO;
import domain.metier.Account;
import domain.metier.Address;
import domain.metier.Entreprise;

public class EntrepriseDAO extends HibernateDaoSupport implements IEntrepriseDAO {

	@Override
	public Entreprise createEntreprise(String firstName, String lastName, String email, Address add, long numSiret, Account creator) {
		Session session = getSessionFactory().getCurrentSession();
		Entreprise entre = new Entreprise();
		entre.setFirstName(firstName);
		entre.setLastName(lastName);
		entre.setEmail(email);
		entre.setNumSiret(numSiret);
		entre.setCreator(creator);
		entre.setAdd(add);
		
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		session.persist(entre);
		tx.commit();
		
		return entre;
		
	}

	@Override
	public void updateEntreprise(long id, String firstName, String lastName, String emailC, Address add, long numSiret) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		
		Entreprise entre = (Entreprise) session.load(Entreprise.class, id);
		if(firstName!= null && !firstName.isEmpty()) entre.setFirstName(firstName);
		if(lastName!= null && !lastName.isEmpty()) entre.setLastName(lastName);
		if(emailC!= null && !emailC.isEmpty()) entre.setEmail(emailC);
		if(add!= null) entre.setAdd(add);
		entre.setNumSiret(numSiret);
		
		tx.commit();
	}

	@Override
	public void deleteEntreprise(long id) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();

		Entreprise entreprise = (Entreprise) session.load(Entreprise.class, id);
		session.delete(entreprise);
		tx.commit();
	}

	@Override
	public Entreprise getEntreprise(long id) {
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();

		Entreprise entreprise = (Entreprise) session.load(Entreprise.class, id);
		tx.commit();
		return entreprise;
	}
}