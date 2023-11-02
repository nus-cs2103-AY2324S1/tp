package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.interview.Interview;
import seedu.address.model.interview.UniqueInterviewList;
import seedu.address.testutil.TypicalApplicants;
import seedu.address.testutil.TypicalInterviews;

public class TimeTest {
    /*
     * Tests for the listsInterviewsToday class
     */
    @Test
    void testListInterviewsToday() {
        List<Interview> interviewList = TypicalInterviews.getTypicalInterviews();
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        uniqueInterviewList.setInterviews(interviewList);
        List<Interview> expected = new ArrayList<>();
        List<Interview> actual = Time.listInterviewsToday(uniqueInterviewList);
        assertEquals(expected, actual);
    }

    @Test
    void testListInterviewsToday2() {
        List<Interview> interviewList = TypicalInterviews.getTypicalInterviews();
        LocalDateTime today = LocalDateTime.now();
        Interview interviewNow = new Interview(TypicalApplicants.IDA, "SWE", today, today);
        interviewList.add(interviewNow);
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        uniqueInterviewList.setInterviews(interviewList);
        List<Interview> expected = new ArrayList<>();
        expected.add(interviewNow);
        List<Interview> actual = Time.listInterviewsToday(uniqueInterviewList);
        assertEquals(expected, actual);
    }

    @Test
    void testListInterviewsToday3() {
        List<Interview> interviewList = TypicalInterviews.getTypicalInterviews();
        int todayDay = 4;
        int todayMonth = 4;
        int todayYear = 2038;
        Interview interviewNow = new Interview(TypicalApplicants.IDA,
                "SWE",
                LocalDateTime.of(todayYear, todayMonth, todayDay, 9, 11),
                LocalDateTime.of(todayYear, todayMonth, todayDay, 11, 11)
        );
        interviewList.add(interviewNow);
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        uniqueInterviewList.setInterviews(interviewList);
        List<Interview> expected = new ArrayList<>();
        List<Interview> actual = Time.listInterviewsToday(uniqueInterviewList);
        assertEquals(expected, actual);
    }

    @Test
    void testListInterviewsToday4() {
        List<Interview> interviewList = TypicalInterviews.getTypicalInterviews();
        int todayDay = 1;
        int todayMonth = 1;
        int todayYear = 2099;
        Interview interviewNow = new Interview(TypicalApplicants.IDA,
                "SWE",
                LocalDateTime.of(todayYear, todayMonth, todayDay + 1, 9, 11),
                LocalDateTime.of(todayYear, todayMonth, todayDay + 1, 11, 11)
        );
        interviewList.add(interviewNow);
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        uniqueInterviewList.setInterviews(interviewList);
        List<Interview> expected = new ArrayList<>();
        List<Interview> actual = Time.listInterviewsToday(uniqueInterviewList);
        assertEquals(expected, actual);
    }

    @Test
    void testListInterviewsToday5() {
        List<Interview> interviewList = TypicalInterviews.getTypicalInterviews();
        int todayDay = 1;
        int todayMonth = 12;
        int todayYear = 2050;
        Interview interviewNow = new Interview(TypicalApplicants.IDA,
                "SWE",
                LocalDateTime.of(todayYear, todayMonth, todayDay, 9, 11),
                LocalDateTime.of(todayYear, todayMonth, todayDay, 11, 11)
        );
        interviewList.add(interviewNow);
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        uniqueInterviewList.setInterviews(interviewList);
        List<Interview> expected = new ArrayList<>();
        List<Interview> actual = Time.listInterviewsToday(uniqueInterviewList);
        assertEquals(expected, actual);
    }

    @Test
    void testListInterviewsToday6() {
        List<Interview> interviewList = TypicalInterviews.getTypicalInterviews();
        int todayDay = 1;
        int todayMonth = 2;
        int todayYear = 2099;
        Interview interviewNow = new Interview(TypicalApplicants.IDA,
                "SWE",
                LocalDateTime.of(todayYear, todayMonth, todayDay, 9, 11),
                LocalDateTime.of(todayYear, todayMonth, todayDay, 11, 11)
        );
        interviewList.add(interviewNow);
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        uniqueInterviewList.setInterviews(interviewList);
        List<Interview> expected = new ArrayList<>();
        List<Interview> actual = Time.listInterviewsToday(uniqueInterviewList);
        assertEquals(expected, actual);
    }

    @Test
    void testListInterviewsToday7() {
        List<Interview> interviewList = TypicalInterviews.getTypicalInterviews();
        int todayDay = 1;
        int todayMonth = 1;
        int todayYear = 2099;
        Interview interviewNow = new Interview(TypicalApplicants.IDA,
                "SWE",
                LocalDateTime.of(todayYear, todayMonth, todayDay, 9, 11),
                LocalDateTime.of(todayYear, todayMonth, todayDay, 11, 11)
        );
        interviewList.add(interviewNow);
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        uniqueInterviewList.setInterviews(interviewList);
        List<Interview> expected = new ArrayList<>();
        List<Interview> actual = Time.listInterviewsToday(uniqueInterviewList);
        assertEquals(expected, actual);
    }

    /*
     * Tests for the listPocketsOfTimeOnGivenDay method
     */
    @Test
    void testListPocketsOfFreeTime() {
        List<Interview> interviewList = TypicalInterviews.getTypicalInterviews();
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        uniqueInterviewList.setInterviews(interviewList);
        LocalDateTime day = LocalDateTime.of(2024, 12, 21, 0, 0);
        List<List<LocalDateTime>> expected = new ArrayList<>();
        List<LocalDateTime> element = new ArrayList<>();
        element.add(LocalDateTime.of(2024, 12, 21, 9, 0));
        element.add(LocalDateTime.of(2024, 12, 21, 17, 0));
        expected.add(element);
        List<List<LocalDateTime>> actual = Time.listPocketsOfTimeOnGivenDay(day, uniqueInterviewList);
        assertEquals(expected, actual);
    }

    @Test
    void testListPocketsOfFreeTime2() {
        List<Interview> interviewList = TypicalInterviews.getTypicalInterviews();
        interviewList.add(new Interview(TypicalApplicants.HOON,
                "SWE",
                LocalDateTime.of(2024, 12, 21, 10, 0),
                LocalDateTime.of(2024, 12, 21, 11, 0)
        ));
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        uniqueInterviewList.setInterviews(interviewList);
        LocalDateTime day = LocalDateTime.of(2024, 12, 21, 0, 0);
        List<List<LocalDateTime>> expected = new ArrayList<>();
        List<LocalDateTime> element1 = new ArrayList<>();
        element1.add(LocalDateTime.of(2024, 12, 21, 9, 0));
        element1.add(LocalDateTime.of(2024, 12, 21, 10, 0));
        List<LocalDateTime> element2 = new ArrayList<>();
        element2.add(LocalDateTime.of(2024, 12, 21, 11, 0));
        element2.add(LocalDateTime.of(2024, 12, 21, 17, 0));
        expected.add(element1);
        expected.add(element2);
        List<List<LocalDateTime>> actual = Time.listPocketsOfTimeOnGivenDay(day, uniqueInterviewList);
        assertEquals(expected, actual);
    }

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
