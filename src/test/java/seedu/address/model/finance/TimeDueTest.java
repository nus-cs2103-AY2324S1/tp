package seedu.address.model.finance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.finance.TimeDue.DATE_TIME_FORMATTER;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class TimeDueTest {
    @Test
    public void constructor_invalidTime_throwsIllegalArgumentException() {
        String invalidTime = "Invalid Time";
        assertThrows(DateTimeParseException.class, () -> new TimeDue(LocalDateTime.parse(invalidTime,
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))));
    }

    @Test
    public void getTime() throws ParseException {
        TimeDue timeDue = new TimeDue("27-10-2023 01:57");
        assertEquals(timeDue.getTime(), LocalDateTime.parse("27-10-2023 01:57", DATE_TIME_FORMATTER));
    }

    @Test
    public void isBefore_earlierTime_returnsTrue() {
        // Arrange
        LocalDateTime earlierTime = LocalDateTime.now();
        TimeDue timeDue = new TimeDue(earlierTime);
        LocalDateTime laterTime = LocalDateTime.now().plusHours(1);

        // Act
        boolean result = timeDue.isBefore(new TimeDue(laterTime));

        // Assert
        assertTrue(result);
    }

    @Test
    public void isBefore_sameTime_returnsFalse() {
        // Arrange
        LocalDateTime earlierTime = LocalDateTime.now();
        TimeDue timeDue = new TimeDue(earlierTime);

        // Act
        boolean result = timeDue.isBefore(new TimeDue(earlierTime));

        // Assert
        assertFalse(result);
    }

    @Test
    public void equals() {
        LocalDateTime time = LocalDateTime.parse("23-09-2023 16:40", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        TimeDue timeDue = new TimeDue(time);

        // same values -> returns true
        assertTrue(timeDue.equals(new TimeDue(time)));

        // same object -> returns true
        assertTrue(timeDue.equals(timeDue));

        // null -> returns false
        assertFalse(timeDue.equals(null));

        // different types -> returns false
        assertFalse(timeDue.equals(5.0f));

        // different values -> returns false
        LocalDateTime differentTime = LocalDateTime.parse("24-09-2023 16:40",
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        assertFalse(timeDue.equals(new TimeDue(differentTime)));
    }
}
