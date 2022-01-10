package com.iamsajan.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iamsajan.main.model.User;

/**
 * @Author Sajan K.C.
 * @Date January 7, 2022
 *
 */

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findUserByEmail(String email);

}
