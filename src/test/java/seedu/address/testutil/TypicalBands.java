package seedu.address.testutil;

import static seedu.address.testutil.TypicalMusicians.ALICE;
import static seedu.address.testutil.TypicalMusicians.BOB;
import static seedu.address.testutil.TypicalMusicians.CARL;
import static seedu.address.testutil.TypicalMusicians.DANIEL;
import static seedu.address.testutil.TypicalMusicians.ELLE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.band.Band;


/**
 * A utility class containing a list of {@code Musician} objects to be used in tests.
 */
public class TypicalBands {

    // Bands without Musicians

    public static final Band ACE = new BandBuilder().withName("Ace Jazz")
        .withGenres("jazz").build();

    public static final Band BOOM = new BandBuilder().withName("Boom Rock")
        .withGenres("rock").build();

    // Bands with Musicians

    public static final Band CANDY = new BandBuilder().withName("Candy Pop")
        .withGenres("pop").withMusicians(ALICE, BOB).build();

    public static final Band DRAGON = new BandBuilder().withName("Dragon Metal")
        .withGenres("metal").withMusicians(CARL, DANIEL).build();

    public static final Band ELISE = new BandBuilder().withName("Elise Classical")
        .withGenres("classical").withMusicians(ELLE).build();


    private TypicalBands() {} // prevents instantiation

    public static List<Band> getTypicalBands() {
        return new ArrayList<>(Arrays.asList(ACE, BOOM, CANDY, DRAGON, ELISE));
    }
}
