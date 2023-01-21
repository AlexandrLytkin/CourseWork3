package com.example.coursework3.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import org.jetbrains.annotations.Nullable;

public enum Size {
    SIZE_37("size",37),
    SIZE_39("size",39),
    SIZE_41("size",41);

    private String unit;
    private final int size;

    Size(String unit, double size) {
        this.unit = unit;
        this.size = (int) size;
    }

    @JsonCreator
    public static @Nullable Size forValues(@JsonProperty("size") String strSize) {
        for (Size size : Size.values()) {
            if (
                    String.valueOf(size.getSize()).equals(strSize)) {
                return size;
            }
        }
        return null;
    }


    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @JsonValue
    public int getSize() {
        return size;
    }
}
