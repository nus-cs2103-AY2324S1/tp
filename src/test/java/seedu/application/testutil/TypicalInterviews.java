package seedu.application.testutil;

import static seedu.application.logic.commands.CommandTestUtil.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.application.model.job.interview.Interview;

/**
 * A utility class containing a list of {@code Job} objects to be used in tests.
 */
public class TypicalInterviews {

    public static final Interview TECHNICAL_INTERVIEW = new InterviewBuilder().withType("Technical")
            .withDateTime("Dec 31 2030 1200").withAddress("XYZ Road").build();

    public static final Interview BEHAVIOURAL_INTERVIEW = new InterviewBuilder().withType("Behavioural")
            .withDateTime("Jan 31 2020 1000").withAddress("123 Street").build();

    public static final Interview ONLINE_INTERVIEW = new InterviewBuilder().withType("Online")
            .withDateTime("Aug 18 2023 1600").withAddress("Home").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Interview CHEF_INTERVIEW = new InterviewBuilder().withType(VALID_INTERVIEW_TYPE_CHEF)
            .withDateTime(VALID_INTERVIEW_DATETIME_CHEF).withAddress(VALID_INTERVIEW_ADDRESS_CHEF).build();
    public static final Interview CLEANER_INTERVIEW = new InterviewBuilder().withType(VALID_INTERVIEW_TYPE_CLEANER)
            .withDateTime(VALID_INTERVIEW_DATETIME_CLEANER).withAddress(VALID_INTERVIEW_ADDRESS_CLEANER).build();

    private TypicalInterviews() {
    } // prevents instantiation

    public static List<Interview> getTypicalInterviews() {
        return new ArrayList<>(Arrays.asList(TECHNICAL_INTERVIEW, BEHAVIOURAL_INTERVIEW, ONLINE_INTERVIEW));
    }
}
