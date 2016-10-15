package domain.services;

import domain.dao.AccountDAO;

public class AccountService {
	
	private AccountDAO dao;
	
	public AccountService(){
		dao = new AccountDAO();
	}
	
	public int createAccount(String login, String pwd){
		return dao.createAccount(login, pwd);
	}
	
	public boolean updateAccount(long id, String pwd){
		return dao.updateContact(id, pwd);
	}
	
	public boolean deleteAccount(long id){
		return dao.deleteAccount(id);
	}

	public boolean containsLogin(String login) {
		return dao.containsLogin(login);
	}

	public int checkConnection(String login, String password) {
		return dao.checkConnection(login, password);
	}

	public int getAccountId(String login) {
		return dao.findAccountIdByLogin(login);
	}
	
}
