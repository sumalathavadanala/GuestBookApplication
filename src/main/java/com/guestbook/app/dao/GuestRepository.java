/**
 * 
 */
package com.guestbook.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.guestbook.app.entity.Guest;


/**
 * @author SV00494257
 *
 */
@Repository
@Transactional
public interface GuestRepository extends JpaRepository<Guest, Long>{
	
	Guest findByName(String name);
	
	@Transactional
	@Modifying
	@Query("update Guest g set g.enabled='enabled' where g.id=(:id)")
	void editRecordById(@Param("id") Long id);
	
	List<Guest> findByEnabledNull();
	
	List<Guest> findByEnabledNotNull();	
	
}
