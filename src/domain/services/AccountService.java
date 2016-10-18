package domain.services;

import domain.dao.AccountDAO;
import domain.metier.Account;

public class AccountService {
	
	private AccountDAO dao;
	
	public AccountService(){
		dao = new AccountDAO();
	}
	
	public Account createAccount(String login, String pwd){
		return dao.createAccount(login, pwd);
	}
	
	public void updateAccount(long id, String pwd){
		dao.updateContact(id, pwd);
	}
	
	public void deleteAccount(long id){
		dao.deleteAccount(id);
	}

	public boolean containsLogin(String login) {
		return dao.containsLogin(login);
	}

	public Account checkConnection(String login, String password) {
		return dao.checkConnection(login, password);
	}

	public long getAccountId(String login) {
		return dao.findAccountIdByLogin(login);
	}
	
}
