package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class TimeEndTest {

    @Test
    public void constructor_nullString_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TimeEnd((String) null));
    }

    @Test
    public void constructor_nullLocalDateTime_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TimeEnd((LocalDateTime) null));
    }

    @Test
    public void constructor_invalidTime_throwsIllegalArgumentException() {
        String invalidTime = "Invalid Time";
        assertThrows(DateTimeParseException.class, () -> new TimeEnd(LocalDateTime.parse(invalidTime,
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))));
    }

    @Test
    public void equals() {
        LocalDateTime time = LocalDateTime.parse("23-09-2023 16:40", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        TimeEnd timeEnd = new TimeEnd(time);

        // same values -> returns true
        assertTrue(timeEnd.equals(new TimeEnd(time)));

        // same object -> returns true
        assertTrue(timeEnd.equals(timeEnd));

        // null -> returns false
        assertFalse(timeEnd.equals(null));

        // different types -> returns false
        assertFalse(timeEnd.equals(5.0f));

        // different values -> returns false
        LocalDateTime differentTime = LocalDateTime.parse("24-09-2023 16:40",
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        assertFalse(timeEnd.equals(new TimeEnd(differentTime)));
    }
}
