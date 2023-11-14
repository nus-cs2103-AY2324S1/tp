package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;


public class TimeStartTest {

    @Test
    public void constructor_nullString_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TimeStart((String) null));
    }

    @Test
    public void constructor_nullLocalDateTime_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TimeStart((LocalDateTime) null));
    }

    @Test
    public void constructor_invalidTime_throwsIllegalArgumentException() {
        String invalidTime = "Invalid Time";
        assertThrows(DateTimeParseException.class, () -> new TimeStart(LocalDateTime.parse(invalidTime,
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))));
    }

    @Test
    public void isBefore_earlierTime_returnsTrue() {
        // Arrange
        LocalDateTime earlierTime = LocalDateTime.now();
        TimeStart timeStart = new TimeStart(earlierTime);
        LocalDateTime laterTime = LocalDateTime.now().plusHours(1);

        // Act
        boolean result = timeStart.isBefore(new TimeStart(laterTime));

        // Assert
        assertTrue(result);
    }

    @Test
    public void isBefore_sameTime_returnsFalse() {
        // Arrange
        LocalDateTime earlierTime = LocalDateTime.now();
        TimeStart timeStart = new TimeStart(earlierTime);

        // Act
        boolean result = timeStart.isBefore(new TimeStart(earlierTime));

        // Assert
        assertFalse(result);
    }

    @Test
    public void equals() {
        LocalDateTime time = LocalDateTime.parse("23-09-2023 16:40", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        TimeStart timeStart = new TimeStart(time);

        // same values -> returns true
        assertTrue(timeStart.equals(new TimeStart(time)));

        // same object -> returns true
        assertTrue(timeStart.equals(timeStart));

        // null -> returns false
        assertFalse(timeStart.equals(null));

        // different types -> returns false
        assertFalse(timeStart.equals(5.0f));

        // different values -> returns false
        LocalDateTime differentTime = LocalDateTime.parse("24-09-2023 16:40",
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        assertFalse(timeStart.equals(new TimeStart(differentTime)));
    }
}
