package seedu.staffsnap.model.applicant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.staffsnap.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PositionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Position(null));
    }

    @Test
    public void constructor_invalidPosition_throwsIllegalArgumentException() {
        String invalidPosition = "";
        assertThrows(IllegalArgumentException.class, () -> new Position(invalidPosition));
    }

    @Test
    public void isValidPosition() {
        // null position
        assertThrows(NullPointerException.class, () -> Position.isValidPosition(null));

        // invalid positions
        assertFalse(Position.isValidPosition("")); // empty string
        assertFalse(Position.isValidPosition(" ")); // spaces only

        // valid positions
        assertTrue(Position.isValidPosition("Blk 456, Den Road, #01-355"));
        assertTrue(Position.isValidPosition("-")); // one character
        assertTrue(Position.isValidPosition(
                "Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long position
    }

    @Test
    public void equals() {
        Position position = new Position("Valid Position");

        // same values -> returns true
        assertTrue(position.equals(new Position("Valid Position")));

        // same object -> returns true
        assertTrue(position.equals(position));

        // null -> returns false
        assertFalse(position.equals(null));

        // different types -> returns false
        assertFalse(position.equals(5.0f));

        // different values -> returns false
        assertFalse(position.equals(new Position("Other Valid Position")));
    }
}
