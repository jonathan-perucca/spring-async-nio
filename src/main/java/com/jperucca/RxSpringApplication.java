package com.jperucca;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

import static com.jperucca.User.Visibility.PRIVATE;
import static com.jperucca.User.Visibility.PUBLIC;
import static java.util.Arrays.asList;

@SpringBootApplication
public class RxSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(RxSpringApplication.class, args);
	}

	@Autowired
	UserRepository userRepository;

	@Bean
	public InitializingBean startup() {
		return () -> users.stream()
						  .map(userRepository::save)
						  .forEach(user -> System.out.println("Persisting user " + user.getName()));
	}

	private List<User> users = asList(
			User.builder().name("Jame").age(15).visibility(PRIVATE).build(),
			User.builder().name("Walt").age(24).visibility(PUBLIC).build(),
			User.builder().name("Ben").age(65).visibility(PUBLIC).build(),
			User.builder().name("July").age(22).visibility(PUBLIC).build(),
			User.builder().name("Harry").age(34).visibility(PRIVATE).build(),
			User.builder().name("Barry").age(24).visibility(PUBLIC).build(),
			User.builder().name("Mocky").age(25).visibility(PUBLIC).build(),
			User.builder().name("Bim").age(45).visibility(PUBLIC).build()
	);
}
