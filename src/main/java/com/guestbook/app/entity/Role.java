/**
 * 
 */
package com.guestbook.app.entity;

/**
 * @author SV00494257
 *
 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "ROLE", uniqueConstraints = { @UniqueConstraint(name = "role_uk", columnNames = "role_name") })
public class Role {

	@Id
	@GeneratedValue
	@Column(name = "role_id", nullable = false)
	private Long roleId;

	@Column(name = "role_name", length = 30, nullable = false)
	private String roleName;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
