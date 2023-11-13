package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICAL_HISTORY_OSTEOPOROSIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditPatientDescriptor;
import seedu.address.testutil.EditPatientDescriptorBuilder;

public class EditPatientDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditPatientDescriptor descriptorWithSameValues = new EditPatientDescriptorBuilder(DESC_AMY).build();
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
        EditPatientDescriptor editedAmy = (EditPatientDescriptor) new EditPatientDescriptorBuilder(DESC_AMY)
                .withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = (EditPatientDescriptor) new EditPatientDescriptorBuilder(DESC_AMY)
                .withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = (EditPatientDescriptor) new EditPatientDescriptorBuilder(DESC_AMY)
                .withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different medical history -> returns false
        editedAmy = (EditPatientDescriptor) new EditPatientDescriptorBuilder(DESC_AMY)
                .withMedicalHistory(VALID_MEDICAL_HISTORY_OSTEOPOROSIS).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = (EditPatientDescriptor) new EditPatientDescriptorBuilder(DESC_AMY)
                .withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }

    @Test
    public void toStringMethod() {
        EditPatientDescriptor editPatientDescriptor = new EditPatientDescriptor();
        String expected = EditPatientDescriptor.class.getCanonicalName() + "{name="
                + editPatientDescriptor.getName().orElse(null) + ", phone="
                + editPatientDescriptor.getPhone().orElse(null) + ", email="
                + editPatientDescriptor.getEmail().orElse(null) + ", tags="
                + editPatientDescriptor.getTags().orElse(null) + ", age="
                + editPatientDescriptor.getAge().orElse(null) + ", medical history="
                + editPatientDescriptor.getMedicalHistory().orElse(null) + "}";
        assertEquals(expected, editPatientDescriptor.toString());
    }
}
