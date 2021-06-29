package org.naamtamilar.magazine.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Role extends Auditable<String>{
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;
	private String name;
	@OneToMany(targetEntity = User.class, mappedBy = "role", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@ToString.Exclude
	private Set<User> users;
}
