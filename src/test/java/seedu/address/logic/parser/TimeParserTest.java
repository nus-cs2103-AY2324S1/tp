package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.interview.Interview;
import seedu.address.model.interview.UniqueInterviewList;
import seedu.address.testutil.TypicalApplicants;
import seedu.address.testutil.TypicalInterviews;

class TimeParserTest {
    private static final LocalDateTime DEFAULT_DATE =
            LocalDateTime.of(1970, 1, 1, 0, 0);
    private final Logger logger = Logger.getLogger("TimeParserTestLogger");

    /*
     * Tests for the timeParser class
     */
    @Test
    void testTimeParserDefaultDate() {
        logger.log(Level.INFO, "Tests that field does not disappear");
        assertEquals(DEFAULT_DATE, TimeParser.DEFAULT_DATE);
    }

    @Test
    void testTimeParser_arrayNotNull() {
        assertNotNull(TimeParser.DATE_FORMATS);
    }

    @Test
    void testMissingTimeErrorMessage() {
        try {
            TimeParser.parseDate("Sunday");
        } catch (ParseException parseException) {
            assertEquals(parseException.getMessage(), "Please enter an interview time!");
        }
    }

    @Test
    void testTimeErrorMessages() {
        try {
            TimeParser.parseDate("24/10/1988 1930");
        } catch (ParseException parseException) {
            assertEquals(parseException.getMessage(), "Please specify a valid date!");
        }
    }

    // TEST TOTALLY INVALID STRINGS
    @Test
    void testParseDateInvalidDateUnsuccessfulParse() {
        boolean hasError = false;
        try {
            TimeParser.parseDate("whenever I say so");
        } catch (ParseException parseException) {
            hasError = true;
        }
        assertTrue(hasError);
    }

    // TESTS FOR VALID DATES WITH DAY AND TIME
    @Test
    void testParseDateDayIncompleteUnsuccessfulParse() {
        boolean hasError = false;
        try {
            TimeParser.parseDate("T 1630");
        } catch (ParseException ignored) {
            hasError = true;
        }
        assertTrue(hasError);
    }

    @Test
    void testParseDateDayIncompleteUnsuccessfulParse2() {
        boolean hasError = false;
        try {
            TimeParser.parseDate("Tu 1630");
        } catch (ParseException ignored) {
            hasError = true;
        }
        assertTrue(hasError);
    }

    @Test
    void testParseDateDayAnd24hTimeWithMinutesSuccessfulParse() throws ParseException {
        TimeParser.parseDate("Tue 1630");
    }

    @Test
    void testParseDateDayAnd12hTimeWithMinutesSuccessfulParse() throws ParseException {
        TimeParser.parseDate("Tue 4.30pm");
    }

    // TODO: FIX THIS FROM PASSING
    @Test
    void testParseDateDayAnd12hTimeWithoutMinutesUnsuccessfulParse() throws ParseException {
        TimeParser.parseDate("Tue 4amdfjs");
    }

    @Test
    void testParseDateDayAnd12hTimeWithoutMinutesSuccessfulParse() throws ParseException {
        TimeParser.parseDate("Tue 4pm");
    }

    // TESTS FOR VALID DATES WITH YEAR, MONTH, DAY OF MONTH, AND TIME
    @Test
    void testParseDateYearMonthDayTimeSuccessfulParse() throws ParseException {
        TimeParser.parseDate("21/12/2024 5pm");
    }

    @Test
    void testParseDateYearMonthDayTime2SuccessfulParse() throws ParseException {
        TimeParser.parseDate("21/12/24 1730");
    }

    @Test
    void testParseDateYearMonthDayTime3SuccessfulParse() throws ParseException {
        TimeParser.parseDate("21-12-2024 1730");
    }

    @Test
    void testParseDateYearMonthDayTime4SuccessfulParse() throws ParseException {
        TimeParser.parseDate("21-12-2024 1730pm");
    }

    @Test
    void testParseDateYearMonthDayTime5SuccessfulParse() throws ParseException {
        TimeParser.parseDate("15 Dec 2023 1.30pm");
    }

    @Test
    void testParseDateYearMonthDayTime6SuccessfulParse() throws ParseException {
        TimeParser.parseDate("31 mar 2099 1453");
    }

    @Test
    void testParseDateYearMonthDayTime7SuccessfulParse() throws ParseException {
        TimeParser.parseDate("12-12-2023 1647");
    }

    @Test
    void testParseDateYearMonthDayTime8SuccessfulParse() throws ParseException {
        TimeParser.parseDate("21/12/2024 5.30pm");
    }

    @Test
    void testParseDateYearMonthDayTime9SuccessfulParse() throws ParseException {
        TimeParser.parseDate("21/12/2024 5pm");
    }

    @Test
    void testParseDateYearMonthDayTime10SuccessfulParse() throws ParseException {
        TimeParser.parseDate("21/12/24 5.30pm");
    }

    @Test
    void testParseDateYearMonthDayTime11SuccessfulParse() throws ParseException {
        TimeParser.parseDate("21-12-2024 5pm");
    }

    // TESTS FOR VALID DATES WITH MONTH, DAY OF MONTH, AND TIME

    @Test
    void testParseDateMonthDayTime2SuccessfulParse() throws ParseException {
        TimeParser.parseDate("15 Jan 2.13pm");
    }

    @Test
    void testParseDateMonthDayTime3SuccessfulParse() throws ParseException {
        TimeParser.parseDate("15 Jan 1456");
    }

    @Test
    void testParseDateMonthDayTime5SuccessfulParse() throws ParseException {
        TimeParser.parseDate("15/01 1456");
    }

    @Test
    void testParseDateMonthDayTime6UnsuccessfulParse() {
        boolean hasError = false;
        try {
            TimeParser.parseDate("15/13 1456");
        } catch (ParseException ignored) {
            hasError = true;
        }
        assertTrue(hasError);
    }

    @Test
    void testParseDateMonthDayTime8SuccessfulParse() throws ParseException {
        TimeParser.parseDate("16 May 3.15pm");
    }

    // TESTS FOR VALID DATES BUT WITH MISSING TIMES
    @Test
    void testParseDayOnlyUnsuccessfulParse() {
        boolean hasError = false;
        try {
            TimeParser.parseDate("Tue");
        } catch (ParseException ignored) {
            hasError = true;
        }
        assertTrue(hasError);
    }

    @Test
    void testParseDayOnlyUnsuccessfulParse2() {
        boolean hasError = false;
        try {
            TimeParser.parseDate("Tuesday");
        } catch (ParseException ignored) {
            hasError = true;
        }
        assertTrue(hasError);
    }

    @Test
    void testParseDayOnlyUnsuccessfulParse3() {
        boolean hasError = false;
        try {
            TimeParser.parseDate("Tues");
        } catch (ParseException ignored) {
            hasError = true;
        }
        assertTrue(hasError);
    }
    @Test
    void testParseDayOnlyUnsuccessfulParse4() {
        boolean hasError = false;
        try {
            TimeParser.parseDate("Tuesd");
        } catch (ParseException ignored) {
            hasError = true;
        }
        assertTrue(hasError);
    }

    @Test
    void testParseDayOnlyUnsuccessfulParse5() {
        boolean hasError = false;
        try {
            TimeParser.parseDate("Tuesda");
        } catch (ParseException ignored) {
            hasError = true;
        }
        assertTrue(hasError);
    }

    @Test
    void testParseDateParseDateWithDdMmYyyyUnsuccessfulParse() {
        boolean hasError = false;
        try {
            TimeParser.parseDate("01/01/2099");
        } catch (ParseException ignored) {
            hasError = true;
        }
        assertTrue(hasError);
    }

    @Test
    void testParseDateParseDateWithPastDateUnsuccessfulParse() {
        boolean hasError = false;
        try {
            TimeParser.parseDate("01/01/1970");
        } catch (ParseException parseException) {
            hasError = true;
        }
        assertTrue(hasError);
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
        List<List<LocalDateTime>> actual = TimeParser.listPocketsOfTimeOnGivenDay(day, uniqueInterviewList);
        assertEquals(expected, actual);
    }

    /*
     * Tests for the listInterviewClashes class
     */
    @Test
    void testListInterviewClashesListFirstElement() {
        List<Interview> interviewList = TypicalInterviews.getTypicalInterviews();
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        uniqueInterviewList.setInterviews(interviewList);
        LocalDateTime startTime = LocalDateTime.of(2024, 12, 21, 20, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 12, 21, 20, 30);
        List<Interview> expected = new ArrayList<>();
        expected.add(TypicalInterviews.STANDARD_INTERVIEW);
        List<Interview> actual = TimeParser.listInterviewClashes(startTime, endTime, uniqueInterviewList);
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
        List<List<LocalDateTime>> actual = TimeParser.listPocketsOfTimeOnGivenDay(day, uniqueInterviewList);
        assertEquals(expected, actual);
    }

    void testListInterviewClashesListFirstElement2() {
        List<Interview> interviewList = TypicalInterviews.getTypicalInterviews();
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        uniqueInterviewList.setInterviews(interviewList);
        LocalDateTime startTime = LocalDateTime.of(2024, 12, 21, 18, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 12, 21, 22, 0);
        List<Interview> expected = new ArrayList<>();
        expected.add(TypicalInterviews.STANDARD_INTERVIEW);
        List<Interview> actual = TimeParser.listInterviewClashes(startTime, endTime, uniqueInterviewList);
        assertEquals(expected, actual);
    }

    @Test
    void testListInterviewClashesListFirstElement3() {
        List<Interview> interviewList = TypicalInterviews.getTypicalInterviews();
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        uniqueInterviewList.setInterviews(interviewList);
        LocalDateTime startTime = LocalDateTime.of(2024, 12, 21, 18, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 12, 21, 19, 1);
        List<Interview> expected = new ArrayList<>();
        expected.add(TypicalInterviews.STANDARD_INTERVIEW);
        List<Interview> actual = TimeParser.listInterviewClashes(startTime, endTime, uniqueInterviewList);
        assertEquals(expected, actual);
    }

    @Test
    void testListInterviewClashesListFirstElement4() {
        List<Interview> interviewList = TypicalInterviews.getTypicalInterviews();
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        uniqueInterviewList.setInterviews(interviewList);
        LocalDateTime startTime = LocalDateTime.of(2024, 12, 21, 20, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 12, 21, 22, 0);
        List<Interview> expected = new ArrayList<>();
        expected.add(TypicalInterviews.STANDARD_INTERVIEW);
        List<Interview> actual = TimeParser.listInterviewClashes(startTime, endTime, uniqueInterviewList);
        assertEquals(expected, actual);
    }

    @Test
    void testListInterviewClashesListTwoClashes() {
        List<Interview> interviewList = TypicalInterviews.getTypicalInterviews();
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        uniqueInterviewList.setInterviews(interviewList);
        LocalDateTime startTime = LocalDateTime.of(2024, 7, 12, 9, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 12, 21, 22, 0);
        List<Interview> expected = new ArrayList<>();
        expected.add(TypicalInterviews.STANDARD_INTERVIEW);
        expected.add(TypicalInterviews.STANDARD_INTERVIEW_4);
        List<Interview> actual = TimeParser.listInterviewClashes(startTime, endTime, uniqueInterviewList);
        assertEquals(expected, actual);
    }

    @Test
    void testListInterviewClashesListThreeClashes() {
        List<Interview> interviewList = TypicalInterviews.getTypicalInterviews();
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        uniqueInterviewList.setInterviews(interviewList);
        LocalDateTime startTime = LocalDateTime.of(2024, 5, 12, 9, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 12, 21, 22, 0);
        List<Interview> expected = new ArrayList<>();
        expected.add(TypicalInterviews.STANDARD_INTERVIEW);
        expected.add(TypicalInterviews.STANDARD_INTERVIEW_3);
        expected.add(TypicalInterviews.STANDARD_INTERVIEW_4);
        List<Interview> actual = TimeParser.listInterviewClashes(startTime, endTime, uniqueInterviewList);
        assertEquals(expected, actual);
    }

    @Test
    void testListInterviewClashesListThreeClashes2() {
        List<Interview> interviewList = TypicalInterviews.getTypicalInterviews();
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        uniqueInterviewList.setInterviews(interviewList);
        LocalDateTime startTime = LocalDateTime.of(2023, 5, 12, 9, 0);
        LocalDateTime endTime = LocalDateTime.of(2025, 12, 21, 14, 0);
        List<Interview> expected = new ArrayList<>();
        expected.add(TypicalInterviews.STANDARD_INTERVIEW);
        expected.add(TypicalInterviews.STANDARD_INTERVIEW_3);
        expected.add(TypicalInterviews.STANDARD_INTERVIEW_4);
        List<Interview> actual = TimeParser.listInterviewClashes(startTime, endTime, uniqueInterviewList);
        assertEquals(expected, actual);
    }

    @Test
    void testListInterviewClashesListFourClashes() {
        List<Interview> interviewList = TypicalInterviews.getTypicalInterviews();
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        uniqueInterviewList.setInterviews(interviewList);
        LocalDateTime startTime = LocalDateTime.of(2023, 5, 12, 9, 0);
        LocalDateTime endTime = LocalDateTime.of(2025, 12, 21, 22, 0);
        List<Interview> expected = new ArrayList<>();
        expected.add(TypicalInterviews.STANDARD_INTERVIEW);
        expected.add(TypicalInterviews.STANDARD_INTERVIEW_2);
        expected.add(TypicalInterviews.STANDARD_INTERVIEW_3);
        expected.add(TypicalInterviews.STANDARD_INTERVIEW_4);
        List<Interview> actual = TimeParser.listInterviewClashes(startTime, endTime, uniqueInterviewList);
        assertEquals(expected, actual);
    }

    @Test
    void testListInterviewClashesListNoClashes() {
        List<Interview> interviewList = TypicalInterviews.getTypicalInterviews();
        UniqueInterviewList uniqueInterviewList = new UniqueInterviewList();
        uniqueInterviewList.setInterviews(interviewList);
        LocalDateTime startTime = LocalDateTime.of(2023, 5, 12, 9, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 12, 21, 22, 0);
        List<Interview> expected = new ArrayList<>();
        List<Interview> actual = TimeParser.listInterviewClashes(startTime, endTime, uniqueInterviewList);
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
        List<Interview> actual = TimeParser.listInterviewsToday(uniqueInterviewList);
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
        List<Interview> actual = TimeParser.listInterviewsToday(uniqueInterviewList);
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
        List<Interview> actual = TimeParser.listInterviewsToday(uniqueInterviewList);
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
        List<Interview> actual = TimeParser.listInterviewsToday(uniqueInterviewList);
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
        List<Interview> actual = TimeParser.listInterviewsToday(uniqueInterviewList);
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
        List<Interview> actual = TimeParser.listInterviewsToday(uniqueInterviewList);
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
        List<Interview> actual = TimeParser.listInterviewsToday(uniqueInterviewList);
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
        List<Interview> actual = TimeParser.sortInterviewsInChronologicalAscendingOrder(uniqueInterviewList);
        assertEquals(expected, actual);
    }
}
