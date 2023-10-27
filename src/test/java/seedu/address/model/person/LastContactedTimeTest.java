package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class LastContactedTimeTest {

    public static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy HHmm");

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LastContactedTime(null));
    }

    @Test
    public void isValidLastContactedTime() {
        // default value
        assertTrue(LastContactedTime.isValidLastContactedTime(LocalDateTime.MIN));

        // valid
        assertTrue(LastContactedTime.isValidLastContactedTime(LocalDateTime.parse("02.10.2023 1000", FORMAT)));
    }

    @Test
    public void equals() {
        LastContactedTime lastContactedTime = new LastContactedTime(LocalDateTime.parse("02.10.2023 1000", FORMAT));

        // same values -> returns true
        assertTrue(lastContactedTime.equals(new LastContactedTime(LocalDateTime.parse("02.10.2023 1000", FORMAT))));

        // same object -> returns true
        assertTrue(lastContactedTime.equals(lastContactedTime));

        // null -> returns false
        assertFalse(lastContactedTime.equals(null));

        // different types -> returns false
        assertFalse(lastContactedTime.equals(5.0f));

        // different values -> returns false
        assertFalse(lastContactedTime.equals(new LastContactedTime(LocalDateTime.parse("02.10.2023 1200", FORMAT))));
    }
}
