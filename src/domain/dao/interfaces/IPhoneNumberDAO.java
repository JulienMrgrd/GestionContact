package domain.dao.interfaces;

import domain.metier.Contact;
import domain.metier.PhoneNumber;

public interface IPhoneNumberDAO {

	PhoneNumber createPhoneNumber(String phoneKind, String phoneNumber, Contact contact);

	void updatePhoneNumber(long id, String phoneKind, String phoneNumber, Contact contact);

	void deletePhoneNumber(long id);

	PhoneNumber getPhoneNumberById(long id);

}