package org.naamtamilar.magazine.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class User extends Auditable<String>{
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;
	@NotNull
	private String name;
	@NotNull
	private String email;
	@NotNull
	private String mobile;
	@NotNull
	private String password;
	private boolean active;
	@Column(columnDefinition="int default 0")
	private boolean isAccountVerified;
	@Column
	private boolean adminApproved =true;
	@Column(columnDefinition="int default 0")
	private int gender;@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dob;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "role_id")
	@ToString.Exclude
	private Role role;
	@JsonIgnore
	@OneToMany(targetEntity = Address.class,mappedBy = "user",cascade= CascadeType.ALL, fetch = FetchType.EAGER)
	@ToString.Exclude
	private Set<Address> addresses;

	public User(User user) {
		this.id=user.getId();
		this.active=user.isActive();
		this.email=user.getEmail();
		this.password=user.getPassword();
		this.name=user.getName();
		this.mobile=user.getMobile();
		this.dob=user.getDob();
		this.role=user.getRole();
	}

    public User() {

    }
}
