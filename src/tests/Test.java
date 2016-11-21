package tests;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import domain.dao.AccountDAO;
import domain.dao.AddressDAO;
import domain.dao.ContactDAO;
import domain.dao.EntrepriseDAO;
import domain.dao.PhoneNumberDAO;
import domain.metier.Account;
import domain.metier.Address;
import domain.metier.Contact;
import domain.metier.Entreprise;  

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
	
	@org.junit.Test
	public void testIntegrationHibernateEtSpring() {
		/*Account a = accountDAO.createAccount("test", "test");
		System.out.println("============> create acc");
		
		Contact c = contactDAO.createContact("firstname", "lastname", "emailC", add, a);
		System.out.println("============> create contact");
		//System.out.println(accountDAO.checkConnection("test", "test"));
		/*System.out.println("============> check connection");
		contactDAO.updateContact(c.getId(), "NewFirstName", "lastName", "emailC", add);
		System.out.println("============> update contact");
		List<Contact> listContact = contactDAO.searchContact("st");
		System.out.println("avant for");
		for(Contact cont: listContact) System.out.println(cont);*/
		
		//Account a = accountDAO.createAccount("test", "test");
		System.out.println("============> create acc");

		Address add = addressDAO.createAddress("street", "city", "zip", "country");
		/*Contact c = contactDAO.createContact("firstname", "lastname", "emailC", add, a);
		PhoneNumberDAO pn = new PhoneNumberDAO();
		pn.createPhoneNumber("", "", c);*/
		Account a = accountDAO.createAccount("test", "test");
		System.out.println("============> create add");
		Contact c = contactDAO.createContact("", "", "", add, a);
		if(c.getPhones()!=null){
			System.out.println("not null");
			System.out.println(c.getPhones().size()>0);
		}
		else System.out.println("null");
		/*PhoneNumberDAO pn = new PhoneNumberDAO();
		Contact c2 = contactDAO.getContactById(1);
		pn.createPhoneNumber("", "06886555", c2);
		System.out.println(c2.getPhones().size());*/
		/*contactDAO.updateContact(8, "firstName8788", "tName", "emailC", add);
		
		System.out.println(c2.getPhones());*/
	}

}
