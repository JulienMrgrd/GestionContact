package domain.dao.interfaces;

import domain.metier.Account;

public interface IAccountDAO {

	Account createAccount(String login, String password);

	void updateContact(long id, String pwd);
	
	void deleteAccount(long id);

	boolean containsLogin(String login);

	/**
	 * Verifie que les identifiants sont OK
	 * @param login
	 * @param password
	 * @return account, or null if there is no account for this login/password
	 */
	Account checkConnection(String login, String password);

	long findAccountIdByLogin(String login);

}