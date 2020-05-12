/**
 * 
 */
package com.guestbook.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
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

}
