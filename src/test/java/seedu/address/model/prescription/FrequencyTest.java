package seedu.address.model.prescription;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FrequencyTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Frequency(null));
    }

    @Test
    public void constructor_invalidFrequency_throwsIllegalArgumentException() {
        String invalidFrequency = "";
        assertThrows(IllegalArgumentException.class, () -> new Frequency(invalidFrequency));
    }

    @Test
    public void isValidFrequency() {
        // null frequency
        assertThrows(NullPointerException.class, () -> Frequency.isValidFrequency(null));

        // invalid frequency
        assertFalse(Frequency.isValidFrequency("")); // empty string
        assertFalse(Frequency.isValidFrequency(" ")); // spaces only
        assertFalse(Frequency.isValidFrequency("^")); // only non-alphanumeric characters
        assertFalse(Frequency.isValidFrequency("Daily*")); // contains non-alphanumeric characters
        assertFalse(Frequency.isValidFrequency("Daaily")); // extra characters
        assertFalse(Frequency.isValidFrequency("Weeekly")); // extra characters
        assertFalse(Frequency.isValidFrequency("Wkly")); // not enough characters
        assertFalse(Frequency.isValidFrequency("Yearly")); // contains invalid values

        // valid frequency
        assertTrue(Frequency.isValidFrequency("Daily")); // Daily
        assertTrue(Frequency.isValidFrequency("Weekly")); // Weekly
        assertTrue(Frequency.isValidFrequency("Monthly")); // Month
    }

    @Test
    public void equals() {
        Frequency frequency = new Frequency("Daily");

        // same values -> returns true
        assertTrue(frequency.equals(new Frequency("Daily")));

        // same object -> returns true
        assertTrue(frequency.equals(frequency));

        // null -> returns false
        assertFalse(frequency.equals(null));

        // different types -> returns false
        assertFalse(frequency.equals(5.0f));

        // different values -> returns false
        assertFalse(frequency.equals(new Frequency("Weekly")));
    }
}
