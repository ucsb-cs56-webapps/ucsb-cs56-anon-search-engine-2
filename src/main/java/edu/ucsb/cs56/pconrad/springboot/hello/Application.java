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

    public static String bingKey;
    public static String googleApiKey;
    public static String googleEngineId;


    @Override
    public void run(String... args) throws Exception {
        bingKey = env.getRequiredProperty("BingSubscriptionKey");
        googleApiKey = env.getRequiredProperty("GoogleApiKey");
        googleEngineId = env.getRequiredProperty("GoogleEngineId");
        System.out.println("THIS IS IN APPLICATION. " + bingKey);
        System.out.println("THIS IS IN APPLICATION. " + googleApiKey);
        System.out.println("THIS IS IN APPLICATION. " + googleEngineId);
    }

    public static void main(String[] args) {
	SpringApplication.run(Application.class, args);
    }
}
