package edu.ncat.aggies.abchotelreservation;

import java.util.ArrayList;

//Another example of single inheritance
public class ABCRoomManager implements RoomManager {

    private final ArrayList<Room> rooms = new ArrayList<>();
    int highestRoomNumber = 0;

    //Setting number of rooms in constructor
    public ABCRoomManager() {
        this(1, 1, 1);
    }

    public ABCRoomManager(int numRegular, int numDeluxe, int numJunior) {
        for (int i = numRegular; i > 0; i--) addRoom(RoomType.REGULAR);
        for (int i = numDeluxe; i > 0; i--) addRoom(RoomType.DELUXE);
        for (int i = numJunior; i > 0; i--) addRoom(RoomType.JUNIOR);
    }

    private void addRoom(RoomType type) {
        highestRoomNumber++;
        rooms.add(new Room(highestRoomNumber, type));
    }

    //Example of static polymorphism
    private void addRoom(int roomNumber, RoomType type) {
        Room newRoom = new Room(roomNumber, type);
        if (isDuplicateRoom(newRoom)) return;

        highestRoomNumber = Math.max(highestRoomNumber, roomNumber);
        rooms.add(newRoom);
    }

    private boolean isDuplicateRoom(Room newRoom) {
        for (Room room :
                rooms) {
            if (room.getRoomNumber() == newRoom.getRoomNumber()) return true;
        }
        return false;
    }

    //An example of dynamic polymorphism
    //Returns room number if reservation can be made for a given room type and date range or -1 otherwise.
    @Override
    public int makeReservation(RoomReservation reservation, RoomType roomType) {
        for (Room room :
                rooms) {
            if (room.getType() != roomType) continue;
            boolean cannotReserve = false;
            for (int i = 0; i < room.getNumReservations(); i++) {
                RoomReservation oldReservation = room.getReservation(i);

                boolean startOverlap = (oldReservation.startDate().after(reservation.startDate())
                        && oldReservation.startDate().before(reservation.endDate()));
                boolean endOverlap = (oldReservation.endDate().after(reservation.startDate())
                        && oldReservation.endDate().before(reservation.endDate()));
                boolean outerOverlap = (oldReservation.startDate().before(reservation.startDate()) || oldReservation.startDate().equals(reservation.startDate()))
                        && (oldReservation.endDate().after(reservation.endDate()) || oldReservation.endDate().equals(reservation.endDate()));

                cannotReserve = cannotReserve || startOverlap || endOverlap || outerOverlap;
            }
            if(cannotReserve) continue; //check next room
            room.addReservation(reservation);
            return room.getRoomNumber();
        }
        return -1;
    }
}
