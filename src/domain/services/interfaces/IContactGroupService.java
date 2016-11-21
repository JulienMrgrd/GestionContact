package domain.services.interfaces;

import java.util.List;

import domain.metier.ContactGroup;

public interface IContactGroupService {

	ContactGroup createContactGroup(String nameGroupe);

	void updateContactGroup(long id, String nameGroupe);

	List<ContactGroup> findAll(long id);

}