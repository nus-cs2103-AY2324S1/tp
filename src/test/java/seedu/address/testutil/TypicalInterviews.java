package seedu.address.testutil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.interview.Interview;

/**
 * A utility class containing a list of {@code Interview} objects to be used in tests.
 * Adapted from AB3's TypicalPersons class.
 */
public class TypicalInterviews {
    public static final Interview STANDARD_INTERVIEW = new Interview(TypicalApplicants.ALICE,
                    "StandardJobRole",
                                       LocalDateTime.of(2024, 12, 21, 19, 0),
                    LocalDateTime.of(2024, 12, 21, 21, 0),
                            false);
    public static final Interview STANDARD_INTERVIEW_2 = new Interview(TypicalApplicants.BENSON,
                    "StandardJobRole2",
                                         LocalDateTime.of(2025, 12, 21, 14, 0),
                    LocalDateTime.of(2025, 12, 21, 16, 0),
                            false);
    public static final Interview STANDARD_INTERVIEW_3 = new Interview(TypicalApplicants.CARL,
                    "StandardJobRole3",
                                         LocalDateTime.of(2024, 5, 21, 9, 0),
                    LocalDateTime.of(2024, 5, 21, 11, 0),
                            false
                            );
    public static final Interview STANDARD_INTERVIEW_4 = new Interview(TypicalApplicants.DANIEL,
                    "StandardJobRole4",
                                         LocalDateTime.of(2024, 7, 12, 10, 45),
                    LocalDateTime.of(2024, 7, 12, 12, 45),
                            false
                            );
    public static final Interview STANDARD_INTERVIEW_DIFFERENT_ROLE = new Interview(TypicalApplicants.ALICE,
                    "DifferentStandardJobRole",
                                                      LocalDateTime.of(2024, 8, 12, 8, 30),
                    LocalDateTime.of(2024, 8, 12, 10, 30),
                            false
                            );
    public static final Interview STANDARD_INTERVIEW_DIFFERENT_APPLICANT =
            new Interview(TypicalApplicants.ELLE, "StandardJobRole",
                          LocalDateTime.of(2024, 10, 27, 14, 0),
                            LocalDateTime.of(2024, 10, 27, 16, 0),
                                    false
                                    );
    public static final Interview STANDARD_INTERVIEW_DIFFERENT_TIME = new Interview(TypicalApplicants.ALICE,
                    "StandardJobRole",
                                                      LocalDateTime.of(2024, 11, 6, 15, 0),
                    LocalDateTime.of(2024, 11, 6, 17, 0),
                            false);

    /** Prevents instantiation - Code reused and Adapted from AB3's TypicalPersons.java */
    private TypicalInterviews() {}

    public static List<Interview> getTypicalInterviews() {
        return new ArrayList<>(Arrays.asList(STANDARD_INTERVIEW, STANDARD_INTERVIEW_2,
                STANDARD_INTERVIEW_3, STANDARD_INTERVIEW_4));
    }
}
