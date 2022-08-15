package fr.hey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
//@EntityScan("fr.hey.entity")
public class DemoSpringBootSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringBootSecurityApplication.class, args);
	}

}
