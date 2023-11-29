package seedu.address.testutil;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.treatment.Treatment;

/**
 * A utility class containing a list of {@code Treatment} objects to be used in tests.
 */
public class TypicalTreatments {

    public static final Treatment WISDOM = new TreatmentBuilder().withName("WisdomTooth")
        .withCost("1080").withTime("13:12").build();

    public static final Treatment BRACES = new TreatmentBuilder().withName("Braces")
        .withCost("5000").withTime("12:31").build();

    public static final Treatment ROOT_CANAL = new TreatmentBuilder().withName("RootCanal")
        .withCost("10000").withTime("15:22").build();


    private TypicalTreatments() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical treatments.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Treatment treatment : getTypicalTreatments()) {
            ab.addTreatment(treatment);
        }
        return ab;
    }

    public static List<Treatment> getTypicalTreatments() {
        return new ArrayList<>(Arrays.asList(WISDOM, BRACES, ROOT_CANAL));
    }
}
