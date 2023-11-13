package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DG;
import static seedu.address.logic.commands.CommandTestUtil.DESC_UG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_UG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEMBER_UG;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditTaskDescriptor;
import seedu.address.testutil.EditTaskDescriptorBuilder;

public class EditTaskDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditTaskDescriptor descriptorWithSameValues = new EditTaskDescriptor(DESC_DG);
        assertTrue(DESC_DG.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_DG.equals(DESC_DG));

        // null -> returns false
        assertFalse(DESC_DG.equals(null));

        // different types -> returns false
        assertFalse(DESC_DG.equals(5));

        // different values -> returns false
        assertFalse(DESC_DG.equals(DESC_UG));

        // different name -> returns false
        EditTaskDescriptor editedDG = new EditTaskDescriptorBuilder(DESC_DG).withDescription(VALID_DESC_UG)
                .build();
        assertFalse(DESC_DG.equals(editedDG));

        // different members -> returns false
        editedDG = new EditTaskDescriptorBuilder(DESC_DG).withMembers(VALID_MEMBER_UG).build();
        assertFalse(DESC_DG.equals(editedDG));
    }

    @Test
    public void toStringMethod() {
        EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptor();
        String expected = EditTaskDescriptor.class.getCanonicalName() + "{description="
                + editTaskDescriptor.getDescription().orElse(null) + ", note="
                + editTaskDescriptor.getNote().orElse(null) + ", deadline="
                + editTaskDescriptor.getDeadline().orElse(null) + ", priority="
                + editTaskDescriptor.getPriority().orElse(null) + ", members="
                + editTaskDescriptor.getMembers().orElse(null) + "}";
        assertEquals(expected, editTaskDescriptor.toString());
    }
}
