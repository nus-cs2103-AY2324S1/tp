package seedu.address.testutil;

import seedu.address.model.interview.Interview;

/**
 * A utility class containing a list of {@code Interview} objects to be used in tests.
 * Adapted from AB3 TypicalPersons class.
 */
public class TypicalInterviews {
    public static final Interview STANDARD_INTERVIEW = new Interview("ApplicantObject",
            "StandardJobRole", "StandardTime");
    public static final Interview STANDARD_INTERVIEW_2 = new Interview("ApplicantObject2",
            "StandardJobRole2", "StandardTime2");
    public static final Interview STANDARD_INTERVIEW_DIFFERENT_ROLE = new Interview("ApplicantObject",
            "DifferentStandardJobRole", "StandardTime");
    public static final Interview STANDARD_INTERVIEW_DIFFERENT_APPLICANT =
            new Interview("DifferentApplicantObject", "StandardJobRole", "StandardTime");
    public static final Interview STANDARD_INTERVIEW_DIFFERENT_TIME = new Interview("ApplicantObject",
            "StandardJobRole", "DifferentStandardTime");

    /** Prevents instantiation - Code reused and adapted from AB3 TypicalPersons.java */
    private TypicalInterviews() {}

}
