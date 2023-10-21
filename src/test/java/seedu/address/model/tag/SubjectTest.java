package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.YearMonth;

import org.junit.jupiter.api.Test;

public class SubjectTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Subject(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Subject(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Subject.isValidSubjectName(null));
    }

    @Test
    public void equals() {
        Subject subject1 = new Subject("Computer Science", "2023 Jul");
        Subject subject2 = new Subject("Computer Science", "2023 Aug");
        Subject subject3 = new Subject("English", "2023 Jul");

        //same values -> equal
        assertEquals(subject1, subject2);

        //null -> not equal
        assertNotEquals(subject1, null);

        // different values -> not equal
        assertNotEquals(subject1, subject3);

        // different type -> return false
        assertFalse(subject3.equals(0.5f));
    }

    @Test
    public void checkValidDate() {
        assertTrue(Subject.isValidDate("2023 Jul", new YearMonth[1]));
        assertFalse(Subject.isValidDate("2023-July", new YearMonth[1]));
    }

}
