package seedu.application.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.logic.commands.CommandTestUtil.DESC_CHEF;
import static seedu.application.logic.commands.CommandTestUtil.DESC_CLEANER;
import static seedu.application.logic.commands.CommandTestUtil.VALID_COMPANY_CLEANER;
import static seedu.application.logic.commands.CommandTestUtil.VALID_DEADLINE_CLEANER;
import static seedu.application.logic.commands.CommandTestUtil.VALID_ROLE_CLEANER;

import org.junit.jupiter.api.Test;

import seedu.application.logic.commands.EditCommand.EditJobDescriptor;
import seedu.application.testutil.EditJobDescriptorBuilder;

public class EditJobDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditJobDescriptor descriptorWithSameValues = new EditJobDescriptor(DESC_CHEF);
        assertTrue(DESC_CHEF.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_CHEF.equals(DESC_CHEF));

        // null -> returns false
        assertFalse(DESC_CHEF.equals(null));

        // different types -> returns false
        assertFalse(DESC_CHEF.equals(5));

        // different values -> returns false
        assertFalse(DESC_CHEF.equals(DESC_CLEANER));

        // different role -> returns false
        EditJobDescriptor editedChef = new EditJobDescriptorBuilder(DESC_CHEF).withRole(VALID_ROLE_CLEANER).build();
        assertFalse(DESC_CHEF.equals(editedChef));

        // different company -> returns false
        editedChef = new EditJobDescriptorBuilder(DESC_CHEF).withCompany(VALID_COMPANY_CLEANER).build();
        assertFalse(DESC_CHEF.equals(editedChef));

        // different deadline -> returns false
        editedChef = new EditJobDescriptorBuilder(DESC_CHEF).withDeadline(VALID_DEADLINE_CLEANER).build();
        assertFalse(DESC_CHEF.equals(editedChef));
    }

    @Test
    public void toStringMethod() {
        EditJobDescriptor editJobDescriptor = new EditJobDescriptor();
        String expected = EditJobDescriptor.class.getCanonicalName() + "{company="
            + editJobDescriptor.getCompany().orElse(null) + ", role="
            + editJobDescriptor.getRole().orElse(null) + ", deadline="
            + editJobDescriptor.getDeadline().orElse(null) + ", status="
            + editJobDescriptor.getStatus().orElse(null) + ", jobType="
            + editJobDescriptor.getJobType().orElse(null) + "}";
        assertEquals(expected, editJobDescriptor.toString());
    }
}
