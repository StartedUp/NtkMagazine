package org.naamtamilar.magazine.domain;

import lombok.Data;
import org.naamtamilar.magazine.enums.MeasurmentUnitsEnum;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Prithu on 04-03-2017.
 */
@Entity
@Data
public class Product {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @NotNull
    private String productName;

    private int measurementUnit;

    private String varietyName;

    private String colour;

    private String sizeInWord;

    private BigDecimal sizeInNumber;

    @NotNull
    private BigDecimal pricePerUnit;

    private String productDescription;

    private BigDecimal minimumQuantity;

    private boolean active;

    @OneToMany(targetEntity = ProductImage.class, mappedBy = "product",cascade= CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProductImage> productImages;

    public MeasurmentUnitsEnum returnMeasurementUnit(int measurementUnitCode){
        List<MeasurmentUnitsEnum> measurementUnits = Arrays.asList(MeasurmentUnitsEnum.values());
        for(MeasurmentUnitsEnum measurmentUnitsEnum:measurementUnits){
            if(measurementUnitCode==measurmentUnitsEnum.getUnitCode()){
                return measurmentUnitsEnum;
            }
        }
        return null;
    }
}
