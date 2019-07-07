package com.luv2code.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;

public class MainDemoApp {

	public static void main(String[] args) {
		
		//read the spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		//get the bean from spring contatiner
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);

		//get call membership beN FROM SPRING CONTATINER
		MembershipDAO theMembershipDAO = context.getBean("membershipDAO", MembershipDAO.class);
		
		Account theAccount = new Account();
		theAccount.setName("Madhu");
		theAccount.setLevel("Platinum");
		//call the business method
		
		theAccountDAO.addAccount(theAccount, true);
		theAccountDAO.doWork();
		
		//do it again
		System.out.println("\n let's call it again business!!!!!\n");
		
		//call theaccountdao getter/setter method
		
		theAccountDAO.setName("Foobar");
		theAccountDAO.setServiceCode("SILVER");
		
		String name = theAccountDAO.getName();
		String serviceCode = theAccountDAO.getServiceCode();
		
		
		//call membership method
		theMembershipDAO.addSillyMember();
		theMembershipDAO.goToSleep();
		
		//close the context
		context.close();
	}

}
