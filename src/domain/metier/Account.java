package domain.metier;

public class Account {
	
	private long id_account;
	private String login;
	private String pwd;
	
	public Account() { }
	
	public Account(long id_account, String login, String pwd) {
		super();
		
	}
	
	public Account(Account copy){
		this(copy.getId(), copy.getLogin(), copy.getPwd());
	}

	public long getId() {
		return id_account;
	}

	public void setId(long id_account) {
		this.id_account = id_account;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}	
}
