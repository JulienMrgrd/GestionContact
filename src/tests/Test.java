package tests;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import domain.dao.AccountDAO;  

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations="classpath:applicationContext.xml")  
@TransactionConfiguration(transactionManager="transactionManager")  	
public class Test {

	@Autowired  
    private AccountDAO accountDAO;  
	
	@org.junit.Test
	public void test() {
		accountDAO.createAccount("test", "test");
	}

}
