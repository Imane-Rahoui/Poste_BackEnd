package com.pfa.jee.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AgencyRequest {

	@NotBlank(message = "the Name field must not be empty")
	@Size(min = 3, message = "the Name field must have at least 3 characters")
	private String name;

	@NotBlank(message = "the Email field must not be empty")
	@Email(message = "the Email field must respect the Email format")
	private String email;

	@NotBlank(message = "the Password field must not be empty")
	@Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$", message = "Password must contain upper and lower case letters, numbers and be at least 8 characters")
	private String password;

	@NotBlank(message = "the Mobile field must not be empty")
	@Size(min = 10, message = "the Mobile field must have at least 10 numbers")
	private String mobile;

	@NotBlank(message = "the City field must not be empty")
	private String city;

	@NotBlank(message = "the Country field must not be empty")
	private String country;
	
	@NotBlank(message = "the Street field must not be empty")
	private String street;
	
	@NotBlank(message = "the Postal field must not be empty")
	private String postal;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

}
