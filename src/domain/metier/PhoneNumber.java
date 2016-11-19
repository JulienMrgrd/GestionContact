package domain.metier;

import javax.persistence.Version;

public class PhoneNumber {
	
	private long id_phone;
	private String phoneKind;
	private String phoneNumber;
	private Contact contact;
	private int version;
	
	public PhoneNumber() { };
	
	public PhoneNumber(long id_phone, String phoneKind, String phoneNumber, Contact contact) {
		super();
		this.id_phone = id_phone;
		this.phoneKind = phoneKind;
		this.phoneNumber = phoneNumber;
		this.contact = contact;
	}

	public long getId() {
		return id_phone;
	}

	public void setId(long id_phone) {
		this.id_phone = id_phone;
	}

	public String getPhoneKind() {
		return phoneKind;
	}

	public void setPhoneKind(String phoneKind) {
		this.phoneKind = phoneKind;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
		
		if (!contact.getPhones().contains(this)) {
			contact.addPhoneNumber(this);
		}
	}
	
	@Version
	public long getVersion() {
	    return version;
	} 
}
