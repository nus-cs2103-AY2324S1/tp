package seedu.lovebook.testutil;

import seedu.lovebook.model.DatePrefs;

/**
 * A utility class containing the default date preferences.
 */
public class TypicalDatePrefs {
    private static DatePrefs datePrefs = new DatePrefs();

    public static DatePrefs getTypicalDatePrefs() {
        return datePrefs;
    }
}
