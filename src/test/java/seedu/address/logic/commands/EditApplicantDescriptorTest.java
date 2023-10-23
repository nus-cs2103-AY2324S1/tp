package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CADENCE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DILL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DILL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_DILL;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditApplicantCommand.EditApplicantDescriptor;
import seedu.address.testutil.EditApplicantDescriptorBuilder;

public class EditApplicantDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditApplicantDescriptor descriptorWithSameValues = new EditApplicantDescriptor(DESC_CADENCE);
        assertTrue(DESC_CADENCE.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_CADENCE.equals(DESC_CADENCE));

        // null -> returns false
        assertFalse(DESC_CADENCE.equals(null));

        // different types -> returns false
        assertFalse(DESC_CADENCE.equals(5));

        // different values -> returns false
        assertFalse(DESC_CADENCE.equals(DESC_DILL));

        // different name -> returns false
        EditApplicantDescriptor editedCadence = new EditApplicantDescriptorBuilder(DESC_CADENCE)
                .withName(VALID_NAME_DILL).build();
        assertFalse(DESC_CADENCE.equals(editedCadence));

        // different phone -> returns false
        editedCadence = new EditApplicantDescriptorBuilder(DESC_CADENCE).withPhone(VALID_PHONE_DILL).build();
        assertFalse(DESC_CADENCE.equals(editedCadence));
    }

    @Test
    public void toStringMethod() {
        EditApplicantDescriptor editApplicantDescriptor = new EditApplicantDescriptor();
        String expected = EditApplicantDescriptor.class.getCanonicalName() + "{name="
                + editApplicantDescriptor.getName().orElse(null) + ", phone="
                + editApplicantDescriptor.getPhone().orElse(null) + "}";
        assertEquals(expected, editApplicantDescriptor.toString());
    }
}
