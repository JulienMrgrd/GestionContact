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
		try{
			return dao.createAccount(login, pwd);
		} catch (Exception e){
			return null;
		}
	}
	
	@Override
	public void updateAccount(long id, String pwd){
		try{
			dao.updateContact(id, pwd);
		} catch (Exception e){	}
	}
	
	@Override
	public void deleteAccount(long id){
		try{
			dao.deleteAccount(id);
		} catch (Exception e){}
	}

	@Override
	public boolean containsLogin(String login) {
		try{
			return dao.containsLogin(login);
		} catch (Exception e){
			return false;
		}
	}

	@Override
	public Account checkConnection(String login, String password) {
		try{
			return dao.checkConnection(login, password);
		} catch (Exception e){
			return null;
		}
	}

	@Override
	public long getAccountId(String login) {
		try{
			return dao.findAccountIdByLogin(login);
		} catch (Exception e){
			return 0;
		}
	}
	
}
