/**
 * 
 */
package com.guestbook.app.entity;

import javax.persistence.Column;
/**
 * @author SV00494257
 *
 */
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "GUEST", uniqueConstraints = { @UniqueConstraint(name = "guest_uk", columnNames = "name") })
public class Guest {

	@Id
	@Column(name = "ID")
	private Long id = System.nanoTime();;

	@Column(name = "NAME", length = 30, nullable = false)
	private String name;

	@Column(name = "EMAIL", length = 30, nullable = false)
	private String email;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "STATE")
	private String state;

	@Column(name = "PHONE", length = 10, nullable = false)
	private String phone;

	@Column(name = "ENABLED")
	private String enabled;

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
