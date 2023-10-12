package seedu.lovebook.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.lovebook.model.LoveBook;
import seedu.lovebook.model.ReadOnlyLoveBook;
import seedu.lovebook.model.person.Age;
import seedu.lovebook.model.person.Date;
import seedu.lovebook.model.person.Gender;
import seedu.lovebook.model.person.Height;
import seedu.lovebook.model.person.Name;
import seedu.lovebook.model.tag.Tag;

/**
 * Contains utility methods for populating {@code LoveBook} with sample data.
 */
public class SampleDataUtil {
    public static Date[] getSamplePersons() {
        return new Date[] {
            new Date(new Name("Alex Yeoh"), new Age("23"), new Gender("M"),
                new Height("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Date(new Name("Bernice Yu"), new Age("34"), new Gender("F"),
                new Height("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Date(new Name("Charlotte Oliveiro"), new Age("31"), new Gender("F"),
                new Height("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Date(new Name("David Li"), new Age("21"), new Gender("M"),
                new Height("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Date(new Name("Irfan Ibrahim"), new Age("25"), new Gender("M"),
                new Height("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Date(new Name("Roy Balakrishnan"), new Age("43"), new Gender("M"),
                new Height("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
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
