package seedu.address.testutil.typicalentities;

import seedu.address.model.AddressBook;
import seedu.address.model.band.Band;
import seedu.address.model.musician.Musician;

/**
 * A utility class to create a typical address book with the typical musicians and bands to be used in tests.
 */
public class TypicalAddressBook {
    private TypicalAddressBook() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical musicians and bands.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Musician musician : TypicalMusicians.getTypicalMusicians()) {
            ab.addMusician(musician);
        }
        for (Band band : TypicalBands.getTypicalBands()) {
            ab.addBand(band);
        }
        return ab;
    }
}
