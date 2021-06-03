package org.naamtamilar.magazine.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Address {
	@Id
    @GeneratedValue
    @NotNull
    private int id;
	@NotNull
	private String addressHolderName;
	@NotNull
	private String addressLine1;
	@NotNull
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
	@JoinColumn(name ="subscriber_id")
	private User user;
}
