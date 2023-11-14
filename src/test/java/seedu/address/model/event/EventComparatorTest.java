package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.testutil.EventBuilder;


public class EventComparatorTest {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    private static final Event EVENT1;

    static {
        try {
            EVENT1 = new EventBuilder()
                    .withDuration("01-01-2023 10:00", "01-01-2023 11:00")
                    .build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static final Event EVENT2;

    static {
        try {
            EVENT2 = new EventBuilder().withDuration("01-01-2023 11:00", "01-01-2023 12:00").build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testCompare_event1BeforeEvent2_returnsNegativeValue() {
        EventComparator comparator = new EventComparator();
        int result = comparator.compare(EVENT1, EVENT2);
        assertTrue(result < 0);
    }

    @Test
    public void testCompare_event1AfterEvent2_returnsPositiveValue() {
        EventComparator comparator = new EventComparator();
        int result = comparator.compare(EVENT2, EVENT1);
        assertTrue(result > 0);
    }

    @Test
    public void testCompare_event1EqualsEvent2_returnsZero() {
        EventComparator comparator = new EventComparator();
        int result = comparator.compare(EVENT1, EVENT1);
        assertEquals(0, result);
    }
}

