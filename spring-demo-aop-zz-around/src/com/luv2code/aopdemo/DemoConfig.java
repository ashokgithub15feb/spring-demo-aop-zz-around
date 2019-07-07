package com.luv2code.aopdemo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration //spring pure java config
@EnableAspectJAutoProxy //spring AOP proxy support
@ComponentScan("com.luv2code.aopdemo") //component scan for componnet and aspects recurse package
public class DemoConfig {

}
