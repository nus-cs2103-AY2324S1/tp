package seedu.address.model.room;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.room.exceptions.DuplicateRoomException;
import seedu.address.model.room.exceptions.RoomNotFoundException;

/**
 * A list of rooms in the hotel.
 */
public class UniqueRoomList implements Iterable<Room> {
    private final ObservableList<Room> internalList = FXCollections.observableArrayList();
    private final ObservableList<Room> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    private boolean contains(Room toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameRoom);
    }

    private void add(Room toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateRoomException();
        }
        internalList.add(toAdd);
    }

    /**
     * Retrieves a room by its room number.
     *
     * Searches through the list of rooms and returns the room that matches the given room number.
     * The method employs the isSameRoom method to check for room number equality.
     *
     * @param roomNumber The room number to search for.
     * @return The room that matches the provided room number.
     * @throws RoomNotFoundException if no room with the given room number is found.
     */
    public Room get(int roomNumber) {
        requireNonNull(roomNumber);
        return internalList.stream()
                .filter(room -> room.isSameRoom(new Room(roomNumber)))
                .findAny()
                .orElseThrow(RoomNotFoundException::new);
    }

    /**
     * Create a list of rooms with unique room numbers.
     */
    public void initializeRoom(int numberOfRooms) {
        requireNonNull(numberOfRooms);
        int floor = 1;
        int roomCount = 0;

        for (int i = 1; i <= numberOfRooms; i++) {
            int roomNumber = floor * 100 + (roomCount + 1);
            Room newRoom = new Room(roomNumber);
            add(newRoom);

            roomCount++;

            if (roomCount >= 10) {
                roomCount = 0;
                floor++;
            }
        }
    }

    @Override
    public Iterator<Room> iterator() {
        return internalList.iterator();
    }
}
