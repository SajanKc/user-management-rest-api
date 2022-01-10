package com.iamsajan.main.model;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.iamsajan.main.repository.UserRepository;

/**
 * @Author Sajan K.C.
 * @Date January 7, 2022
 *
 */

@Configuration
public class UserConfiguration {

	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository) {
		return args -> {
			User sajan = new User("Sajan Kc", "Budhanilkantha", "sazankce@gmail.com",
					LocalDate.of(1996, Month.JULY, 1));
			User shanti = new User("Shanti Thapa", "Kapan", "shanti@gmail.com", LocalDate.of(1998, Month.JANUARY, 12));
			User amar = new User("Amar Shrestha", "Chabahil", "amar@gmail.com", LocalDate.of(1996, Month.AUGUST, 1));
			userRepository.saveAll(List.of(sajan, shanti, amar));
		};
	}
}
