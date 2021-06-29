package org.naamtamilar.magazine.repository;

import org.naamtamilar.magazine.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);

	@Query("SELECT u from User u LEFT JOIN u.role r WHERE r.id = u.role.id")
	User findByEmailCustom(String email);
}