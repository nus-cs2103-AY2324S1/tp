package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AssignmentTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Assignment(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Assignment(new Name("")));
    }

    @Test
    public void isValidAssignment() {
        // null name
        assertThrows(NullPointerException.class, () -> Assignment.isValidAssignment(null));

        // invalid name
        assertThrows(IllegalArgumentException.class, () -> new Assignment(new Name(""))); // Empty string throws error
        assertThrows(IllegalArgumentException.class, () -> new Assignment(new Name(" "))); // One whitespace
        // contains non-alphanumeric
        assertThrows(IllegalArgumentException.class, () -> new Assignment(new Name("This is not valid!")));
        // only non-alphanumeric characters
        assertThrows(IllegalArgumentException.class, () -> new Assignment(new Name("^")));

        // valid name
        assertTrue(Assignment.isValidAssignment(new Name("123456"))); // numbers only
        assertTrue(Assignment.isValidAssignment(new Name("CS1231S Tutorial"))); // alphanumeric characters
        assertTrue(Assignment.isValidAssignment(new Name("BEST CS2100"))); // with capital letters
        assertTrue(Assignment.isValidAssignment(new Name("Why is CS2100 giving so much assignment"))); // long names
    }

    @Test
    public void equals() {
        Name name = new Name("Valid Name");
        Assignment assignment = new Assignment(name);

        // same values -> returns true
        assertTrue(assignment.equals(new Assignment(name)));

        // same object -> returns true
        assertTrue(assignment.equals(assignment));

        // different case -> returns false
        assertFalse(assignment.equals(new Assignment(new Name("valid name"))));

        // null -> returns false
        assertFalse(assignment.equals(null));

        // different types -> returns false
        assertFalse(assignment.equals(5.0f));

        // different values -> returns false
        assertFalse(assignment.equals(new Assignment(new Name("Other Valid Name"))));

        // different case sensitive -> returns false
        assertFalse(assignment.equals(new Assignment(new Name("VALID NAME"))));
    }


    @Test
    public void hashCodeTest() {
        Name name = new Name("Valid Name");
        Assignment assignment = new Assignment(name);
        assertTrue(assignment.hashCode() == assignment.hashCode());
    }
}
