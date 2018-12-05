package edu.ucsb.cs56.pconrad.springboot.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;



@SpringBootApplication
public class Application implements CommandLineRunner{

    @Autowired
    private Environment env;

    @Override
    public void run(String... args) throws Exception {

        System.out.println(env.getRequiredProperty("BingSubscriptionKey"));

    }

    public static void main(String[] args) {
	SpringApplication.run(Application.class, args);
    }
}
