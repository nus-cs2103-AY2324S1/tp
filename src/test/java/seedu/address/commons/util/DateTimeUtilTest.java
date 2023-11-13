package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.commons.util.DateTimeUtil.FORMATTER;
import static seedu.address.commons.util.DateTimeUtil.VERBOSE_FORMATTER;
import static seedu.address.commons.util.DateTimeUtil.format;
import static seedu.address.commons.util.DateTimeUtil.parse;
import static seedu.address.commons.util.DateTimeUtil.verbose;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class DateTimeUtilTest {

    private String testString = "20.09.2023 1200";
    private LocalDateTime testDateTime = LocalDateTime.parse("20.09.2023 1200", FORMATTER);
    private String testVerbose = testDateTime.format(VERBOSE_FORMATTER);

    @Test
    public void format_success() {
        assertEquals(format(testDateTime), testString);
    }

    @Test
    public void verbose_success() {
        assertEquals(verbose(testDateTime), testVerbose);
    }

    @Test
    public void parse_success() {
        assertEquals(parse(testString), testDateTime);
    }

    @Test
    public void parse_badInput_throwsDateTimeParseException() {
        assertThrows(DateTimeParseException.class, ()->parse(""));
    }

}
