package domain.services.interfaces;

import domain.metier.Contact;
import domain.metier.PhoneNumber;

public interface IPhoneNumberService {

	PhoneNumber createPhoneNumber(String phoneKind, String phoneNumber, Contact contact);

	void updatePhoneNumber(long id, String phoneKind, String phoneNumber);

	void deletePhoneNumber(long id);

}