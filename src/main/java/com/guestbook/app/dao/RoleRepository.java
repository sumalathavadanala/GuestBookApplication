/**
 * 
 */
package com.guestbook.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.guestbook.app.entity.Role;

/**
 * @author SV00494257
 *
 */
@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Long> {
	@Query("select ur.appRole.roleName from UserRole ur  where ur.appUser.userId=(:roleId)")
	List<String> getRoleNames(@Param("roleId") Long userId);
}
