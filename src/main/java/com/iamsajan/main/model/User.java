package com.iamsajan.main.model;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author Sajan K.C.
 * @Date January 7, 2022
 *
 */

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class User {
	@Id
	@SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_sequence")
	private Integer userId;
	private String accountNumber;
	private String fullName;
	private String address;
	private String email;
	private LocalDate dob;
	// Add image field
	@Transient
	private Integer age;

	public User(String accountNumber, String fullName, String address, String email, LocalDate dob) {
		this.accountNumber = accountNumber;
		this.fullName = fullName;
		this.address = address;
		this.email = email;
		this.dob = dob;
	}

	public Integer getAge() {
		return Period.between(this.dob, LocalDate.now()).getYears();
	}

}
