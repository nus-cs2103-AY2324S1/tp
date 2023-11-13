package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.task.Deadline.ABSENT_DEADLINE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TaskBuilder.DEFAULT_DEADLINE;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.exceptions.InvalidDeadlineException;

class DeadlineTest {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Test
    public void constructorTest() {
        assertThrows(InvalidDeadlineException.class, () ->
                new Deadline("Invalid"));

        assertNull(new Deadline((String) null).getDeadline());
    }

    @Test
    public void isPresentTest() {
        assertTrue(new Deadline(DEFAULT_DEADLINE).isPresent());

        assertFalse(new Deadline((String) null).isPresent());
    }

    @Test
    public void compareToTest() {
        Deadline nonPresent = new Deadline((String) null);
        Deadline otherNonPresent = new Deadline((LocalDateTime) null);
        Deadline present = new Deadline(DEFAULT_DEADLINE);
        Deadline presentLater = new Deadline("2023-01-01 14:00");

        assertEquals(0, nonPresent.compareTo(otherNonPresent));

        assertEquals(1, presentLater.compareTo(present));

        assertEquals(-1, present.compareTo(presentLater));

        assertEquals(1, nonPresent.compareTo(present));

        assertEquals(-1, present.compareTo(nonPresent));
    }

    @Test
    public void getFormattedDateTimeTest() {
        assertEquals(DEFAULT_DEADLINE, new Deadline(DEFAULT_DEADLINE).getFormattedDeadline());

        assertEquals(ABSENT_DEADLINE, new Deadline((LocalDateTime) null).getFormattedDeadline());
    }

    @Test
    public void equalsTest() {
        Deadline nonPresent = new Deadline((String) null);
        Deadline otherNonPresent = new Deadline((LocalDateTime) null);
        Deadline present = new Deadline(DEFAULT_DEADLINE);
        Deadline presentLater = new Deadline("2023-01-01 14:00");

        assertTrue(nonPresent.equals(otherNonPresent));

        assertTrue(present.equals(present));

        assertFalse(present.equals(presentLater));

        assertFalse(present.equals(nonPresent));
    }
}
