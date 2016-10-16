package domain.metier;
import java.util.Set;

public class Account {
	
	private long id_account;
	private String login;
	private String pwd;
	private Set<Contact> contacts;
	
	public Account() { }
	
	public Account(long id_account, String login, String pwd) {
		super();
		this.id_account=id_account;
		this.login=login;
		this.pwd=pwd;
		
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

	public Set<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}
	
	public void addContact(Contact contact){
		if(contact!=null){
			contacts.add(contact);
			contact.setCreator(this);
		}
		
	}
}
