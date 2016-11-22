package domain.dao.interfaces;

import java.util.List;

import domain.metier.Account;
import domain.metier.Address;
import domain.metier.Contact;
import domain.metier.PhoneNumber;

public interface IContactDAO {

	Contact createContact(String firstname, String lastname, String emailC, Address add, Account creator);

	boolean updateContact(long id, String firstName, String lastName, String emailC, Address add);

	void deleteContact(long id);

	Contact getContactById(long id);

	List<Contact> getContactByCreator(Account acc);

	void deleteContactByCreator(Account acc);

	void addPhonesInContact(long idContact, PhoneNumber pn);

	List<Contact> searchContact(String search, Account acc);

	void addContactInGroup(long id_cont, long id_group);

	Address getAddress(long id);
}