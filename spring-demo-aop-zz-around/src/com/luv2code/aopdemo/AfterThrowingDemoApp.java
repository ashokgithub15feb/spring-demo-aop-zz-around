package com.luv2code.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;

public class AfterThrowingDemoApp {

	public static void main(String[] args) {
		
		//read the spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
		
		//get the bean from spring contatiner
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);

		//call method to find account
		List<Account> findAccount = null;
		
		try
		{
			// add a boolean flag to simulate exception
			
			boolean tripWire = true;
			theAccountDAO.findAccount(tripWire);
		}catch (Exception e) {
			System.out.println("\n\nMain Program: Exception thrown: "+e.getMessage());
			
		}
		
		//display the account
		System.out.println("\n\nMain Program: AfterThrowingDemoApp");
		System.out.println("------");
		
		System.out.println(findAccount);
		
		System.out.println("\n");
		//close the context
		context.close();
	}

}
