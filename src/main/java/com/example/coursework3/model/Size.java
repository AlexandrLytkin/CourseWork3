package com.example.coursework3.model;

public enum Size {
    SIZE_37("size",36),
    SIZE_39("size",39),
    SIZE_41("size",41);

    private String unit;
    private final int size;

    private Size(String unit, double size) {
        this.unit = unit;
        this.size = (int) size;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getSize() {
        return size;
    }
}
