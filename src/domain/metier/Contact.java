package domain.metier;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Version;

public class Contact {
	
	private long id_contact;
	private String firstName;
	private String lastName;
	private String email;
	private Address add;
	private Set<ContactGroup> books;
	private Set<PhoneNumber> phones;
	private Account creator;
	private int version;
	
	public Contact() { 
		this.books = new HashSet<>();
		this.phones = new HashSet<>();
	}
	
	public Contact(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.books = new HashSet<>();
		this.phones = new HashSet<>();
	}
	
	public Contact(long id_contact, String firstName, String lastName, String email) {
		super();
		this.id_contact = id_contact;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.books = new HashSet<>();
		this.phones = new HashSet<>();
	}
	
	public Contact(long id_contact, String firstName, String lastName, String email, Address add, Set<ContactGroup> books,
						Set<PhoneNumber> phones){
		super();
		this.id_contact = id_contact;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.add = add;
		this.books = books;
		this.phones = phones;
	}
	
	public Contact(Contact copy){
		this(copy.getId(), copy.getFirstName(), copy.getLastName(), copy.getEmail(), 
				copy.getAdd(), copy.getBooks(), copy.getPhones());
	}

	public long getId() {
		return id_contact;
	}

	public void setId(long id_contact) {
		this.id_contact = id_contact;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAdd() {
		return add;
	}

	public void setAdd(Address add) {
		this.add = add;
	}

	public Set<ContactGroup> getBooks() {
		return books;
	}
	
	public void setBooks(Set<ContactGroup> books) {
		this.books = books;
	}

	public void addContactGroup(ContactGroup grp){
		this.books.add(grp);
		
		if(!grp.getContacts().contains(this)){
			grp.addContact(this);
		}
	}
	
	public void removeContactGroup(ContactGroup grp){
		this.books.remove(grp);
		
		if(grp.getContacts().contains(this)){
			grp.removeContact(this);
		}
	}

	public Set<PhoneNumber> getPhones() {
		return phones;
	}

	public void setPhones(Set<PhoneNumber> phones) {
		this.phones = phones;
	}

	public void addPhoneNumber(PhoneNumber phoneNumber) {
		this.phones.add(phoneNumber);
		
		if (phoneNumber !=null && 
				(phoneNumber.getContact() == null||!phoneNumber.getContact().equals(this))){
			phoneNumber.setContact(this);
		}
	}
	
	public void removePhoneNumber(PhoneNumber phoneNumber) {
		this.phones.remove(phoneNumber);
		
		if (phoneNumber !=null && phoneNumber.getContact() != null 
				&& phoneNumber.getContact().equals(this)){
			phoneNumber.setContact(null);
			//TODO: voir avec Reda
		}
	}
	
	public void removeAllPhoneNumber() {
		phones.clear();
	}

	public Account getCreator() {
		return creator;
	}

	public void setCreator(Account creator) {
		this.creator = creator;
	}
	
	@Version
	public long getVersion() {
	    return version;
	} 
}
