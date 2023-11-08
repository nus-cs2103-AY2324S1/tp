package seedu.lovebook.model.date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.lovebook.testutil.TypicalDates.ALICE;
import static seedu.lovebook.testutil.TypicalDates.CARL;
import static seedu.lovebook.testutil.TypicalDates.DANIEL;
import static seedu.lovebook.testutil.TypicalDates.MRPERFECT;

import org.junit.jupiter.api.Test;

import seedu.lovebook.model.DatePrefs;

public class ScoreTest {
    @Test
    public void testCorrectScore() {
        // Default: age 21; height 170; income 10000; horoscope ARIES;
        DatePrefs prefs = new DatePrefs();

        assertEquals(ALICE.getScore(prefs), 0);

        assertEquals(DANIEL.getScore(prefs), 2);

        assertEquals(MRPERFECT.getScore(prefs), 40);

        assertEquals(CARL.getScore(prefs), 16);

    }
}
