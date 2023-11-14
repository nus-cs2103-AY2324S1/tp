package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_TASK_AGENDA;
import static seedu.address.logic.commands.CommandTestUtil.DESC_TASK_BUDGET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_BUDGET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FINANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_BUDGET;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.address.testutil.EditTaskDescriptorBuilder;

public class EditTaskDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditTaskDescriptor descriptorWithSameValues = new EditTaskDescriptor(DESC_TASK_AGENDA);
        assertTrue(DESC_TASK_AGENDA.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_TASK_AGENDA.equals(DESC_TASK_AGENDA));

        // null -> returns false
        assertFalse(DESC_TASK_AGENDA.equals(null));

        // different types -> returns false
        assertFalse(DESC_TASK_AGENDA.equals(5));

        // different values -> returns false
        assertFalse(DESC_TASK_AGENDA.equals(DESC_TASK_BUDGET));

        // different title -> returns false
        EditTaskDescriptor editedAgenda = new EditTaskDescriptorBuilder(DESC_TASK_AGENDA).withTitle(VALID_TITLE_BUDGET)
                .build();
        assertFalse(DESC_TASK_AGENDA.equals(editedAgenda));

        // different note -> returns false
        editedAgenda = new EditTaskDescriptorBuilder(DESC_TASK_AGENDA).withNote(VALID_NOTE_BUDGET).build();
        assertFalse(DESC_TASK_AGENDA.equals(editedAgenda));

        // different tags -> returns false
        editedAgenda = new EditTaskDescriptorBuilder(DESC_TASK_AGENDA).withTags(VALID_TAG_FINANCE).build();
        assertFalse(DESC_TASK_AGENDA.equals(editedAgenda));
    }

    @Test
    public void toStringMethod() {
        EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptor();
        String expected = EditTaskDescriptor.class.getCanonicalName() + "{title="
                + editTaskDescriptor.getTitle().orElse(null) + ", note="
                + editTaskDescriptor.getNote().orElse(null) + ", tags="
                + editTaskDescriptor.getTags().orElse(null) + "}";
        assertEquals(expected, editTaskDescriptor.toString());
    }
}
