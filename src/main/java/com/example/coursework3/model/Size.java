package com.example.coursework3.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Size {
    SIZE_37(37),
    SIZE_39(39),
    SIZE_41(41);

    private final Integer intValue;

    Size(int size) {
        this.intValue = size;
    }

    @JsonCreator
    public static Size forValues(Integer strSize) {
        for (Size size : Size.values()) {
            if (size.getIntValue().equals(strSize)) {
                return size;
            }
        }
        return null;
    }

    @JsonValue
    public Integer getIntValue() {
        return this.intValue;
    }

}

