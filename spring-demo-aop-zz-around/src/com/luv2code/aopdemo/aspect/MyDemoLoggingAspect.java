package com.luv2code.aopdemo.aspect;

import java.awt.Desktop.Action;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

	@Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
	public Object aroundGetFortune(ProceedingJoinPoint joinPoint) throws Throwable
	{
		//print out method we are advising on
		String method = joinPoint.getSignature().toShortString();
		
		System.out.println("\n=======>>>>> Execution @Around on method: "+method);
		
		//get begin timestamp
		long begin = System.currentTimeMillis();
		
		//now 
		Object result = joinPoint.proceed();
		
		long end = System.currentTimeMillis();
		
		long duration = end - begin;
		
		System.out.println("\n==========>>>>> Duration: "+duration/1000.0 +" seconds");
		return result;
	}

	@After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccount(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint)
	{
		String method = joinPoint.getSignature().toShortString();
		
		System.out.println("\n=======>>>>> Execution @After finally on method: "+method);
		
	}
	
	@AfterThrowing(
				pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccount(..))",
				throwing = "theEx"
			)
	public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable theEx)
	{
		String method = joinPoint.getSignature().toShortString();
		
		System.out.println("\n=======>>>>> Execution @AfterThrowing on method: "+method);
		
		//log the exception
		System.out.println("\n=======>>>>> The Exception is: "+theEx);
		
	}
	
	//add a new advice for @AfterReturning on the findAccount method
	@AfterReturning(
				pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccount(..))",
				returning = "result"
			)
	public void afterReturningFindAccountAdvice(JoinPoint joinPoint, List<Account> result)
	{
		//print out which method we are advising on
		String method = joinPoint.getSignature().toShortString();
		
		System.out.println("\n=======>>>>> Execution @AfterReturning on method: "+method);
		
		//print out the result of the method call
		System.out.println("\n=======>>>>> Result is: "+result);
		
		//let's post-process the data ---- let;s modify the data
		
		
		//convert the account name to uppercase
		convertAccountNamesToUppercase(result);
	
		System.out.println("\n=======>>>>> After convert Upper case Result is: "+result);
	}
	
	
	
	private void convertAccountNamesToUppercase(List<Account> result) {

		//loop through account
		
		List<String> collect = result.stream().map(theAccount -> theAccount.getName().toUpperCase()).collect(Collectors.toList());
		
		System.out.println(collect);
		
		
		result.get(0).setName(collect.get(0));
		result.get(1).setName(collect.get(1));
		result.get(2).setName(collect.get(2));
		
//		
//		for(Account theAccount : result)
//		{
//			String upperCase = theAccount.getName().toUpperCase();
//			
//			theAccount.setName(upperCase);
//		}
		//get uppercase version of name
		
		//update the name on the account
	}



	@Before("com.luv2code.aopdemo.aspect.util.LuvAopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint)
	{
		System.out.println("\n=======>>>>> Execution Logging Aspect on method");
		
		//display the method signature
		MethodSignature methodSig = (MethodSignature)theJoinPoint.getSignature();
		System.out.println("Method: "+methodSig);
		
		//display method argument
		
		Object[] args = theJoinPoint.getArgs();
		
		for(Object arg : args)
		{
			System.out.println(arg);
			
			if(arg instanceof Account)
			{
				//downcast and print Account specific stuff
				
				Account account = (Account) arg;
				
				System.out.println("Account Name: "+account.getName());
				System.out.println("Account Level: "+account.getLevel());
				
			}
			
		}
	}
	
	
}





















