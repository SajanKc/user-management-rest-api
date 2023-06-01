package com.iamsajan.main.model;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.iamsajan.main.repository.UserRepository;

/**
 * @Author Sajan K.C.
 * @Date January 7, 2022
 */

@Configuration
public class UserConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            User sajan = new User("1234567890", "Sajan Kc", "Budhanilkantha", "sazankce@gmail.com",
                    LocalDate.of(1996, Month.JULY, 1));
            User ashmita = new User("0987654321", "Ashmita Thapa", "Kapan", "asmita@gmail.com", LocalDate.of(1998, Month.JANUARY, 12));
            userRepository.saveAll(Arrays.asList(sajan, ashmita));
        };
    }
}
