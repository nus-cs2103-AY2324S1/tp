package seedu.lovebook.model.util;

import seedu.lovebook.model.DatePrefs;
import seedu.lovebook.model.person.Age;
import seedu.lovebook.model.person.Gender;
import seedu.lovebook.model.person.Height;
import seedu.lovebook.model.person.Income;
import seedu.lovebook.model.person.horoscope.Horoscope;

/**
 * A utility class containing a sample {@code DatePrefs} to be used in tests.
 */
public class SampleDatePrefUtil {
    public static DatePrefs getSamplePreferences() {
        Age age = new Age("20");
        Height height = new Height("180");
        Income income = new Income("10000");
        Gender gender = new Gender("F");
        Horoscope horoscope = new Horoscope("Aries");
        return new DatePrefs(age, gender, height, income, horoscope);
    }
}
