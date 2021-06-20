package org.naamtamilar.magazine.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.Set;

/**
 * Created by Prithu on 04-03-2017.
 */
@Entity
@Table(name = "subscription")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    @NotNull
    @Column(name = "start_date")
    private Date startDate;
    @NotNull
    @Column(name = "end_date")
    private Date endDate;
    @Column(name = "preferred_delivery_time")
    private Time preferredDeliveryTime;
    @NotNull
    @Column(name = "quantity_per_day")
    private BigDecimal quantityPerDay;
    @NotNull
    @Column(name = "total_quantity")
    private BigDecimal totalQuantity;
    @Column(name = "discount_percentage")
    private int discountPercentage;
    @Column(name = "discount")
    private BigDecimal discount;
    @NotNull
    @Column(name = "total_number_of_days")
    private int totalNumberOfDays;
    @NotNull
    @Column(name = "actual_price")
    private BigDecimal actualPrice;
    @NotNull
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    @NotNull
    @Column(name = "payment_status")
    private String paymentStatus;
    @Column(name = "payment_type")
    private String paymentType;
    @NotNull
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "subscriber_id")
    private User subscriber;
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_address")
    private Address deliveryAddress;
    @Column(name = "subscription_status")
    private String subscriptionStatus;
    @Column(name = "create_date")
    private Date createDate;
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    @NotNull
    @Column(unique = true)
    private String orderId;

}
