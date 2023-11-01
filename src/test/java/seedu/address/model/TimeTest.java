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
     * Tests for the listInterviewClashes class
     */
    @Test
    void testListInterviewClashesListFirstElement() {
        List<Interview> interviewList = TypicalInterviews.getTypicalInterviews();
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        uniqueInterviewList.setInterviews(interviewList);
        Time startTime = new Time(LocalDateTime.of(2024, 12, 21, 20, 0));
        Time endTime = new Time(LocalDateTime.of(2024, 12, 21, 20, 30));
        List<Interview> expected = new ArrayList<>();
        expected.add(TypicalInterviews.STANDARD_INTERVIEW);
        List<Interview> actual = Time.listInterviewClashes(startTime, endTime, uniqueInterviewList);
        assertEquals(expected, actual);
    }

    @Test
    void testListInterviewClashesListFirstElement2() {
        List<Interview> interviewList = TypicalInterviews.getTypicalInterviews();
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        uniqueInterviewList.setInterviews(interviewList);
        Time startTime = new Time(LocalDateTime.of(2024, 12, 21, 18, 0));
        Time endTime = new Time(LocalDateTime.of(2024, 12, 21, 22, 0));
        List<Interview> expected = new ArrayList<>();
        expected.add(TypicalInterviews.STANDARD_INTERVIEW);
        List<Interview> actual = Time.listInterviewClashes(startTime, endTime, uniqueInterviewList);
        assertEquals(expected, actual);
    }

    @Test
    void testListInterviewClashesListFirstElement3() {
        List<Interview> interviewList = TypicalInterviews.getTypicalInterviews();
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        uniqueInterviewList.setInterviews(interviewList);
        Time startTime = new Time(LocalDateTime.of(2024, 12, 21, 18, 0));
        Time endTime = new Time(LocalDateTime.of(2024, 12, 21, 19, 1));
        List<Interview> expected = new ArrayList<>();
        expected.add(TypicalInterviews.STANDARD_INTERVIEW);
        List<Interview> actual = Time.listInterviewClashes(startTime, endTime, uniqueInterviewList);
        assertEquals(expected, actual);
    }

    @Test
    void testListInterviewClashesListFirstElement4() {
        List<Interview> interviewList = TypicalInterviews.getTypicalInterviews();
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        uniqueInterviewList.setInterviews(interviewList);
        Time startTime = new Time(LocalDateTime.of(2024, 12, 21, 20, 0));
        Time endTime = new Time(LocalDateTime.of(2024, 12, 21, 22, 0));
        List<Interview> expected = new ArrayList<>();
        expected.add(TypicalInterviews.STANDARD_INTERVIEW);
        List<Interview> actual = Time.listInterviewClashes(startTime, endTime, uniqueInterviewList);
        assertEquals(expected, actual);
    }

    // test case 1: the current interview is completely within the interview to be added
    @Test
    void testListInterviewClashesListFirstElement5() {
        List<Interview> interviewList = TypicalInterviews.getTypicalInterviews();
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        uniqueInterviewList.setInterviews(interviewList);
        Time startTime = new Time(LocalDateTime.of(2024, 12, 21, 18, 59));
        Time endTime = new Time(LocalDateTime.of(2024, 12, 21, 21, 1));
        List<Interview> expected = new ArrayList<>();
        expected.add(TypicalInterviews.STANDARD_INTERVIEW);
        List<Interview> actual = Time.listInterviewClashes(startTime, endTime, uniqueInterviewList);
        assertEquals(expected, actual);
    }

    // test case 2: the interview to be added is completely within the current interview
    @Test
    void testListInterviewClashesListFirstElement6() {
        List<Interview> interviewList = TypicalInterviews.getTypicalInterviews();
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        uniqueInterviewList.setInterviews(interviewList);
        Time startTime = new Time(LocalDateTime.of(2024, 12, 21, 19, 1));
        Time endTime = new Time(LocalDateTime.of(2024, 12, 21, 20, 59));
        List<Interview> expected = new ArrayList<>();
        expected.add(TypicalInterviews.STANDARD_INTERVIEW);
        List<Interview> actual = Time.listInterviewClashes(startTime, endTime, uniqueInterviewList);
        assertEquals(expected, actual);
    }

    // test case 3: the end time of the interview to be added falls within the current interview window
    @Test
    void testListInterviewClashesListFirstElement7() {
        List<Interview> interviewList = TypicalInterviews.getTypicalInterviews();
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        uniqueInterviewList.setInterviews(interviewList);
        Time startTime = new Time(LocalDateTime.of(2024, 12, 21, 14, 0));
        Time endTime = new Time(LocalDateTime.of(2024, 12, 21, 20, 59));
        List<Interview> expected = new ArrayList<>();
        expected.add(TypicalInterviews.STANDARD_INTERVIEW);
        List<Interview> actual = Time.listInterviewClashes(startTime, endTime, uniqueInterviewList);
        assertEquals(expected, actual);
    }

    // test case 4: the start time of the interview to be added falls within the current interview window
    @Test
    void testListInterviewClashesListFirstElement8() {
        List<Interview> interviewList = TypicalInterviews.getTypicalInterviews();
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        uniqueInterviewList.setInterviews(interviewList);
        Time startTime = new Time(LocalDateTime.of(2024, 12, 21, 19, 1));
        Time endTime = new Time(LocalDateTime.of(2024, 12, 21, 22, 0));
        List<Interview> expected = new ArrayList<>();
        expected.add(TypicalInterviews.STANDARD_INTERVIEW);
        List<Interview> actual = Time.listInterviewClashes(startTime, endTime, uniqueInterviewList);
        assertEquals(expected, actual);
    }

    // test case 5: the interview to be added has the exact same start and end time as the
    // current interview
    @Test
    void testListInterviewClashesListFirstElement9() {
        List<Interview> interviewList = TypicalInterviews.getTypicalInterviews();
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        uniqueInterviewList.setInterviews(interviewList);
        Time startTime = new Time(LocalDateTime.of(2024, 12, 21, 19, 0));
        Time endTime = new Time(LocalDateTime.of(2024, 12, 21, 21, 0));
        List<Interview> expected = new ArrayList<>();
        expected.add(TypicalInterviews.STANDARD_INTERVIEW);
        List<Interview> actual = Time.listInterviewClashes(startTime, endTime, uniqueInterviewList);
        assertEquals(expected, actual);
    }

    // test case 6: the interview to be added has an end time that is after the end time of the current interview
    // and the start time of the interview to be added has a start time equals to the current interview
    @Test
    void testListInterviewClashesListFirstElement10() {
        List<Interview> interviewList = TypicalInterviews.getTypicalInterviews();
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        uniqueInterviewList.setInterviews(interviewList);
        Time startTime = new Time(LocalDateTime.of(2024, 12, 21, 19, 0));
        Time endTime = new Time(LocalDateTime.of(2024, 12, 21, 22, 0));
        List<Interview> expected = new ArrayList<>();
        expected.add(TypicalInterviews.STANDARD_INTERVIEW);
        List<Interview> actual = Time.listInterviewClashes(startTime, endTime, uniqueInterviewList);
        assertEquals(expected, actual);
    }

    // test case 7: the interview to be added has a start time before the start time of the current interview
    // and the end time of the interview to be added has an end time equals to the current interview
    @Test
    void testListInterviewClashesListFirstElement11() {
        List<Interview> interviewList = TypicalInterviews.getTypicalInterviews();
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        uniqueInterviewList.setInterviews(interviewList);
        Time startTime = new Time(LocalDateTime.of(2024, 12, 21, 18, 59));
        Time endTime = new Time(LocalDateTime.of(2024, 12, 21, 21, 0));
        List<Interview> expected = new ArrayList<>();
        expected.add(TypicalInterviews.STANDARD_INTERVIEW);
        List<Interview> actual = Time.listInterviewClashes(startTime, endTime, uniqueInterviewList);
        assertEquals(expected, actual);
    }

    @Test
    void testListInterviewClashesListTwoClashes() {
        List<Interview> interviewList = TypicalInterviews.getTypicalInterviews();
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        uniqueInterviewList.setInterviews(interviewList);
        Time startTime = new Time(LocalDateTime.of(2024, 7, 12, 9, 0));
        Time endTime = new Time(LocalDateTime.of(2024, 12, 21, 22, 0));
        List<Interview> expected = new ArrayList<>();
        expected.add(TypicalInterviews.STANDARD_INTERVIEW);
        expected.add(TypicalInterviews.STANDARD_INTERVIEW_4);
        List<Interview> actual = Time.listInterviewClashes(startTime, endTime, uniqueInterviewList);
        assertEquals(expected, actual);
    }

    @Test
    void testListInterviewClashesListThreeClashes() {
        List<Interview> interviewList = TypicalInterviews.getTypicalInterviews();
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        uniqueInterviewList.setInterviews(interviewList);
        Time startTime = new Time(LocalDateTime.of(2024, 5, 12, 9, 0));
        Time endTime = new Time(LocalDateTime.of(2024, 12, 21, 22, 0));
        List<Interview> expected = new ArrayList<>();
        expected.add(TypicalInterviews.STANDARD_INTERVIEW);
        expected.add(TypicalInterviews.STANDARD_INTERVIEW_3);
        expected.add(TypicalInterviews.STANDARD_INTERVIEW_4);
        List<Interview> actual = Time.listInterviewClashes(startTime, endTime, uniqueInterviewList);
        assertEquals(expected, actual);
    }

    @Test
    void testListInterviewClashesListThreeClashes2() {
        List<Interview> interviewList = TypicalInterviews.getTypicalInterviews();
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        uniqueInterviewList.setInterviews(interviewList);
        Time startTime = new Time(LocalDateTime.of(2023, 5, 12, 9, 0));
        Time endTime = new Time(LocalDateTime.of(2025, 12, 21, 14, 0));
        List<Interview> expected = new ArrayList<>();
        expected.add(TypicalInterviews.STANDARD_INTERVIEW);
        expected.add(TypicalInterviews.STANDARD_INTERVIEW_3);
        expected.add(TypicalInterviews.STANDARD_INTERVIEW_4);
        List<Interview> actual = Time.listInterviewClashes(startTime, endTime, uniqueInterviewList);
        assertEquals(expected, actual);
    }

    @Test
    void testListInterviewClashesListFourClashes() {
        List<Interview> interviewList = TypicalInterviews.getTypicalInterviews();
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        uniqueInterviewList.setInterviews(interviewList);
        Time startTime = new Time(LocalDateTime.of(2023, 5, 12, 9, 0));
        Time endTime = new Time(LocalDateTime.of(2025, 12, 21, 22, 0));
        List<Interview> expected = new ArrayList<>();
        expected.add(TypicalInterviews.STANDARD_INTERVIEW);
        expected.add(TypicalInterviews.STANDARD_INTERVIEW_2);
        expected.add(TypicalInterviews.STANDARD_INTERVIEW_3);
        expected.add(TypicalInterviews.STANDARD_INTERVIEW_4);
        List<Interview> actual = Time.listInterviewClashes(startTime, endTime, uniqueInterviewList);
        assertEquals(expected, actual);
    }

    @Test
    void testListInterviewClashesListNoClashes() {
        List<Interview> interviewList = TypicalInterviews.getTypicalInterviews();
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        uniqueInterviewList.setInterviews(interviewList);
        Time startTime = new Time(LocalDateTime.of(2023, 5, 12, 9, 0));
        Time endTime = new Time(LocalDateTime.of(2023, 12, 21, 22, 0));
        List<Interview> expected = new ArrayList<>();
        List<Interview> actual = Time.listInterviewClashes(startTime, endTime, uniqueInterviewList);
        assertEquals(expected, actual);
    }

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
        Interview interviewNow = new Interview(TypicalApplicants.ALICE, "SWE", today, today);
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
        LocalDateTime today = LocalDateTime.now();
        int todayDay = today.getDayOfMonth();
        int todayMonth = today.getMonthValue();
        int todayYear = today.getYear();
        Interview interviewNow = new Interview(TypicalApplicants.ALICE,
                "SWE",
                LocalDateTime.of(todayYear, todayMonth, todayDay, 9, 11),
                LocalDateTime.of(todayYear, todayMonth, todayDay, 11, 11)
        );
        interviewList.add(interviewNow);
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        uniqueInterviewList.setInterviews(interviewList);
        List<Interview> expected = new ArrayList<>();
        expected.add(interviewNow);
        List<Interview> actual = Time.listInterviewsToday(uniqueInterviewList);
        assertEquals(expected, actual);
    }

    @Test
    void testListInterviewsToday4() {
        List<Interview> interviewList = TypicalInterviews.getTypicalInterviews();
        LocalDateTime today = LocalDateTime.now();
        int todayDay = 1;
        int todayMonth = today.getMonthValue();
        int todayYear = today.getYear();
        Interview interviewNow = new Interview(TypicalApplicants.ALICE,
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
        LocalDateTime today = LocalDateTime.now();
        int todayDay = 1;
        int todayMonth = today.getMonthValue();
        int todayYear = today.getYear();
        Interview interviewNow = new Interview(TypicalApplicants.ALICE,
                "SWE",
                LocalDateTime.of(todayYear, todayMonth + 1, todayDay, 9, 11),
                LocalDateTime.of(todayYear, todayMonth + 1, todayDay, 11, 11)
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
        LocalDateTime today = LocalDateTime.now();
        int todayDay = today.getDayOfMonth();
        int todayMonth = today.getMonthValue();
        int todayYear = today.getYear();
        Interview interviewNow = new Interview(TypicalApplicants.ALICE,
                "SWE",
                LocalDateTime.of(todayYear + 1, todayMonth, todayDay, 9, 11),
                LocalDateTime.of(todayYear + 1, todayMonth, todayDay, 11, 11)
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
        LocalDateTime today = LocalDateTime.now();
        int todayDay = today.getDayOfMonth();
        int todayMonth = today.getMonthValue();
        int todayYear = today.getYear();
        Interview interviewNow = new Interview(TypicalApplicants.ALICE,
                "SWE",
                LocalDateTime.of(todayYear, todayMonth, todayDay - 1, 9, 11),
                LocalDateTime.of(todayYear, todayMonth, todayDay - 1, 11, 11)
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
