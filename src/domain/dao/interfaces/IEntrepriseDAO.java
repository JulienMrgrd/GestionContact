package domain.dao.interfaces;

import domain.metier.Account;
import domain.metier.Address;
import domain.metier.Entreprise;

public interface IEntrepriseDAO {

	void deleteEntreprise(long id);

	void updateEntreprise(long id, String firstName, String lastName, String emailC, Address add, long numSiret);

	Entreprise createEntreprise(String firstName, String lastName, String email, Address add, long numSiret, Account creator);

	Entreprise getEntreprise(long id);

}