package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.DateTimeUtil.verbose;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.DateTimeUtil;

public class LastContactedTimeTest {

    private LocalDateTime lastContactedTime = DateTimeUtil.parse("02.10.2023 1000");
    private LastContactedTime lastContactedTimeObject = new LastContactedTime(lastContactedTime);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LastContactedTime(null));
    }

    @Test
    public void isValidLastContactedTime() {
        // default value
        assertTrue(LastContactedTime.isValidLastContactedTime(LocalDateTime.MIN));

        // valid
        assertTrue(LastContactedTime.isValidLastContactedTime(lastContactedTime));
    }

    @Test
    public void equals() {
        // same values -> returns true
        assertTrue(lastContactedTimeObject.equals(new LastContactedTime(lastContactedTime)));

        // same object -> returns true
        assertTrue(lastContactedTimeObject.equals(lastContactedTimeObject));

        // null -> returns false
        assertFalse(lastContactedTimeObject.equals(null));

        // different types -> returns false
        assertFalse(lastContactedTimeObject.equals(5.0f));

        // different values -> returns false
        assertFalse(lastContactedTimeObject.equals(new LastContactedTime(lastContactedTime.minusHours(1))));
    }

    @Test
    public void getDisplay() {
        // non default value
        assertEquals(lastContactedTimeObject.getDisplay(), verbose(lastContactedTime));

        // default value
        assertEquals(new LastContactedTime(LocalDateTime.MIN).getDisplay(), "NA");
    }
}
