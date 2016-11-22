package tests;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import domain.dao.AccountDAO;
import domain.dao.AddressDAO;
import domain.dao.ContactDAO;
import domain.dao.ContactGroupDAO;
import domain.dao.EntrepriseDAO;
import domain.dao.PhoneNumberDAO;  

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations="classpath:applicationContext.xml")  
@TransactionConfiguration(transactionManager="transactionManager")  	
public class Test {

	@Autowired  
    AccountDAO accountDAO;  
	@Autowired  
    ContactDAO contactDAO;  
	@Autowired  
    AddressDAO addressDAO;  
	@Autowired  
	EntrepriseDAO entrepriseDAO;  
	@Autowired  
	ContactGroupDAO contactGroupDAO; 
	PhoneNumberDAO phoneNumberDAO;  
	
	@org.junit.Test
	public void testIntegrationHibernateEtSpring() {
		contactDAO.addContactInGroup(7, 3);
		contactDAO.addContactInGroup(7, 1);
	/*	Account acc = accountDAO.checkConnection("test", "test");
		contactDAO.getContactByCreator(acc);*/
		/*Contact c =contactDAO.getContactById(7);
		PhoneNumberDAO pnd = new PhoneNumberDAO();
		PhoneNumber pn = pnd.createPhoneNumber("tel", "06885563", c);
		/*Account acc = accountDAO.checkConnection("test", "test");
		contactGroupDAO.findAll(acc);
		*/System.out.println("Apres findAll");
		//contactDAO.deleteContact(3);
		//Contact c = contactDAO.getContactById(3);
		//contactDAO.deleteContact(2);
/*		Account a = accountDAO.createAccount("test", "test");
		Account a2 = accountDAO.createAccount("test2", "test2");
		System.out.println("============> create acc");
		Address add = addressDAO.createAddress("street", "city", "zip", "country");
		System.out.println("============> create add");
		Contact c = contactDAO.createContact("firstname", "lastname", "emailC", add, a);
		System.out.println("============> create contact");
		Address add2 = addressDAO.createAddress("street", "city", "zip", "country");
		System.out.println("============> create add");
		Contact c2 = contactDAO.createContact("firstname", "lastname", "emailC", add2, a);
		System.out.println("============> create contact");
		Address add3 = addressDAO.createAddress("street", "city", "zip", "country");
		System.out.println("============> create add");
		Contact c3 = contactDAO.createContact("firstname", "lastname", "emailC", add3, a);
		System.out.println("============> create contact");
		Address add4 = addressDAO.createAddress("street", "city", "zip", "country");
		System.out.println("============> create add");

		Contact c4 = contactDAO.createContact("firstname", "lastname", "emailC", add4, a2);
		System.out.println("============> create contact");
		
		ContactGroup cg = contactGroupDAO.createContactGroup("groupName", a);
		ContactGroup cg2 = contactGroupDAO.createContactGroup("groupName2", a);
		ContactGroup cg3 = contactGroupDAO.createContactGroup("groupName3", a);
		ContactGroup cg4 = contactGroupDAO.createContactGroup("groupName4", a2);
		
		System.out.println(contactDAO.getContactByCreator(a2).size());
		System.out.println(contactGroupDAO.findAll(a2).size());
		contactDAO.addContactInGroup(2, 2);
		contactDAO.addContactInGroup(3, 3);
		contactDAO.addContactInGroup(4, 3);
//		Contact c =contactDAO.getContactById(2);
//		PhoneNumberDAO pnd = new PhoneNumberDAO();
//		PhoneNumber pn = pnd.createPhoneNumber("fixe", "06885563", c);
//		
//		ContactGroup cg = contactGroupDAO.getContactGroupById(2);
//		contactGroupDAO.deleteContactGroup(2);
//		System.out.println(cg.getContacts().size());
//		
		//System.out.println(contactDAO.searchContact("st", a).size());
		//System.out.println(accountDAO.checkConnection("test", "test"));
		/*System.out.println("============> check connection");
		contactDAO.updateContact(c.getId(), "NewFirstName", "lastName", "emailC", add);
		System.out.println("============> update contact");
		List<Contact> listContact = contactDAO.searchContact("st");
		System.out.println("avant for");
		for(Contact cont: listContact) System.out.println(cont);*/
		
		//Account a = accountDAO.createAccount("test", "test");
		System.out.println("============> create acc");

	//	Address add = addressDAO.createAddress("street", "city", "zip", "country");
		/*Contact c = contactDAO.createContact("firstname", "lastname", "emailC", add, a);
		PhoneNumberDAO pn = new PhoneNumberDAO();
		pn.createPhoneNumber("", "", c);*/
		//Account a = accountDAO.createAccount("test", "test");
		System.out.println("============> create add");
		//Contact c = contactDAO.createContact("", "", "", add, a);
		/*if(c.getPhones()!=null){
			System.out.println("not null");
			System.out.println(c.getPhones().size()>0);
		}
		else System.out.println("null");*/
//		Contact c = contactDAO.getContactById(2);
//		PhoneNumberDAO pn = new PhoneNumberDAO();
//		pn.createPhoneNumber("lllll", "06969693", c);
//		pn.createPhoneNumber("lllpppppll", "06969693", c);
		/*PhoneNumberDAO pn = new PhoneNumberDAO();
		Contact c2 = contactDAO.getContactById(1);
		pn.createPhoneNumber("", "06886555", c2);
		System.out.println(c2.getPhones().size());*/
		/*contactDAO.updateContact(8, "firstName8788", "tName", "emailC", add);
		
		System.out.println(c2.getPhones());*/
	}

}
