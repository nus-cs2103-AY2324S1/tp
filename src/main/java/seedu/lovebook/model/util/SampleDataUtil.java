package seedu.lovebook.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.lovebook.model.LoveBook;
import seedu.lovebook.model.ReadOnlyLoveBook;
import seedu.lovebook.model.person.Date;
import seedu.lovebook.model.tag.Tag;

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

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
