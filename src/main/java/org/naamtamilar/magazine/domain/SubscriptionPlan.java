package org.naamtamilar.magazine.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by Prithu on 04-03-2017.
 */
@Entity
@Data
public class SubscriptionPlan {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @NotNull
    private String planName;
    @NotNull
    private String subscriptionDurationType;

    private String description="";
}
