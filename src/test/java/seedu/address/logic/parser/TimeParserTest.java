package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

class TimeParserTest {
    private static final LocalDateTime DEFAULT_DATE =
            LocalDateTime.of(1970, 1, 1, 0, 0);

    // TESTS FOR THE TIMEPARSER STATIC FIELDS
    @Test
    void testTimeParserDefaultDate() {
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
        System.out.println(TimeParser.parseDate("15 Dec 2023 1.30pm"));
    }

    @Test
    void testParseDateYearMonthDayTime6SuccessfulParse() throws ParseException {
        System.out.println(TimeParser.parseDate("31 mar 2099 1453"));
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
    void testParseDateYearMonthDayTime11SuccessfulParse() {
        boolean hasError = false;
        try {
            TimeParser.parseDate("21-12-2024 5pm");
        } catch (ParseException ignored) {
            hasError = true;
        }
        assertTrue(hasError);
    }

    // TESTS FOR VALID DATES WITH MONTH, DAY OF MONTH, AND TIME
    @Test
    void testParseDateMonthDayTimeSuccessfulParse() throws ParseException {
        TimeParser.parseDate("Jan 15 2pm");
    }

    @Test
    void testParseDateMonthDayTime2SuccessfulParse() throws ParseException {
        TimeParser.parseDate("15 Jan 2.13pm");
    }

    @Test
    void testParseDateMonthDayTime3SuccessfulParse() throws ParseException {
        TimeParser.parseDate("15 Jan 1456");
    }

    @Test
    void testParseDateMonthDayTime4SuccessfulParse() throws ParseException {
        TimeParser.parseDate("Jan 15 1456");
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
    void testParseDateMonthDayTime7SuccessfulParse() throws ParseException {
        TimeParser.parseDate("May 16 3.15pm");
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
}
