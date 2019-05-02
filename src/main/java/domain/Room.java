package domain;

import java.util.Objects;

public class Room {
    private String number;
    private int seatingCapacity;

    public Room(String number, int seatingCapacity) {
        this.number = number;
        this.seatingCapacity = seatingCapacity;
    }

    public String getNumber() {
        return number;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return seatingCapacity == room.seatingCapacity &&
                Objects.equals(number, room.number);
    }

    @Override
    public int hashCode() {

        return Objects.hash(number, seatingCapacity);
    }
}
