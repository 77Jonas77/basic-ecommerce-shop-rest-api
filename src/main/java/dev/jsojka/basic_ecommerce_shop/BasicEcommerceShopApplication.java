package dev.jsojka.basic_ecommerce_shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//@SpringBootApplication
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class BasicEcommerceShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicEcommerceShopApplication.class, args);
	}

}
