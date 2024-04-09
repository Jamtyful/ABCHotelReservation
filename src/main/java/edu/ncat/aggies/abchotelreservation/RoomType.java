package edu.ncat.aggies.abchotelreservation;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum RoomType {

    REGULAR("Regular", 200, new HashMap<FurnitureType, Integer>() {{
        put(FurnitureType.QUEEN_BED, 1);
    }}),
    DELUXE("Deluxe", 300, new HashMap<FurnitureType, Integer>() {{
        put(FurnitureType.QUEEN_BED, 1);
        put(FurnitureType.TWIN_BED, 1);
    }}),
    JUNIOR("Junior", 350, new HashMap<FurnitureType, Integer>() {{
        put(FurnitureType.QUEEN_BED, 1);
        put(FurnitureType.TWIN_BED, 1);
        put(FurnitureType.SOFA, 1);
    }});

    private final String name;
    private final Map<FurnitureType, Integer> furnitureList;

    private final int cost;

    RoomType(String name, int cost, Map<FurnitureType, Integer> furnitureList) {

        this.name = name;
        this.cost = cost;
        this.furnitureList = Collections.unmodifiableMap(furnitureList);
    }

    public String getName() {
        return name;
    }

    public Map<FurnitureType, Integer> getFurnitureList() {
        return furnitureList;
    }

    public int getCost() {
        return cost;
    }
}
