package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

class TimeParserTest {
    private static final LocalDateTime TEST_DATE =
            LocalDateTime.of(2099, 1, 1, 23, 59);
    private static final LocalDateTime DEFAULT_DATE =
            LocalDateTime.of(1970, 1, 1, 0, 0);

    @Test
    void testTimeParserDefaultDate() {
        assertEquals(DEFAULT_DATE, TimeParser.DEFAULT_DATE);
    }

    @Test
    void testTimeParser_arrayNotNull() {
        assertNotNull(TimeParser.dateFormats);
    }

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

    @Test
    void testParseDateDayAnd24hTimeSuccessfulParse() throws ParseException {
        TimeParser.parseDate("Tue 1630");
    }

    @Test
    void testParseDateDayShortForm() throws ParseException {
        TimeParser.parseDate("Tue");
    }

    @Test
    void testParseDateDayLongForm() throws ParseException {
        TimeParser.parseDate("Tuesday");
    }

    @Test
    void testParseDateDayIncompleteValid() throws ParseException {
        TimeParser.parseDate("Tues");
        TimeParser.parseDate("Tuesd");
        TimeParser.parseDate("Tuesda");
    }

    @Test
    void testParseDateDayIncompleteInvalidunsuccessfulParse() {
        boolean hasError = false;
        try {
            System.out.println(TimeParser.parseDate("T"));
        } catch (ParseException ignored) {
            hasError = true;
        }
        assertTrue(hasError);
    }

    @Test
    void testParseDateDayIncompleteInvalid2() {
        boolean hasError = false;
        try {
            TimeParser.parseDate("Tu");
        } catch (ParseException ignored) {
            hasError = true;
        }
        assertTrue(hasError);
    }

    @Test
    void testParseDateDayAnd12hTimeWithMinutesSuccessfulParse() throws ParseException {
        TimeParser.parseDate("Tue 4.30pm");
    }

    // TODO: FIX THIS FROM PASSING
    @Test
    void testParseDateDayAnd12hTimeWithoutMinutes() throws ParseException {
        TimeParser.parseDate("Tue 4pmdfjs");
    }

    @Test
    void testParseDateDayAnd12hTimeWithoutMinutes2SuccessfulParse() throws ParseException {
        TimeParser.parseDate("Tue 4pm");
    }

    @Test
    void testParseDateYearMonthDayTimeSuccessfulParse() throws ParseException {
        TimeParser.parseDate("21/12/2024 5pm");
    }

    @Test
    void testParseDateYearMonthDayTime2SuccessfulParse() throws ParseException {
        TimeParser.parseDate("21-12-2024 5pm");
    }

    @Test
    void testParseDateYearMonthDayTime3SuccessfulParse() throws ParseException {
        TimeParser.parseDate("21-12-2024 1730");
    }

    @Test
    void testParseDateYearMonthDayTime4SuccessfulParse() throws ParseException {
        TimeParser.parseDate("21-12-2024 1730pm");
    }

    // TODO: FIX THIS FROM BECOMING 1.30AM
    @Test
    void testParseDateYearMonthDayTime5SuccessfulParse() throws ParseException {
        TimeParser.parseDate("nov 12 1.30pm 2023");
    }

    @Test
    void testParseDateYearMonthDayTime6SuccessfulParse() throws ParseException {
        TimeParser.parseDate("2023-12-12 1647");
    }

    @Test
    void testParseDateParseDateWithDdMmYyyy() throws ParseException {
        assertEquals(TEST_DATE, TimeParser.parseDate("01/01/2099"));
    }

    @Test
    void testParseDateParseDateWithPastDate() {
        boolean hasError = false;
        try {
            TimeParser.parseDate("01/01/1970");
        } catch (ParseException parseException) {
            hasError = true;
        }
        assertTrue(hasError);
    }

    @Test
    void testParseDateZeroId() throws ParseException {
        assertNotNull(TimeParser.parseDate("mon 2359"));
    }

    @Test
    void testParseDateOneId() throws ParseException {
        assertNotNull(TimeParser.parseDate("mon 2359"));
    }
}
