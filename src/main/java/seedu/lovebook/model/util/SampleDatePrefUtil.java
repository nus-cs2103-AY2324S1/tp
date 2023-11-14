package seedu.lovebook.model.util;

import seedu.lovebook.model.DatePrefs;
import seedu.lovebook.model.date.Age;
import seedu.lovebook.model.date.Height;
import seedu.lovebook.model.date.Income;
import seedu.lovebook.model.date.horoscope.Horoscope;

/**
 * A utility class containing a sample {@code DatePrefs} to be used in tests.
 */
public class SampleDatePrefUtil {
    public static DatePrefs getSamplePreferences() {
        Age age = new Age("20");
        Height height = new Height("180");
        Income income = new Income("10000");
        Horoscope horoscope = new Horoscope("Aries");
        return new DatePrefs(age, height, income, horoscope);
    }
}
