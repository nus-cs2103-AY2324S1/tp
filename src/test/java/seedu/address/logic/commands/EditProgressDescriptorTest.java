package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_TASK;
import static seedu.address.logic.commands.CommandTestUtil.DESC_TASK2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROGRESS_DONE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UpdateTaskProgressCommand.EditProgressDescriptor;
import seedu.address.testutil.EditProgressDescriptorBuilder;

public class EditProgressDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditProgressDescriptor descriptorWithSameValues = new EditProgressDescriptor(DESC_TASK);
        assertTrue(DESC_TASK.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_TASK.equals(DESC_TASK));

        // null -> returns false
        assertFalse(DESC_TASK.equals(null));

        // different types -> returns false
        assertFalse(DESC_TASK.equals(5));

        // different values -> returns false
        assertFalse(DESC_TASK.equals(DESC_TASK2));

        // different progress -> returns false
        EditProgressDescriptor editedTask = new EditProgressDescriptorBuilder(DESC_TASK)
                .withProgress(VALID_PROGRESS_DONE).build();
        assertFalse(DESC_TASK.equals(editedTask));

    }

    @Test
    public void toStringMethod() {
        EditProgressDescriptor editProgressDescriptor = new EditProgressDescriptor();
        String expected = EditProgressDescriptor.class.getCanonicalName() + "{taskProgress="
                + editProgressDescriptor.getProgress().orElse(null) + "}";
        assertEquals(expected, editProgressDescriptor.toString());
    }
}
