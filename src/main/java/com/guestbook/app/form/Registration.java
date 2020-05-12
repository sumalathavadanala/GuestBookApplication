/**
 * 
 */
package com.guestbook.app.form;

import org.springframework.stereotype.Component;

/**
 * @author SV00494257
 *
 */
@Component
public class Registration {

	private String name;
	private String email;
	private String address;
	private String state;
	private String phone;

	public Registration() {

	}

	public Registration(String name, String email, String address, String state, String phone) {
		this.name = name;
		this.email = email;
		this.address = address;
		this.state = state;
		this.phone = phone;
	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
