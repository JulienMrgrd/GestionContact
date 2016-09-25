package domain.metier;

import java.util.List;

public class Contact {
	
	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private Address add;
	private List<ContactGroup> books;
	private List<PhoneNumber> phones;
	
	public Contact() { }
	
	public Contact(long id, String firstName, String lastName, String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	public Contact(long id, String firstName, String lastName, String email, Address add, List<ContactGroup> books,
						List<PhoneNumber> phones){
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.add = add;
		this.books = books;
		this.phones = phones;
	}
	
	public Contact(Contact copy){
		this(copy.getId(), copy.getFirstName(), copy.getLastName(), copy.getEmail(), 
				copy.getAdd(), copy.getBooks(), copy.getProfiles());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public List<ContactGroup> getBooks() {
		return books;
	}

	public void setBooks(List<ContactGroup> books) {
		this.books = books;
	}

	public List<PhoneNumber> getProfiles() {
		return phones;
	}

	public void setPhones(List<PhoneNumber> phones) {
		this.phones = phones;
	}
	
	
}
