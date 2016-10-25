package domain.services.interfaces;

import domain.metier.ContactGroup;

public interface IContactGroupService {

	ContactGroup createContactGroup(String nameGroupe);

	void updateContactGroup(long id, String nameGroupe);

}