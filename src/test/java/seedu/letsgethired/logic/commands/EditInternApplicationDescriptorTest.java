package seedu.letsgethired.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.letsgethired.logic.commands.CommandTestUtil.DESC_BYTEDANCE;
import static seedu.letsgethired.logic.commands.CommandTestUtil.DESC_JANE_STREET;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_COMPANY_BYTEDANCE;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_CYCLE_WINTER;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_ROLE_BACK_END;
import static seedu.letsgethired.logic.commands.CommandTestUtil.VALID_STATUS_REJECTED;

import org.junit.jupiter.api.Test;

import seedu.letsgethired.logic.commands.EditCommand.EditInternApplicationDescriptor;
import seedu.letsgethired.testutil.EditInternApplicationDescriptorBuilder;

public class EditInternApplicationDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditInternApplicationDescriptor descriptorWithSameValues =
                new EditInternApplicationDescriptor(DESC_JANE_STREET);
        assertTrue(DESC_JANE_STREET.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_JANE_STREET.equals(DESC_JANE_STREET));

        // null -> returns false
        assertFalse(DESC_JANE_STREET.equals(null));

        // different types -> returns false
        assertFalse(DESC_JANE_STREET.equals(5));

        // different values -> returns false
        assertFalse(DESC_JANE_STREET.equals(DESC_BYTEDANCE));

        // different name -> returns false
        EditInternApplicationDescriptor editedInternApplication =
                new EditInternApplicationDescriptorBuilder(DESC_JANE_STREET)
                .withCompany(VALID_COMPANY_BYTEDANCE)
                        .build();
        assertFalse(DESC_JANE_STREET.equals(editedInternApplication));

        // different role -> returns false
        editedInternApplication = new EditInternApplicationDescriptorBuilder(DESC_JANE_STREET)
                .withRole(VALID_ROLE_BACK_END)
                .build();
        assertFalse(DESC_JANE_STREET.equals(editedInternApplication));

        // different email -> returns false
        editedInternApplication = new EditInternApplicationDescriptorBuilder(DESC_JANE_STREET)
                .withCycle(VALID_CYCLE_WINTER)
                .build();
        assertFalse(DESC_JANE_STREET.equals(editedInternApplication));

        // different status -> returns false
        editedInternApplication = new EditInternApplicationDescriptorBuilder(DESC_JANE_STREET)
                .withStatus(VALID_STATUS_REJECTED)
                .build();
        assertFalse(DESC_JANE_STREET.equals(editedInternApplication));
    }

    @Test
    public void toStringMethod() {
        EditInternApplicationDescriptor editInternApplicationDescriptor = new EditInternApplicationDescriptor();
        String expected = EditInternApplicationDescriptor.class.getCanonicalName() + "{company="
                + editInternApplicationDescriptor.getCompany().orElse(null) + ", role="
                + editInternApplicationDescriptor.getRole().orElse(null) + ", cycle="
                + editInternApplicationDescriptor.getCycle().orElse(null) + ", status="
                + editInternApplicationDescriptor.getStatus().orElse(null) + ", deadline="
                + editInternApplicationDescriptor.getCycle().orElse(null) + "}";
        assertEquals(expected, editInternApplicationDescriptor.toString());
    }
}
