package profplan.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static profplan.logic.commands.CommandTestUtil.DESC_AMY;
import static profplan.logic.commands.CommandTestUtil.DESC_BOB;
import static profplan.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static profplan.logic.commands.CommandTestUtil.VALID_PRIORITY_BOB;
import static profplan.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;

import profplan.logic.commands.EditCommand.EditTaskDescriptor;
import profplan.testutil.EditTaskDescriptorBuilder;

public class EditTaskDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditTaskDescriptor descriptorWithSameValues = new EditTaskDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different name -> returns false
        EditTaskDescriptor editedAmy = new EditTaskDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different priority -> returns false
        editedAmy = new EditTaskDescriptorBuilder(DESC_AMY).withPriority(VALID_PRIORITY_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditTaskDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }

    @Test
    public void toStringMethod() {
        EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptor();
        String expected = EditTaskDescriptor.class.getCanonicalName() + "{name="
                + editTaskDescriptor.getName().orElse(null) + ", priority="
                + editTaskDescriptor.getPriority().orElse(null) + ", tags="
                + editTaskDescriptor.getTags().orElse(null) + ", dueDate="
                + editTaskDescriptor.getDueDate().orElse(null) + ", link="
                + editTaskDescriptor.getLink().orElse(null) + "}";
        assertEquals(expected, editTaskDescriptor.toString());
    }
}
