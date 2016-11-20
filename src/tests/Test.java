package tests;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import domain.dao.AccountDAO;
import domain.dao.AddressDAO;
import domain.dao.ContactDAO;
import domain.metier.Account;
import domain.metier.Address;  

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations="classpath:applicationContext.xml")  
@TransactionConfiguration(transactionManager="transactionManager")  	
public class Test {

	@Autowired  
    private AccountDAO accountDAO;  
	@Autowired  
    private ContactDAO contactDAO;  
	@Autowired  
    private AddressDAO addressDAO;  
	
	@org.junit.Test
	public void test() {
		Account a = accountDAO.createAccount("test", "test");
		Address add = addressDAO.createAddress("street", "city", "zip", "country");
		contactDAO.createContact("firstname", "lastname", "emailC", add, a);
		System.out.println(accountDAO.checkConnection("test", "test"));
	}

}
