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
		try{
			dao.deleteEntreprise(id);
		} catch (Exception e){}
	}

	public void updateEntreprise(long id, String firstName, String lastName, String emailC, Address add, long numSiret){
		try{
			dao.updateEntreprise(id, firstName, lastName, emailC, add, numSiret);
		} catch (Exception e){ }
	}

	public Entreprise createEntreprise(String firstName, String lastName, String email, Address add, long numSiret, Account creator){
		try{
			return dao.createEntreprise(firstName, lastName, email, add, numSiret, creator);
		} catch (Exception e){
			return null;
		}
	}

	public Entreprise getEntreprise(long id){
		try{
			return dao.getEntreprise(id);
		} catch (Exception e){
			return null;
		}
	}

	@Override
	public List<Entreprise> getEntrepriseByCreator(Account acc) {
		try{
			return dao.getEntrepriseByCreator(acc);
		} catch (Exception e){
			return null;
		}
	}

}
