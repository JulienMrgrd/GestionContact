package domain.metier;

import java.util.Set;

import javax.persistence.Version;

public class ContactGroup {
	
	private long id_group;
	private String groupName;
	private Set<Contact> contacts;
	private Account creator;
	private int version;
	
	public ContactGroup() { }
	
	public ContactGroup(long id_group, String groupName,Set<Contact> contacts) {
		super();
		this.id_group = id_group;
		this.groupName = groupName;
		this.setContacts(contacts);
	}

	public long getId() {
		return id_group;
	}

	public void setId(long id_group) {
		this.id_group = id_group;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Set<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}

	public void addContact(Contact contact) {
		this.contacts.add(contact);
		
		if(!contact.getBooks().contains(this)){
			contact.addContactGroup(this);
		}
	}

	public void removeContact(Contact contact) {
		this.contacts.remove(contact);
		
		if(contact.getBooks().contains(this)){
			contact.removeContactGroup(this);
		}
	}
	
	public long getId_group() {
		return id_group;
	}

	public void setId_group(long id_group) {
		this.id_group = id_group;
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
