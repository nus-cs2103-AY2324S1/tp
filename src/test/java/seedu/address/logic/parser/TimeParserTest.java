package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Time;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

class TimeParserTest {
    private static final LocalDateTime TEST_DATE =
            LocalDateTime.of(2099, 1, 1, 23, 59);
    private static final LocalDateTime DEFAULT_DATE =
            LocalDateTime.of(1970, 1, 1, 0, 0);

    @Test
    void testDefaultDate() {
        assertEquals(DEFAULT_DATE, TimeParser.DEFAULT_DATE);
    }

    @Test
    void testDateFormatsArrayNotNull() {
        assertNotNull(TimeParser.dateFormats);
    }

    @Test
    void testInvalidDate() {
        boolean hasError = false;
        try {
            TimeParser.parseDate("whenever I say so");
        } catch (ParseException parseException) {
            hasError = true;
        }
        assertTrue(hasError);
    }

    @Test
    void testDayAnd24hTime() throws ParseException {
        TimeParser.parseDate("Tue 1630");
    }

    @Test
    void testDayShortForm() throws ParseException {
        TimeParser.parseDate("Tue");
    }

    @Test
    void testDayLongForm() throws ParseException {
        TimeParser.parseDate("Tuesday");
    }

    @Test
    void testDayIncompleteValid() throws ParseException {
        TimeParser.parseDate("Tues");
        TimeParser.parseDate("Tuesd");
        TimeParser.parseDate("Tuesda");
    }

    @Test
    void testDayIncompleteInvalid() {
        boolean hasError = false;
        try {
            System.out.println(TimeParser.parseDate("T"));
        } catch (ParseException ignored) {
            hasError = true;
        }
        assertTrue(hasError);
    }

    @Test
    void testDayIncompleteInvalid2() {
        boolean hasError = false;
        try {
            TimeParser.parseDate("Tu");
        } catch (ParseException ignored) {
            hasError = true;
        }
        assertTrue(hasError);
    }

    @Test
    void testDayAnd12hTimeWithMinutes() throws ParseException {
        TimeParser.parseDate("Tue 4.30pm");
    }

    // TODO: FIX THIS FROM PASSING
    @Test
    void testDayAnd12hTimeWithoutMinutes() throws ParseException {
        TimeParser.parseDate("Tue 4pmdfjs");
    }

    @Test
    void testDayAnd12hTimeWithoutMinutes2() throws ParseException {
        TimeParser.parseDate("Tue 4pm");
    }

    @Test
    void testYearMonthDayTime() throws ParseException {
        TimeParser.parseDate("21/12/2024 5pm");
    }

    @Test
    void testYearMonthDayTime2() throws ParseException {
        TimeParser.parseDate("21-12-2024 5pm");
    }

    @Test
    void testYearMonthDayTime3() throws ParseException {
        TimeParser.parseDate("21-12-2024 1730");
    }

    @Test
    void testYearMonthDayTime4() throws ParseException {
        TimeParser.parseDate("21-12-2024 1730pm");
    }

    // TODO: FIX THIS FROM BECOMING 1.30AM
    @Test
    void testYearMonthDayTime5() throws ParseException {
        TimeParser.parseDate("nov 12 1.30pm 2023");
    }

    @Test
    void testYearMonthDayTime6() throws ParseException {
        TimeParser.parseDate("2023-12-12 1647");
    }

    @Test
    void testParseDateWithDdMmYyyy() throws ParseException {
        assertEquals(TEST_DATE, TimeParser.parseDate("01/01/2099"));
    }

    @Test
    void testParseDateWithPastDate() {
        boolean hasError = false;
        try {
            TimeParser.parseDate("01/01/1970");
        } catch (ParseException parseException) {
            hasError = true;
        }
        assertTrue(hasError);
    }

    @Test
    void testZeroId() throws ParseException {
        assertNotNull(TimeParser.parseDate("mon 2359"));
    }

    @Test
    void testOneId() throws ParseException {
        assertNotNull(TimeParser.parseDate("mon 2359"));
    }

    @Test
    void testTwoId() throws ParseException {
        assertNotNull(TimeParser.parseDate("23 jan 15"));
    }

    @Test
    void testThreeId() throws ParseException {
        assertNotNull(TimeParser.parseDate("dec 4 8.53pm"));
    }

    @Test
    void testFourId() throws ParseException {
        assertNotNull(TimeParser.parseDate("dec 4"));
    }
}
