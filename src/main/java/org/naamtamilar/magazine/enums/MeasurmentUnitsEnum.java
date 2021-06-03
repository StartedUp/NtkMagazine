package org.naamtamilar.magazine.enums;

/**
 * Created by Balaji on 11/5/18.
 */
public enum MeasurmentUnitsEnum {
    GRAM("gram", 1),
    KG("kilogram", 2),
    METER("meter", 3),
    PIECE("piece", 4),
    LITRE("litre", 5),
    NUMBER("number", 6);

    private String description;
    private int unitCode;

    MeasurmentUnitsEnum(String description, int unitCode) {
        this.description = description;
        this.unitCode = unitCode;
    }

    public String getDescription() {
        return description;
    }

    public MeasurmentUnitsEnum setDescription(String description) {
        this.description = description;
        return this;
    }


    public int getUnitCode() {
        return unitCode;
    }
}
