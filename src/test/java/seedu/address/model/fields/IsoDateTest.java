package seedu.address.model.fields;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class IsoDateTest {

    private final String invalidDate = "1111-11-40 11:50";
    private final String invalidDate2 = "1111-11-40";

    private final String validDate = "2025-11-11 11:50";
    private final String validDate2 = "2025-11-11 11:51";
    private final String validDate3 = "2025-11-11";

    @Test
    public void isValidDate() {
        // Date with time
        assertTrue(IsoDate.isValidDateNotBeforeToday(validDate));
        assertFalse(IsoDate.isValidDateNotBeforeToday(invalidDate));

        //Date without time
        assertTrue(IsoDate.isValidIsoDateWithoutTimeAfterCurrent(validDate3));
        assertFalse(IsoDate.isValidIsoDateWithoutTimeAfterCurrent(invalidDate2));

        // 29 Feb test
        assertFalse(IsoDate.isValidIsoDate("2023-02-29 23:59"));
        assertFalse(IsoDate.isValidIsoDateWithoutTime("2023-02-29"));
    }

    @Test
    public void returnsCorrectSaveData() {
        Date d = new IsoDate(LocalDateTime.parse(validDate, DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT)));
        assertEquals(validDate, d.toSaveData());
        assertNotEquals(invalidDate, d.toSaveData());
    }

    @Test
    public void equals() {
        Date d = new IsoDate(LocalDateTime.parse(validDate, DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT)));

        assertNotEquals(null, d);

        assertNotEquals(d, new IsoDate(LocalDateTime.parse(validDate2,
                DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))));

        assertEquals(d, new IsoDate(LocalDateTime.parse(validDate, DateTimeFormatter.ofPattern(IsoDate.DATE_FORMAT))));

        assertEquals(d, d);
    }
}
