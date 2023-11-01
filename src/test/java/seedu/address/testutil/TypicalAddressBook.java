package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

public class TypicalAddressBook {
    private TypicalAddressBook() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons and meetings.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();

        for (Meeting meeting : TypicalMeetings.getTypicalMeetings()) {
            ab.addMeeting(meeting);
        }

        for (Person person : TypicalPersons.getTypicalPersons()) {
            ab.addPerson(person);
        }

        return ab;
    }
}
