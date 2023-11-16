package seedu.address.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.DESC_PROJECT1;
import static seedu.address.logic.commands.CommandTestUtil.DESC_PROJECT2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FULL_PROJECT_DEADLINE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_DESCRIPTION_APPLEAPP;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditProjectDescriptorBuilder;

public class EditProjectDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditProjectCommand.EditProjectDescriptor descriptorWithSameValues =
                new EditProjectCommand.EditProjectDescriptor(DESC_PROJECT1);
        assertEquals(DESC_PROJECT1, descriptorWithSameValues);

        // same object -> returns true
        assertEquals(DESC_PROJECT1, DESC_PROJECT1);

        // null -> returns false
        assertNotEquals(null, DESC_PROJECT1);

        // different types -> returns false
        assertNotEquals(5, DESC_PROJECT1);

        // different values -> returns false
        assertNotEquals(DESC_PROJECT1, DESC_PROJECT2);

        // different description -> returns false
        EditProjectCommand.EditProjectDescriptor editedProj = new EditProjectDescriptorBuilder(DESC_PROJECT1)
                .withDescription(VALID_PROJECT_DESCRIPTION_APPLEAPP).build();
        assertNotEquals(DESC_PROJECT1, editedProj);

        // different deadlines -> returns false
        editedProj = new EditProjectDescriptorBuilder(DESC_PROJECT1)
                .withDeadline(VALID_FULL_PROJECT_DEADLINE_2).build();
        assertNotEquals(DESC_PROJECT1, editedProj);
    }

    @Test
    public void toStringMethod() {
        EditProjectCommand.EditProjectDescriptor editProjectDescriptor =
                new EditProjectDescriptorBuilder().build();
        String expected = EditProjectCommand.EditProjectDescriptor.class.getCanonicalName() + "{description="
                + editProjectDescriptor.getDescription().orElse(null) + ", deadlines="
                + editProjectDescriptor.getDeadlines().orElse(null) + "}";

        assertEquals(expected, editProjectDescriptor.toString());
    }
}
