/**
 * 
 */
package com.guestbook.app.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.guestbook.app.dao.RoleRepository;
import com.guestbook.app.dao.UserRepository;

/**
 * @author SV00494257
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private UserRepository userRep;

	@Autowired
	private RoleRepository roleRep;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		logger.info("entering into loadUserByUsername()");
		com.guestbook.app.entity.User user = this.userRep.findByUserName(userName);
		if (user == null) {
			logger.info("user not found! " + userName);
			throw new UsernameNotFoundException("User " + userName + "was not found");
		}
		List<String> roleNames = this.roleRep.getRoleNames(user.getUserId());
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		if (roleNames != null) {
			for (String role : roleNames) {
				GrantedAuthority authority = new SimpleGrantedAuthority(role);
				grantList.add(authority);
			}
		}
		UserDetails userDetails = (UserDetails) new User(user.getUserName(), user.getPassword(), grantList);
		return userDetails;
	}
}
