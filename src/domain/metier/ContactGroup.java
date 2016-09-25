package domain.metier;

import java.util.List;

public class ContactGroup {
	
	private Integer groupId;
	private String groupName;
	private List<Contact> contacts;
	
	public ContactGroup() { }
	
	public ContactGroup(Integer groupId, String groupName,List<Contact> contacts) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
		this.setContacts(contacts);
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	

}
