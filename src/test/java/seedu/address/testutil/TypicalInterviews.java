package seedu.address.testutil;

import seedu.address.model.interview.Interview;

/**
 * A utility class containing a list of {@code Interview} objects to be used in tests.
 * Adapted from AB3 TypicalPersons class.
 */
public class TypicalInterviews {
    public static final Interview STANDARD_INTERVIEW = new Interview(TypicalApplicants.HOON,
            "StandardJobRole", "StandardTime");
    public static final Interview STANDARD_INTERVIEW_2 = new Interview(TypicalApplicants.AMY,
            "StandardJobRole2", "StandardTime2");
    public static final Interview STANDARD_INTERVIEW_DIFFERENT_ROLE = new Interview(TypicalApplicants.HOON,
            "DifferentStandardJobRole", "StandardTime");
    public static final Interview STANDARD_INTERVIEW_DIFFERENT_APPLICANT =
            new Interview(TypicalApplicants.ALICE, "StandardJobRole", "StandardTime");
    public static final Interview STANDARD_INTERVIEW_DIFFERENT_TIME = new Interview(TypicalApplicants.HOON,
            "StandardJobRole", "DifferentStandardTime");

    /** Prevents instantiation - Code reused and adapted from AB3 TypicalPersons.java */
    private TypicalInterviews() {}

}
