package domain.dao.interfaces;

import java.util.List;

import domain.metier.Account;
import domain.metier.Address;
import domain.metier.Contact;

public interface IContactDAO {

	Contact createContact(String firstname, String lastname, String emailC, Address add, Account creator);

	boolean updateContact(long id, String firstName, String lastName, String emailC, Address add);

	void deleteContact(long id);

	List<Contact> searchContact(String firstname, String lastname, String emailC);

	Contact getContactById(long id);

	List<Contact> getContactByCreator(Account acc);

	void deleteContactByCreator(Account acc);

}