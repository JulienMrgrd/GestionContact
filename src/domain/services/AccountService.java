package domain.services;

import domain.dao.interfaces.IAccountDAO;
import domain.metier.Account;
import domain.services.interfaces.IAccountService;

public class AccountService implements IAccountService {
	
	private IAccountDAO dao;
	
	public AccountService(IAccountDAO dao){
		this.dao = dao;
	}
	
	@Override
	public Account createAccount(String login, String pwd){
		return dao.createAccount(login, pwd);
	}
	
	@Override
	public void updateAccount(long id, String pwd){
		dao.updateContact(id, pwd);
	}
	
	@Override
	public void deleteAccount(long id){
		dao.deleteAccount(id);
	}

	@Override
	public boolean containsLogin(String login) {
		return dao.containsLogin(login);
	}

	@Override
	public Account checkConnection(String login, String password) {
		return dao.checkConnection(login, password);
	}

	@Override
	public long getAccountId(String login) {
		return dao.findAccountIdByLogin(login);
	}
	
}
