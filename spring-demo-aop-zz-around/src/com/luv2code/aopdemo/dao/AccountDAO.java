package com.luv2code.aopdemo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Component
public class AccountDAO {

	private String name;
	private String serviceCode;
	
	//add a new method: findAccount()
	
	public List<Account> findAccount(boolean tripWire)
	{
		//for academic purpose ... simulate an exception
		
		if(tripWire)
		{
			throw new RuntimeException("No soup for you!!!!!!!!!!!");
		}
		List<Account> myAccounts = new ArrayList<Account>();
		
		//create  sample account
		Account account1 = new Account("John", "Silver");
		Account account2 = new Account("Madhu", "Platinum");
		Account account3 = new Account("Luca", "Gold");
		
		//add them to our account list
		myAccounts.add(account1);
		myAccounts.add(account2);
		myAccounts.add(account3);
		
		return myAccounts; 
	}
	
	public List<Account> findAccount()
	{
		List<Account> myAccounts = new ArrayList<Account>();
		
		//create  sample account
		Account account1 = new Account("John", "Silver");
		Account account2 = new Account("Madhu", "Platinum");
		Account account3 = new Account("Luca", "Gold");
		
		//add them to our account list
		myAccounts.add(account1);
		myAccounts.add(account2);
		myAccounts.add(account3);
		
		return myAccounts; 
	}
	
	public void addAccount(Account theAccount, boolean vipFlag)
	{
		System.out.println(getClass()+" : Doing my DB work: Adding an Account");
	}
	
	public void doWork()
	{
		System.out.println(getClass()+": doing work");
	}

	public String getName() {
		System.out.println(getClass()+": in getName");
		return name;
	}

	public void setName(String name) {
		System.out.println(getClass()+": in setName");
		this.name = name;
	}

	public String getServiceCode() {
		System.out.println(getClass()+": in getServiceCode");
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		System.out.println(getClass()+": in setServiceCode");
		this.serviceCode = serviceCode;
	}
	
	
}
