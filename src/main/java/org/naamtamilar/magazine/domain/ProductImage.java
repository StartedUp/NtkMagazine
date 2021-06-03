package org.naamtamilar.magazine.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Prithu on 4/2/18.
 */
@Entity
@Data
public class ProductImage {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    private String name;

    private String url;

    private int size;

    private int type;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
