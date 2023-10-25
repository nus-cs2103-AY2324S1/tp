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
    void testTimeParser_DefaultDate() {
        assertEquals(DEFAULT_DATE, TimeParser.DEFAULT_DATE);
    }

    @Test
    void testTimeParser_arrayNotNull() {
        assertNotNull(TimeParser.dateFormats);
    }

    @Test
    void testParseDate_InvalidDate_unsuccessfulParse() {
        boolean hasError = false;
        try {
            TimeParser.parseDate("whenever I say so");
        } catch (ParseException parseException) {
            hasError = true;
        }
        assertTrue(hasError);
    }

    @Test
    void testParseDate_DayAnd24hTime_successfulParse() throws ParseException {
        TimeParser.parseDate("Tue 1630");
    }

    @Test
    void testParseDate_DayShortForm() throws ParseException {
        TimeParser.parseDate("Tue");
    }

    @Test
    void testParseDate_DayLongForm() throws ParseException {
        TimeParser.parseDate("Tuesday");
    }

    @Test
    void testParseDate_DayIncompleteValid() throws ParseException {
        TimeParser.parseDate("Tues");
        TimeParser.parseDate("Tuesd");
        TimeParser.parseDate("Tuesda");
    }

    @Test
    void testParseDate_DayIncompleteInvalid_unsuccessfulParse() {
        boolean hasError = false;
        try {
            System.out.println(TimeParser.parseDate("T"));
        } catch (ParseException ignored) {
            hasError = true;
        }
        assertTrue(hasError);
    }

    @Test
    void testParseDate_DayIncompleteInvalid2() {
        boolean hasError = false;
        try {
            TimeParser.parseDate("Tu");
        } catch (ParseException ignored) {
            hasError = true;
        }
        assertTrue(hasError);
    }

    @Test
    void testParseDate_DayAnd12hTimeWithMinutes_successfulParse() throws ParseException {
        TimeParser.parseDate("Tue 4.30pm");
    }

    // TODO: FIX THIS FROM PASSING
    @Test
    void testParseDate_DayAnd12hTimeWithoutMinutes() throws ParseException {
        TimeParser.parseDate("Tue 4pmdfjs");
    }

    @Test
    void testParseDate_DayAnd12hTimeWithoutMinutes2_successfulParse() throws ParseException {
        TimeParser.parseDate("Tue 4pm");
    }

    @Test
    void testParseDate_YearMonthDayTime_successfulParse() throws ParseException {
        TimeParser.parseDate("21/12/2024 5pm");
    }

    @Test
    void testParseDate_YearMonthDayTime2_successfulParse() throws ParseException {
        TimeParser.parseDate("21-12-2024 5pm");
    }

    @Test
    void testParseDate_YearMonthDayTime3_successfulParse() throws ParseException {
        TimeParser.parseDate("21-12-2024 1730");
    }

    @Test
    void testParseDate_YearMonthDayTime4_successfulParse() throws ParseException {
        TimeParser.parseDate("21-12-2024 1730pm");
    }

    // TODO: FIX THIS FROM BECOMING 1.30AM
    @Test
    void testParseDate_YearMonthDayTime5_successfulParse() throws ParseException {
        TimeParser.parseDate("nov 12 1.30pm 2023");
    }

    @Test
    void testParseDate_YearMonthDayTime6_successfulParse() throws ParseException {
        TimeParser.parseDate("2023-12-12 1647");
    }

    @Test
    void testParseDate_ParseDateWithDdMmYyyy() throws ParseException {
        assertEquals(TEST_DATE, TimeParser.parseDate("01/01/2099"));
    }

    @Test
    void testParseDate_ParseDateWithPastDate() {
        boolean hasError = false;
        try {
            TimeParser.parseDate("01/01/1970");
        } catch (ParseException parseException) {
            hasError = true;
        }
        assertTrue(hasError);
    }

    @Test
    void testParseDate_ZeroId() throws ParseException {
        assertNotNull(TimeParser.parseDate("mon 2359"));
    }

    @Test
    void testParseDate_OneId() throws ParseException {
        assertNotNull(TimeParser.parseDate("mon 2359"));
    }
}
