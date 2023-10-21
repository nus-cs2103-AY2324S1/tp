package seedu.ccacommander.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.ccacommander.model.event.Event;

/**
 * A utility class containing a list of {@code Event} objects to be used in tests.
 */
public class TypicalEvents {

    public static final Event AURORA_BOREALIS = new EventBuilder().withName("Aurora Borealis")
            .withDate("2023-11-30").withLocation("Greenland").build();

    public static final Event BOXING_DAY = new EventBuilder().withName("Boxing Day")
            .withDate("2023-12-26").withLocation("Ridge View Residential College").build();

    public static final Event CHINESE_NEW_YEAR = new EventBuilder().withName("Chinese New Year")
            .withDate("2024-02-10").withLocation("Communal Hall").build();

    public static final Event DOG_DAY = new EventBuilder().withName("Dog Celebration Day")
            .withDate("2024-08-26").withLocation("UTown Pitstop").build();

    public static final Event ECHO_DAY = new EventBuilder().withName("Echo Day")
            .withDate("2023-05-05").withLocation("Batu Cave").build();

    public static final Event FESTIVAL = new EventBuilder().withName("Festival")
            .withDate("2023-10-05").withLocation("Clementi 321").build();

    public static final Event GRAVITY_DISCOVERY_DAY = new EventBuilder().withName("Gravity Discovery Day")
            .withDate("1666-06-18").withLocation("Cambridge University").build();

    public static final String KEYWORD_MATCHING_FESTIVAL = "Festival"; // A keyword that matches Festival

    private TypicalEvents() {} // prevents instantiation

    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(AURORA_BOREALIS, BOXING_DAY, CHINESE_NEW_YEAR, DOG_DAY, ECHO_DAY,
                FESTIVAL, GRAVITY_DISCOVERY_DAY));
    }
}
