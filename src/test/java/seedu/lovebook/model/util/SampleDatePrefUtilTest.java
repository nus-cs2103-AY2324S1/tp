package seedu.lovebook.model.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.lovebook.model.DatePrefs;
import seedu.lovebook.model.date.Age;
import seedu.lovebook.model.date.Height;
import seedu.lovebook.model.date.Income;
import seedu.lovebook.model.date.horoscope.Horoscope;

public class SampleDatePrefUtilTest {

    @Test
    public void getSampleDatePrefsTest() {
        DatePrefs datePrefs = new DatePrefs(new Age("20"), new Height("180"),
                new Income("10000"), new Horoscope("Aries"));
        assertTrue(SampleDatePrefUtil.getSamplePreferences().equals(datePrefs));
    }
}
