package org.naamtamilar.magazine.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Prithu on 14/9/17.
 */
@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date paymentDate;
    @NotNull
    private String paymentMode;
    private String transactionId;
    private String orderId;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private String paymentStatus;
    private String description;
    @OneToOne(cascade= CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

}
