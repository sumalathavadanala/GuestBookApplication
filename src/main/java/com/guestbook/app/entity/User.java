/**
 * 
 */
package com.guestbook.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author SV00494257
 *
 */
@Entity
@Table(name = "USER", uniqueConstraints = {
		@UniqueConstraint(name = "USER_UK", columnNames = "User_Name") })

public class User {

	@Id
	@GeneratedValue
	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "user_name", length = 36, nullable = false)
	private String userName;

	@Column(name = "encryted_password", length = 128, nullable = false)
	private String password;

	@Column(name = "Enabled", length = 1, nullable = false)
	private boolean enabled;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
