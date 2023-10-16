package seedu.lovebook.model.util;

import seedu.lovebook.model.LoveBook;
import seedu.lovebook.model.ReadOnlyLoveBook;
import seedu.lovebook.model.person.Date;

/**
 * Contains utility methods for populating {@code LoveBook} with sample data.
 */
public class SampleDataUtil {
    public static Date[] getSamplePersons() {
        return new Date[] {};
    }

    public static ReadOnlyLoveBook getSampleLoveBook() {
        LoveBook sampleAb = new LoveBook();
        for (Date sampleDate : getSamplePersons()) {
            sampleAb.addPerson(sampleDate);
        }
        return sampleAb;
    }
}
