package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class DateTimeUtilTest {
    @Test
    public void test_correctFormat_pass() {
        assertEquals(DateTimeUtil.parseString("12:00"), LocalDateTime.now()
                .withHour(12).withMinute(0).withSecond(0).withNano(0));
        assertEquals(DateTimeUtil.parseString("02:00"), LocalDateTime.now()
                .withHour(2).withMinute(0).withSecond(0).withNano(0));
        assertEquals(DateTimeUtil.parseString("02:01"), LocalDateTime.now()
                .withHour(2).withMinute(1).withSecond(0).withNano(0));
        assertEquals(DateTimeUtil.parseString("02:10"), LocalDateTime.now()
                .withHour(2).withMinute(10).withSecond(0).withNano(0));
        assertEquals(DateTimeUtil.parseString("00:10"), LocalDateTime.now()
                .withHour(0).withMinute(10).withSecond(0).withNano(0));
        assertEquals(DateTimeUtil.parseString("05:01:45"), LocalDateTime.now()
                .withHour(5).withMinute(1).withSecond(45).withNano(0));
        assertEquals(DateTimeUtil.parseString("2023-10-12 02:10"),
                LocalDateTime.parse("2023-10-12T02:10:00"));
        assertEquals(DateTimeUtil.parseString("2023-10-12 20:05"),
                LocalDateTime.parse("2023-10-12T20:05:00"));
        assertEquals(DateTimeUtil.parseString("2023-10-12 20:05:30"),
                LocalDateTime.parse("2023-10-12T20:05:30"));
        assertEquals(DateTimeUtil.parseString("2023-10-12"),
                LocalDateTime.parse("2023-10-12T00:00:00"));
    }

    @Test
    public void test_wrongFormat_fails() {
        assertThrows(DateTimeParseException.class, () -> DateTimeUtil.parseString("002:00"));
        assertThrows(DateTimeParseException.class, () -> DateTimeUtil.parseString("02:0"));
        assertThrows(DateTimeParseException.class, () -> DateTimeUtil.parseString("12:1"));
        assertThrows(DateTimeParseException.class, () -> DateTimeUtil.parseString("2023-10-12 0:0:0"));
    }
}
