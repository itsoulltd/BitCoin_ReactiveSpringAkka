package com.itsoul.lab.bitcoin.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.itsoul.lab.bitcoin.app"
		,"com.itsoul.lab.bitcoin.spring.services"
		,"com.itsoul.lab.bitcoin.akka.cmd"})
//when wish to run SpringCMDLineUI add "com.itsoul.lab.bitcoin.spring.cmd"
public class SpringAkkaBitcoinApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAkkaBitcoinApplication.class, args);
	}
}
