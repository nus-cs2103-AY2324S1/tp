package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void stringFormat() {
        final Deadline originalDeadline = Deadline.of(LocalDateTime.of(2024, 10, 25, 10, 0));

        assertTrue(originalDeadline.toString().equals("2024-10-25 10:00"));
    }

    @Test
    public void equals() {
        final Deadline originalDeadline = Deadline.of(LocalDateTime.of(2024, 10, 24, 10, 0));

        assertTrue(originalDeadline.equals(originalDeadline));

        final Deadline otherDeadline = Deadline.of(LocalDateTime.of(2024, 10, 24, 10, 0));

        assertTrue(originalDeadline.equals(otherDeadline));

        final Deadline notEquals = Deadline.of(LocalDateTime.of(2024, 10, 24, 10, 10));

        assertFalse(originalDeadline.equals(notEquals));

        final Status notDeadline = new Status();

        assertFalse(originalDeadline.equals(notDeadline));
    }

    @Test
    public void isValidDate() {
        String validDateDash = "25-10-2023";

        assertTrue(Deadline.isValidDate(validDateDash));

        String validDateSlash = "25/10/2023";

        assertTrue(Deadline.isValidDate(validDateSlash));

        String validDateMix = "25/10-2023";

        assertTrue(Deadline.isValidDate(validDateMix));

        String invalidDateDay = "32-10-2023";

        assertFalse(Deadline.isValidDate(invalidDateDay));

        String invalidDateMonth = "32-10-2023";

        assertFalse(Deadline.isValidDate(invalidDateMonth));

        String invalidDateYear = "20-10-10000";

        assertFalse(Deadline.isValidDate(invalidDateYear));
    }

    @Test
    public void isValidDateTime() {
        String validDateDashValidTime = "25-10-2023 18:00";

        assertTrue(Deadline.isValidDateTime(validDateDashValidTime));

        String validDateSlashValidTime = "25-10-2023 18:00";

        assertTrue(Deadline.isValidDateTime(validDateSlashValidTime));

        String validDateMixValidTime = "25-10/2023 18:00";

        assertTrue(Deadline.isValidDateTime(validDateMixValidTime));

        String validDateValidTimeDash = "25/10/2023 18-00";

        assertTrue(Deadline.isValidDateTime(validDateValidTimeDash));

        String validDateValidTimeNoDashSlash = "25/10/2023 1800";

        assertTrue(Deadline.isValidDateTime(validDateValidTimeNoDashSlash));

        String validDateInvalidTimeHour = "32-10-2023 25:00";

        assertFalse(Deadline.isValidDateTime(validDateInvalidTimeHour));

        String validDateInvalidTimeMinute = "32-10-2023 23:60";

        assertFalse(Deadline.isValidDateTime(validDateInvalidTimeMinute));

        String invalidDateValidTimeDay = "32-10-2023 18:00";

        assertFalse(Deadline.isValidDateTime(invalidDateValidTimeDay));

        String invalidDateValidTimeMonth = "32-10-2023 18:00";

        assertFalse(Deadline.isValidDateTime(invalidDateValidTimeMonth));

        String invalidDateValidTimeYear = "32-10-2023 18:00";

        assertFalse(Deadline.isValidDateTime(invalidDateValidTimeYear));
    }

    @Test
    public void of() {
        LocalDateTime testLocalDateTime = LocalDateTime.of(2024, 10, 10, 10, 0);

        Deadline originalDeadline = Deadline.of(testLocalDateTime);

        assertEquals(originalDeadline, new Deadline(testLocalDateTime, false));

        assertNotEquals(originalDeadline, Deadline.ofNull());
    }

    @Test
    public void ofNull() {
        LocalDateTime testLocalDateTime = LocalDateTime.of(2024, 10, 10, 10, 0);

        Deadline originalDeadline = Deadline.ofNull();

        assertNotEquals(originalDeadline, new Deadline(testLocalDateTime, false));

        assertEquals(originalDeadline, Deadline.ofNull());

    }
}
