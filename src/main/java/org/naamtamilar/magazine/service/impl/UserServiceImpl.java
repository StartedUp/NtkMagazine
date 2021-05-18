package org.naamtamilar.magazine.service.impl;

import java.util.Collection;
import java.util.Optional;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.naamtamilar.magazine.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.naamtamilar.magazine.domain.User;
import org.naamtamilar.magazine.repository.UserRepository;
import org.naamtamilar.magazine.service.IService;

@Service
public class UserServiceImpl implements IService<User> {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Collection<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public User saveOrUpdate(User user) {
		return userRepository.saveAndFlush(user);
	}

	@Override
	public String deleteById(Long id) {
		 var jsonObject = new JSONObject();
		try {
			userRepository.deleteById(id);
			jsonObject.put("message", "User deleted successfully");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public void populateDefaultValues(User user) {
		user.setActive(true);
		user.setAdminApproved(true);
		var role = new Role();
		role.setName("USER");
		user.setRole(role);
	}
}
