package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.EventsBook;
import seedu.address.model.event.Event;

/**
 * A utility class containing a list of {@code Events} objects to be used in tests.
 */
public class TypicalEvents {

    public static final Event EVENT1;

    static {
        try {
            EVENT1 = new EventBuilder().withName("Meeting with Alice")
                    .withClient(new PersonBuilder().withName("Alice").build())
                    .withDuration("23-09-2023 09:00", "23-09-2023 10:00")
                    .build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Event EVENT2;

    static {
        try {
            EVENT2 = new EventBuilder().withName("Conference with Bob")
                        .withDuration("24-09-2023 14:00", "24-09-2023 18:00")
                        .build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    // Manually added
    public static final Event EVENT3;

    static {
        try {
            EVENT3 = new EventBuilder().withName("Team Lunch")
                    .withDuration("25-09-2023 12:00", "25-09-2023 13:00")
                    .build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    // Manually added - Event's details found in {@code CommandTestUtil}
    public static final Event EVENT4;

    static {
        try {
            EVENT4 = new EventBuilder().withName("Project Presentation")
                    .withDuration("26-09-2023 15:00", "26-09-2023 16:30")
                    .build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Event EVENT5;
    static {
        try {
            EVENT5 = new EventBuilder().withName("Meeting")
                    .withDuration("01-01-2024 14:00", "01-01-2024 15:00")
                    .withLocation("Meeting Room")
                    .withEventDescription("Meeting for discussion")
                    .build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Event EVENT6;
    static {
        try {
            EVENT6 = new EventBuilder().withName("Meeting")
                    .withDuration("01-01-2099 14:00", "01-01-2099 15:00")
                    .withLocation("Meeting Room")
                    .withEventDescription("Meeting for discussion")
                    .build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private TypicalEvents() {} // prevents instantiation

    /**
     * Returns an {@code EventsBook} with all the typical persons.
     */
    public static EventsBook getTypicalEventsBook() {
        EventsBook eb = new EventsBook();
        for (Event event : getTypicalEvents()) {
            eb.addEvent(event);
        }

        return eb;
    }

    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(EVENT1, EVENT2, EVENT3, EVENT4, EVENT5, EVENT6));
    }
}
