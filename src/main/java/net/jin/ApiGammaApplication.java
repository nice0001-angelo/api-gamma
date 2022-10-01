package net.jin;

import java.util.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiGammaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGammaApplication.class, args);
		
		System.out.println("Press 'Enter' to Terminate");
		new Scanner(System.in).nextLine();
		System.out.println("Exiting");
		System.exit(0);
	}

}
