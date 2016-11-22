package domain.services;

import java.util.List;

import domain.dao.interfaces.IEntrepriseDAO;
import domain.metier.Account;
import domain.metier.Address;
import domain.metier.Entreprise;
import domain.services.interfaces.IEntrepriseService;

public class EntrepriseService implements IEntrepriseService {
	
	IEntrepriseDAO dao;
	
	public EntrepriseService(IEntrepriseDAO dao){
		this.dao = dao;
	}
	
	public void deleteEntreprise(long id){
		dao.deleteEntreprise(id);
	}

	public void updateEntreprise(long id, String firstName, String lastName, String emailC, Address add, long numSiret){
		dao.updateEntreprise(id, firstName, lastName, emailC, add, numSiret);
	}

	public Entreprise createEntreprise(String firstName, String lastName, String email, Address add, long numSiret, Account creator){
		return dao.createEntreprise(firstName, lastName, email, add, numSiret, creator);
	}

	public Entreprise getEntreprise(long id){
		return dao.getEntreprise(id);
	}

	@Override
	public List<Entreprise> getEntrepriseByCreator(Account acc) {
		return dao.getEntrepriseByCreator(acc);
	}

}
