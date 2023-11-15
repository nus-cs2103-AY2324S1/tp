package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.EventBook;
import seedu.address.model.event.Event;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalEvents {

    public static final Event EVENT_1 = new Event(ALICE, "Interview",
            LocalDateTime.of(2023, 11, 8, 12, 0),
            LocalDateTime.of(2023, 11, 9, 12, 0));

    public static final Event EVENT_2 = new Event(BENSON, "Round 1 Test",
            LocalDateTime.of(2023, 11, 8, 12, 0),
            LocalDateTime.of(2023, 11, 9, 12, 0));

    public static final Event EVENT_3 = new Event(CARL, "Round 2 Test",
            LocalDateTime.of(2023, 11, 8, 12, 0),
            LocalDateTime.of(2023, 11, 9, 12, 0));

    public static final Event EVENT_4 = new Event(DANIEL, "Round 3 Test",
            LocalDateTime.of(2023, 11, 8, 12, 0),
            LocalDateTime.of(2023, 11, 9, 12, 0));

    private TypicalEvents() {} // prevents instantiation

    /**
     * Returns an {@code EventBook} with all the typical events.
     */
    public static EventBook getTypicalEventBook() {
        EventBook eb = new EventBook();
        for (Event event : getTypicalEvents()) {
            eb.addEvent(event);
        }
        return eb;
    }

    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(EVENT_1, EVENT_2, EVENT_3, EVENT_4));
    }
}
