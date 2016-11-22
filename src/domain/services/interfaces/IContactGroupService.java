package domain.services.interfaces;

import java.util.List;

import domain.metier.Account;
import domain.metier.ContactGroup;

public interface IContactGroupService {

	ContactGroup createContactGroup(String nameGroupe, Account a);

	boolean updateContactGroup(long id, String nameGroupe);

	List<ContactGroup> findAll(Account a);

	void deleteContactGroup(long id);

}