package seedu.lovebook.model.util;

import seedu.lovebook.model.LoveBook;
import seedu.lovebook.model.ReadOnlyLoveBook;
import seedu.lovebook.model.date.Age;
import seedu.lovebook.model.date.Avatar;
import seedu.lovebook.model.date.Date;
import seedu.lovebook.model.date.Gender;
import seedu.lovebook.model.date.Height;
import seedu.lovebook.model.date.Income;
import seedu.lovebook.model.date.Name;
import seedu.lovebook.model.date.Star;
import seedu.lovebook.model.date.horoscope.Horoscope;

/**
 * Contains utility methods for populating {@code LoveBook} with sample data.
 */
public class SampleDataUtil {
    public static Date[] getSamplePersons() {
        return new Date[] {
            new Date(new Name("Alex Yeoh"), new Age("23"), new Gender("M"), new Height("180"), new Income("3000"),
                    new Horoscope("LIBRA"), new Star("false"), new Avatar("9")),
            new Date(new Name("Bernice Yu"), new Age("24"), new Gender("F"), new Height("160"), new Income("3000"),
                    new Horoscope("SCORPIO"), new Star("true"), new Avatar("4")),
            new Date(new Name("Charlotte Oliveiro"), new Age("25"), new Gender("F"), new Height("160"),
                    new Income("3000"), new Horoscope("SAGITTARIUS"), new Star("false"),
                    new Avatar("8")),
            new Date(new Name("David Li"), new Age("44"), new Gender("M"), new Height("110"), new Income("3000"),
                    new Horoscope("VIRGO"), new Star("false"), new Avatar("1")),
            new Date(new Name("Irfan Ibrahim"), new Age("56"), new Gender("M"), new Height("123"),
                    new Income("3000"), new Horoscope("PISCES"), new Star("true"),
                    new Avatar("2")),
            new Date(new Name("Roy Balakrishnan"), new Age("21"), new Gender("M"), new Height("134"),
                    new Income("3000"), new Horoscope("LEO"), new Star("false"),
                    new Avatar("3"))
        };
    }

    public static ReadOnlyLoveBook getSampleLoveBook() {
        LoveBook sampleAb = new LoveBook();
        for (Date sampleDate : getSamplePersons()) {
            sampleAb.addDate(sampleDate);
        }
        return sampleAb;
    }
}
