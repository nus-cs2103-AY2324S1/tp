package seedu.address.model.person;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Implements the comparator to sort Persons based on their meeting times.
 */
public class PersonMeetingTimeComparator implements Comparator<Person> {

    /**
     * Compares the meeting times of two Person objects.
     * Both Person objects should have a valid meeting time that is not null.
     *
     * @param person1 the first Person object to be compared.
     * @param person2 the second Person object to be compared.
     * @return The relative order of the Person objects.
     */
    @Override
    public int compare(Person person1, Person person2) throws NoSuchElementException {
        MeetingTime meetingTime1 = person1.getMeetingTime().orElseThrow();
        MeetingTime meetingTime2 = person2.getMeetingTime().orElseThrow();

        return meetingTime1.value.compareTo(meetingTime2.value);
    }
}
