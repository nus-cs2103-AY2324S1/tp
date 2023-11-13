package seedu.staffsnap.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.staffsnap.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.staffsnap.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_POSITION_BOB;

import org.junit.jupiter.api.Test;

import seedu.staffsnap.logic.commands.EditCommand.EditApplicantDescriptor;
import seedu.staffsnap.testutil.EditApplicantDescriptorBuilder;

public class EditApplicantDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditApplicantDescriptor descriptorWithSameValues = new EditApplicantDescriptor(DESC_AMY);
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
        EditApplicantDescriptor editedAmy = new EditApplicantDescriptorBuilder(DESC_AMY)
                .withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditApplicantDescriptorBuilder(DESC_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditApplicantDescriptorBuilder(DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different position -> returns false
        editedAmy = new EditApplicantDescriptorBuilder(DESC_AMY).withPosition(VALID_POSITION_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }

    @Test
    public void toStringMethod() {
        EditApplicantDescriptor editApplicantDescriptor = new EditApplicantDescriptor();
        String expected = EditApplicantDescriptor.class.getCanonicalName() + "{name="
                + editApplicantDescriptor.getName().orElse(null) + ", phone="
                + editApplicantDescriptor.getPhone().orElse(null) + ", email="
                + editApplicantDescriptor.getEmail().orElse(null) + ", position="
                + editApplicantDescriptor.getPosition().orElse(null) + ", status="
                + editApplicantDescriptor.getStatus().orElse(null) + ", score="
                + editApplicantDescriptor.getScore().orElse(null) + "}";
        assertEquals(expected, editApplicantDescriptor.toString());
    }
}
