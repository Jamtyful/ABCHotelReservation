package edu.ncat.aggies.abchotelreservation;

import java.util.ArrayList;
import java.util.List;

public class Room {
    public int getRoomNumber() {
        return roomNumber;
    }

    public RoomType getType() {
        return type;
    }

    private final int roomNumber;
    private final RoomType type;

    public int getNumReservations() {
        return reservations.size();
    }

    public RoomReservation getReservation(int i) {
        return reservations.get(i);
    }

    public void addReservation(RoomReservation roomReservation) {
        reservations.add(roomReservation);
    }

    public boolean cancelReservation(RoomReservation reservation) {
        return reservations.remove(reservation);
    }

    private final List<RoomReservation> reservations = new ArrayList<>();

    public Room(int roomNumber, RoomType type) {
        this.roomNumber = roomNumber;
        this.type = type;
    }

    //An example of dynamic polymorphism
    @Override
    public String toString() {
        return type.getName() + "-" + roomNumber;
    }
}
