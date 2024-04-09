package edu.ncat.aggies.abchotelreservation;

import java.util.Date;

public record RoomReservation(Date startDate, Date endDate, String customerName) {
}
