package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PriorityTest {
    @Test
    public void stringFormat() {
        assertTrue(Priority.LOW.toString().equals("LOW"));
        assertTrue(Priority.MEDIUM.toString().equals("MEDIUM"));
        assertTrue(Priority.HIGH.toString().equals("HIGH"));
        assertTrue(Priority.NONE.toString().equals("NONE"));
    }
    @Test
    public void ofConstructor() throws Exception {
        assertTrue(Priority.LOW.equals(Priority.of("low")));
        assertTrue(Priority.LOW.equals(Priority.of("LOW")));
        assertFalse(Priority.LOW.equals(Priority.of("medium")));
        assertTrue(Priority.MEDIUM.equals(Priority.of("medium")));
        assertTrue(Priority.MEDIUM.equals(Priority.of("Medium")));
        assertTrue(Priority.MEDIUM.equals(Priority.of("MediUM")));
        assertFalse(Priority.MEDIUM.equals(Priority.of("low")));
    }
    @Test
    public void isValidPriority_success() {
    }
    @Test
    public void isValidPriority_failure() {
    }
}
