package org.naamtamilar.magazine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.naamtamilar.magazine.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}