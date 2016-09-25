package domain.dao;

import java.util.ArrayList;
import java.util.List;

import domain.metier.Contact;

public class ContactDAO{
	
	public ContactDAO(){
		
	}
	
	public boolean addContact(String firstname, String lastname, String emailC){
		System.out.println("addContact réussi");
		return true;
	}
	
	public boolean updateContact(long id, String firstname, String lastname, String emailC){
		System.out.println("updateContact réussi");
		return true;
	}
	
	public boolean deleteContact(long id){
		System.out.println("deleteContact réussi");
		return true;
	}
	
	public List<Contact> searchContact(String firstname, String lastname, String emailC) {
		// Recherche avec tous les paramètres renseignés
		System.out.println("searchContact réussi");
		
		List<Contact> fakeList = new ArrayList<>();
		fakeList.add(new Contact(5, "julien", "m", "yopmail.com"));
		fakeList.add(new Contact(6, "felix", "l", "yopmail2.com"));
		return fakeList;
	}

}
