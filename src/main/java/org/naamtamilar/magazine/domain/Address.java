package org.naamtamilar.magazine.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Address {
	@Id
    @GeneratedValue
    @NotNull
	@EqualsAndHashCode.Include
    private int id;
	@NotNull
	private String addressHolderName;
	@NotNull
	private String addressLine1;
	private String addressLine2;
	@NotNull
	private String city;
	@NotNull
	private String state="TamilNadu";
	@NotNull
	private String country="India";
	@NotNull
	private String pincode;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name ="user_id")
	private User user;
}
