package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY_APPLICANT;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB_APPLICANT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditApplicantCommand.EditApplicantDescriptor;
import seedu.address.testutil.EditApplicantDescriptorBuilder;

public class EditApplicantDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditApplicantDescriptor descriptorWithSameValues = new EditApplicantDescriptor(DESC_AMY_APPLICANT);
        assertTrue(DESC_AMY_APPLICANT.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY_APPLICANT.equals(DESC_AMY_APPLICANT));

        // null -> returns false
        assertFalse(DESC_AMY_APPLICANT.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY_APPLICANT.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY_APPLICANT.equals(DESC_BOB_APPLICANT));

        // different name -> returns false
        EditApplicantDescriptor editedCadence = new EditApplicantDescriptorBuilder(DESC_AMY_APPLICANT)
                .withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY_APPLICANT.equals(editedCadence));

        // different phone -> returns false
        editedCadence = new EditApplicantDescriptorBuilder(DESC_AMY_APPLICANT).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY_APPLICANT.equals(editedCadence));
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
