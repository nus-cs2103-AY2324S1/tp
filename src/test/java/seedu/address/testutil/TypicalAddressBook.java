package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code AddressBook} objects to be used in
 * tests.
 */
public class TypicalAddressBook {
    private TypicalAddressBook() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons and meetings.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();

        for (Person person : TypicalPersons.getTypicalPersons()) {
            ab.addPerson(person);
        }

        for (Meeting meeting : TypicalMeetings.getTypicalMeetings()) {
            ab.addMeeting(meeting);
        }

        return ab;
    }
}
