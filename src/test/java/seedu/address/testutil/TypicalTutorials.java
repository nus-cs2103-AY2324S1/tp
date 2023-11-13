package seedu.address.testutil;

import seedu.address.model.tutorial.Tutorial;

/**
 * A utility class containing a list of {@code Tutorial} objects to be used in tests.
 */
public class TypicalTutorials {
    public static final Tutorial TUTORIAL_TUT1_MON9PM =
            new Tutorial(TypicalModules.FIRST_MODULE, "TUT1", "Mon 9pm");
    public static final Tutorial TUTORIAL_TUT2_TUE9PM =
            new Tutorial(TypicalModules.FIRST_MODULE, "TUT2", "Tue 9pm");
}
