package seedu.address.model.event;

import seedu.address.model.person.Person;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents an Event in the address book.
 */
public abstract class Event {

    ArrayList<Person> person;

    LocalDate date;

    public Event(LocalDate date) {
        this.person = new ArrayList<>();
        this.date = date;
    }
}
