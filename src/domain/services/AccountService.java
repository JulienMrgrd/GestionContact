package domain.services;

import domain.dao.AccountDAO;

public class AccountService {
	
	private AccountDAO dao;
	
	public AccountService(){
		dao = new AccountDAO();
	}
	
	public boolean addAccount(String login, String pwd, String rePwd){
		return dao.addAccount(login, pwd, rePwd);
	}
	
	public boolean updateAccount(long id, String pwd, String rePwd){
		return dao.updateContact(id, pwd, rePwd);
	}
	
	public boolean deleteAccount(long id){
		return dao.deleteAccount(id);
	}
	
}
