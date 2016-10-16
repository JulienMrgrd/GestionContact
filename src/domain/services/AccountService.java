package domain.services;

import domain.dao.AccountDAO;

public class AccountService {
	
	private AccountDAO dao;
	
	public AccountService(){
		dao = new AccountDAO();
	}
	
	public long createAccount(String login, String pwd){
		return dao.createAccount(login, pwd);
	}
	
	public boolean updateAccount(long id, String pwd){
		return dao.updateContact(id, pwd);
	}
	
	public void deleteAccount(long id){
		dao.deleteAccount(id);
	}

	public boolean containsLogin(String login) {
		return dao.containsLogin(login);
	}

	public long checkConnection(String login, String password) {
		return dao.checkConnection(login, password);
	}

	public long getAccountId(String login) {
		return dao.findAccountIdByLogin(login);
	}
	
}
