package edu.ncat.aggies.abchotelreservation;

public enum FurnitureType {
    QUEEN_BED("Queen Bed"),
    TWIN_BED("Twin Bed"),
    SOFA("Sofa");


    private final String name;

    FurnitureType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
