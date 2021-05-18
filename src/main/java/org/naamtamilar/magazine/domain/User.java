package org.naamtamilar.magazine.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
public class User extends Auditable<String>{
	@Id
	@GeneratedValue
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
	private boolean adminApproved =true;
	@Column(columnDefinition="int default 0")
	private int gender;@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dob;
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;

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
