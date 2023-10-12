package seedu.address.testutil;

import seedu.address.model.interview.Interview;

/**
 * A utility class containing a list of {@code Interview} objects to be used in tests.
 */
public class TypicalInterviews {
    public static final Interview STANDARD_INTERVIEW = new Interview("ApplicantObject",
            "StandardJobRole", "StandardTime");

    /** Prevents instantiation - Code reused and adapted from AB3 TypicalPersons.java */
    private TypicalInterviews() {}

}
