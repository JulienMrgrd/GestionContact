package domain.dao.interfaces;

import java.util.List;

import domain.metier.Account;
import domain.metier.ContactGroup;

public interface IContactGroupDAO {

	void updateContactGroup(long id, String groupName);

	ContactGroup getContactGroupById(long id);

	void deleteContactGroup(long id);

	void deleteContactInGroup(long idGroup, long idContact);

	List<ContactGroup> findAll(Account acc);

	ContactGroup createContactGroup(String groupName, Account acc);
}