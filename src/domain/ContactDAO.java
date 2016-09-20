package domain;

public class ContactDAO {
	
	public void addContact(Integer id, String firstname, String lastname, String emailC){
		System.out.println("addContact réussi");
	}
	
	public void updateContact(Integer id, String firstname, String lastname, String emailC){
		System.out.println("updateContact réussi");
	}
	
	public void deleteContact(Integer id){
		System.out.println("deleteContact réussi");
	}
	
	public void searchContact(Integer id){
		System.out.println("searchContact réussi");
	}

}
