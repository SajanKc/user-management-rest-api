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

/**
 * @Author Sajan K.C.
 * @Date January 7, 2022
 *
 */

@Entity
@Table
public class User {
	@Id
	@SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_sequence")
	private Integer userId;
	private String fullName;
	private String address;
	private String email;
	private LocalDate dob;
	@Transient
	private Integer age;

	public User() {

	}

	public User(String fullName, String address, String email, LocalDate dob) {
		this.fullName = fullName;
		this.address = address;
		this.email = email;
		this.dob = dob;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public Integer getAge() {
		return Period.between(this.dob, LocalDate.now()).getYears();
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", fullName=" + fullName + ", address=" + address + ", email=" + email
				+ ", dob=" + dob + ", age=" + this.getAge() + "]";
	}

}
