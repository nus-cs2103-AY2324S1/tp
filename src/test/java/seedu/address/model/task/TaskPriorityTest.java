package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskPriorityTest {

    @Test
    public void testEnumValues_success() {
        // Test that all enum values are correctly defined
        TaskPriority low = TaskPriority.LOW;
        TaskPriority medium = TaskPriority.MEDIUM;
        TaskPriority high = TaskPriority.HIGH;

        assertEquals("LOW", low.name());
        assertEquals("MEDIUM", medium.name());
        assertEquals("HIGH", high.name());
    }

    @Test
    public void testMessageConstraints_success() {
        assertEquals("TaskPriority should only be low, medium, or high", TaskPriority.MESSAGE_CONSTRAINTS);
    }
}

