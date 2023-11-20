package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TypicalPersons;

public class PersonMeetingTimeComparatorTest {

    private final PersonMeetingTimeComparator comparator = new PersonMeetingTimeComparator();
    @Test
    public void compare() {
        Person personWithoutMeeting = TypicalPersons.FIONA;
        Person personWithEarlierMeeting = TypicalPersons.ELLE;
        Person personWithLaterMeeting = TypicalPersons.CARL;

        // first person has earlier meeting time -> returns -1
        assertEquals(comparator.compare(personWithEarlierMeeting, personWithLaterMeeting), -1);

        // both persons have same meeting time -> returns 0
        assertEquals(comparator.compare(personWithEarlierMeeting, personWithEarlierMeeting), 0);

        // second person has earlier meeting time -> returns 1
        assertEquals(comparator.compare(personWithLaterMeeting, personWithEarlierMeeting), 1);

        // one person has no meeting time -> throws
        assertThrows(NoSuchElementException.class, () ->
                comparator.compare(personWithoutMeeting, personWithEarlierMeeting));

        // both persons have no meeting time -> throws
        assertThrows(NoSuchElementException.class, () ->
                comparator.compare(personWithoutMeeting, personWithoutMeeting));
    }
}
