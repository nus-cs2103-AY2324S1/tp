package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

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
