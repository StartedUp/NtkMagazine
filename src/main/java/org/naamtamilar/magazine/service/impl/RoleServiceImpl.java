package org.naamtamilar.magazine.service.impl;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.naamtamilar.magazine.domain.Role;
import org.naamtamilar.magazine.repository.RoleRepository;
import org.naamtamilar.magazine.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class RoleServiceImpl implements IService<Role> {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Collection<Role> findAll() {
		return roleRepository.findAll();
	}

	@Override
	public Optional<Role> findById(Long id) {
		return roleRepository.findById(id);
	}

	@Override
	public Role saveOrUpdate(Role role) {
		return roleRepository.saveAndFlush(role);
	}

	@Override
	public String deleteById(Long id) {
		JSONObject jsonObject = new JSONObject();
		try {
			roleRepository.deleteById(id);
			jsonObject.put("message", "Role deleted successfully");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

    public Role findByName(String name) { return roleRepository.findByName(name);
    }
}
