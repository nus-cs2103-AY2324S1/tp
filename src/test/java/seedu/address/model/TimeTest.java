package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.interview.Interview;
import seedu.address.model.interview.UniqueInterviewList;
import seedu.address.testutil.TypicalInterviews;

public class TimeTest {
    /*
     * Tests for the method which sorts the interviews in chronological order
     */
    @Test
    void testSortInterviews() {
        List<Interview> interviewList = TypicalInterviews.getTypicalInterviews();
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        uniqueInterviewList.setInterviews(interviewList);
        List<Interview> expected = new ArrayList<>();
        expected.add(TypicalInterviews.STANDARD_INTERVIEW_3);
        expected.add(TypicalInterviews.STANDARD_INTERVIEW_4);
        expected.add(TypicalInterviews.STANDARD_INTERVIEW);
        expected.add(TypicalInterviews.STANDARD_INTERVIEW_2);
        List<Interview> actual = Time.sortInterviewsInChronologicalAscendingOrder(uniqueInterviewList);
        assertEquals(expected, actual);
    }
}
