package domain.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import domain.dao.interfaces.IEntrepriseDAO;
import domain.metier.Account;
import domain.metier.Address;
import domain.metier.Entreprise;
import util.HibernateUtil;

public class EntrepriseDAO extends HibernateDaoSupport implements IEntrepriseDAO {

	@Override
	public Entreprise createEntreprise(String firstName, String lastName, String email, long numSiret, Account creator) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Entreprise entre = new Entreprise();
		entre.setFirstName(firstName);
		entre.setLastName(lastName);
		entre.setEmail(email);
		entre.setNumSiret(numSiret);
		entre.setCreator(creator);
		
		Transaction tx = session.beginTransaction();
		session.persist(entre);
		tx.commit();
		
		System.out.println("createContactGroup réussi");
		return entre;
		
	}

	@Override
	public void updateEntreprise(long id, String firstName, String lastName, String emailC, Address add, long numSiret) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		
		Entreprise entre = (Entreprise) session.load(Entreprise.class, id);
		if(firstName!= null && !firstName.isEmpty()) entre.setFirstName(firstName);
		if(lastName!= null && !lastName.isEmpty()) entre.setLastName(lastName);
		if(emailC!= null && !emailC.isEmpty()) entre.setEmail(emailC);
		if(add!= null) entre.setAdd(add);
		entre.setNumSiret(numSiret);
		
		tx.commit();
	
		System.out.println("updateEntreprise réussi");
	}

	@Override
	public void deleteEntreprise(long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();

		Entreprise entreprise = (Entreprise) session.load(Entreprise.class, id);
		tx = session.getTransaction();
		if(!tx.isActive()) tx = session.beginTransaction();
		session.delete(entreprise);
		tx.commit();
		System.out.println("deletePhoneNumber réussi");
	}
}