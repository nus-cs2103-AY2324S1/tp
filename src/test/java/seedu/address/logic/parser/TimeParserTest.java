package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

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
            TimeParser.parseDate("Sunday", false);
        } catch (ParseException parseException) {
            assertEquals(parseException.getMessage(), "Please enter an interview time!");
        }
    }

    // TEST TOTALLY INVALID STRINGS
    @Test
    void testParseDateInvalidDateUnsuccessfulParse() {
        boolean hasError = false;
        try {
            TimeParser.parseDate("whenever I say so", false);
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
            TimeParser.parseDate("T 1630", false);
        } catch (ParseException ignored) {
            hasError = true;
        }
        assertTrue(hasError);
    }

    @Test
    void testParseDateDayIncompleteUnsuccessfulParse2() {
        boolean hasError = false;
        try {
            TimeParser.parseDate("Tu 1630", false);
        } catch (ParseException ignored) {
            hasError = true;
        }
        assertTrue(hasError);
    }

    // TESTS FOR VALID DATES WITH YEAR, MONTH, DAY OF MONTH, AND TIME
    @Test
    void testParseDateYearMonthDayTimeSuccessfulParse() throws ParseException {
        TimeParser.parseDate("21/12/2024 5pm", false);
    }

    @Test
    void testParseDateYearMonthDayTime2SuccessfulParse() throws ParseException {
        TimeParser.parseDate("21/12/24 1730", false);
    }

    @Test
    void testParseDateYearMonthDayTime3SuccessfulParse() throws ParseException {
        TimeParser.parseDate("21-12-2024 1730", false);
    }

    @Test
    void testParseDateYearMonthDayTime4SuccessfulParse() throws ParseException {
        TimeParser.parseDate("21-12-2024 1730pm", false);
    }

    @Test
    void testParseDateYearMonthDayTime5SuccessfulParse() throws ParseException {
        TimeParser.parseDate("15 Dec 2023 1.30pm", false);
    }

    @Test
    void testParseDateYearMonthDayTime6SuccessfulParse() throws ParseException {
        TimeParser.parseDate("31 mar 2099 1453", false);
    }

    @Test
    void testParseDateYearMonthDayTime7SuccessfulParse() throws ParseException {
        TimeParser.parseDate("12-12-2023 1647", false);
    }

    @Test
    void testParseDateYearMonthDayTime8SuccessfulParse() throws ParseException {
        TimeParser.parseDate("21/12/2024 5.30pm", false);
    }

    @Test
    void testParseDateYearMonthDayTime9SuccessfulParse() throws ParseException {
        TimeParser.parseDate("21/12/2024 5pm", false);
    }

    @Test
    void testParseDateYearMonthDayTime10SuccessfulParse() throws ParseException {
        TimeParser.parseDate("21/12/24 5.30pm", false);
    }

    @Test
    void testParseDateYearMonthDayTime11SuccessfulParse() throws ParseException {
        TimeParser.parseDate("21-12-2024 5pm", false);
    }

    // TESTS FOR VALID DATES WITH MONTH, DAY OF MONTH, AND TIME

    @Test
    void testParseDateMonthDayTime2SuccessfulParse() throws ParseException {
        TimeParser.parseDate("15 Jan 2.13pm", false);
    }

    @Test
    void testParseDateMonthDayTime3SuccessfulParse() throws ParseException {
        TimeParser.parseDate("15 Jan 1456", false);
    }

    @Test
    void testParseDateMonthDayTime5SuccessfulParse() throws ParseException {
        TimeParser.parseDate("15/01 1456", false);
    }

    @Test
    void testParseDateMonthDayTime6UnsuccessfulParse() {
        boolean hasError = false;
        try {
            TimeParser.parseDate("15/13 1456", false);
        } catch (ParseException ignored) {
            hasError = true;
        }
        assertTrue(hasError);
    }

    @Test
    void testParseDateMonthDayTime8SuccessfulParse() throws ParseException {
        TimeParser.parseDate("16 May 3.15pm", false);
    }

    // TESTS FOR VALID DATES BUT WITH MISSING TIMES
    @Test
    void testParseDayOnlyUnsuccessfulParse() {
        boolean hasError = false;
        try {
            TimeParser.parseDate("Tue", false);
        } catch (ParseException ignored) {
            hasError = true;
        }
        assertTrue(hasError);
    }

    @Test
    void testParseDayOnlyUnsuccessfulParse2() {
        boolean hasError = false;
        try {
            TimeParser.parseDate("Tuesday", false);
        } catch (ParseException ignored) {
            hasError = true;
        }
        assertTrue(hasError);
    }

    @Test
    void testParseDayOnlyUnsuccessfulParse3() {
        boolean hasError = false;
        try {
            TimeParser.parseDate("Tues", false);
        } catch (ParseException ignored) {
            hasError = true;
        }
        assertTrue(hasError);
    }
    @Test
    void testParseDayOnlyUnsuccessfulParse4() {
        boolean hasError = false;
        try {
            TimeParser.parseDate("Tuesd", false);
        } catch (ParseException ignored) {
            hasError = true;
        }
        assertTrue(hasError);
    }

    @Test
    void testParseDayOnlyUnsuccessfulParse5() {
        boolean hasError = false;
        try {
            TimeParser.parseDate("Tuesda", false);
        } catch (ParseException ignored) {
            hasError = true;
        }
        assertTrue(hasError);
    }

    @Test
    void testParseDateParseDateWithDdMmYyyyUnsuccessfulParse() {
        boolean hasError = false;
        try {
            TimeParser.parseDate("01/01/2099", false);
        } catch (ParseException ignored) {
            hasError = true;
        }
        assertTrue(hasError);
    }

    @Test
    void testParseDateParseDateWithPastDateUnsuccessfulParse() {
        boolean hasError = false;
        try {
            TimeParser.parseDate("01/01/1970", false);
        } catch (ParseException parseException) {
            hasError = true;
        }
        assertTrue(hasError);
    }
}
