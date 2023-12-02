package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_TIKTOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_TIKTOK;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditCompanyDescriptor;
import seedu.address.testutil.EditCompanyDescriptorBuilder;

public class EditCompanyDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCompanyDescriptor descriptorWithSameValues = new EditCompanyDescriptor(DESC_GOOGLE);
        assertTrue(DESC_GOOGLE.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_GOOGLE.equals(DESC_GOOGLE));

        // null -> returns false
        assertFalse(DESC_GOOGLE.equals(null));

        // different types -> returns false
        assertFalse(DESC_GOOGLE.equals(5));

        // different values -> returns false
        assertFalse(DESC_GOOGLE.equals(DESC_TIKTOK));

        // different name -> returns false
        EditCommand.EditCompanyDescriptor editedGoogle = new EditCompanyDescriptorBuilder(DESC_GOOGLE)
                .withName(VALID_NAME_TIKTOK).build();
        assertFalse(DESC_GOOGLE.equals(editedGoogle));

        // different phone -> returns false
        editedGoogle = new EditCompanyDescriptorBuilder(DESC_GOOGLE).withPhone(VALID_PHONE_TIKTOK).build();
        assertFalse(DESC_GOOGLE.equals(editedGoogle));

        // different email -> returns false
        editedGoogle = new EditCompanyDescriptorBuilder(DESC_GOOGLE).withEmail(VALID_EMAIL_TIKTOK).build();
        assertFalse(DESC_GOOGLE.equals(editedGoogle));

        // different priority -> returns false
        editedGoogle = new EditCompanyDescriptorBuilder(DESC_GOOGLE).withPriority("MEDIUM").build();
        assertFalse(DESC_GOOGLE.equals(editedGoogle));
    }

    @Test
    public void toStringMethod() {
        EditCommand.EditCompanyDescriptor editCompanyDescriptor = new EditCommand.EditCompanyDescriptor();
        String expected = EditCommand.EditCompanyDescriptor.class.getCanonicalName() + "{name="
                + editCompanyDescriptor.getName().orElse(null) + ", role="
                + editCompanyDescriptor.getRole().orElse(null) + ", deadline="
                + editCompanyDescriptor.getDeadline().orElse(null) + ", status="
                + editCompanyDescriptor.getStatus().orElse(null) + ", recruiter name="
                + editCompanyDescriptor.getRecruiterName().orElse(null) + ", phone="
                + editCompanyDescriptor.getPhone().orElse(null) + ", email="
                + editCompanyDescriptor.getEmail().orElse(null) + ", priority="
                + editCompanyDescriptor.getPriority().orElse(null)
                + "}";
        assertEquals(expected, editCompanyDescriptor.toString());
    }
}
