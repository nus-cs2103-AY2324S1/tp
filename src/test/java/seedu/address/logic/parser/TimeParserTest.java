package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TimeParserTest {
    @Test
    void testDefaultDate() {
        assertEquals(LocalDateTime.of(1970, 1, 1, 0, 0), TimeParser.DEFAULT_DATE);
    }

    @Test
    void testDateFormatsArrayNotNull() {
        assertNotNull(TimeParser.dateFormats);
    }
}