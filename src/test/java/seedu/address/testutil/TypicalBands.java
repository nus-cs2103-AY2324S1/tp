package seedu.address.testutil;

import static seedu.address.testutil.TypicalMusicians.ALICE;
import static seedu.address.testutil.TypicalMusicians.BENSON;
import static seedu.address.testutil.TypicalMusicians.CARL;
import static seedu.address.testutil.TypicalMusicians.DANIEL;
import static seedu.address.testutil.TypicalMusicians.ELLE;
import static seedu.address.testutil.TypicalMusicians.FIONA;
import static seedu.address.testutil.TypicalMusicians.GEORGE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.band.Band;


/**
 * A utility class containing a list of {@code Musician} objects to be used in tests.
 */
public class TypicalBands {

    public static final Band ACE = new BandBuilder().withName("Ace Jazz")
        .withGenres("jazz").withMusicians(ALICE, BENSON, CARL).build();

    public static final Band BOOM = new BandBuilder().withName("Boom Rock")
        .withGenres("rock").withMusicians(DANIEL, ELLE).build();

    public static final Band CANDY = new BandBuilder().withName("Candy Pop")
        .withGenres("pop").withMusicians(FIONA, GEORGE).build();


    private TypicalBands() {} // prevents instantiation

    public static List<Band> getTypicalBands() {
        return new ArrayList<>(Arrays.asList(ACE, BOOM, CANDY));
    }
}
