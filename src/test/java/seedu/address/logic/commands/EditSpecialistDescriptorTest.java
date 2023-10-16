package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIALTY_ORTHOPAEDIC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditSpecialistDescriptor;
import seedu.address.testutil.EditSpecialistDescriptorBuilder;

public class EditSpecialistDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditSpecialistDescriptor descriptorWithSameValues = new EditSpecialistDescriptorBuilder(DESC_BOB).build();
        assertTrue(DESC_BOB.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_BOB.equals(DESC_BOB));

        // null -> returns false
        assertFalse(DESC_BOB.equals(null));

        // different types -> returns false
        assertFalse(DESC_BOB.equals(5));

        // different values -> returns false
        assertFalse(DESC_BOB.equals(DESC_AMY));

        // different name -> returns false
        EditSpecialistDescriptor editedBob = (EditSpecialistDescriptor) new EditSpecialistDescriptorBuilder(DESC_BOB)
                .withName(VALID_NAME_AMY).build();
        assertFalse(DESC_BOB.equals(editedBob));

        // different phone -> returns false
        editedBob = (EditSpecialistDescriptor) new EditSpecialistDescriptorBuilder(DESC_BOB)
                .withPhone(VALID_PHONE_AMY).build();
        assertFalse(DESC_BOB.equals(editedBob));

        // different email -> returns false
        editedBob = (EditSpecialistDescriptor) new EditSpecialistDescriptorBuilder(DESC_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        assertFalse(DESC_BOB.equals(editedBob));

        // different location -> returns false
        editedBob = new EditSpecialistDescriptorBuilder(DESC_BOB)
                .withLocation(VALID_LOCATION_AMY).build();
        assertFalse(DESC_BOB.equals(editedBob));

        // different specialty -> returns false
        editedBob = new EditSpecialistDescriptorBuilder(DESC_BOB)
                .withSpecialty(VALID_SPECIALTY_ORTHOPAEDIC).build();
        assertFalse(DESC_BOB.equals(editedBob));

        // different tags -> returns false
        editedBob = (EditSpecialistDescriptor) new EditSpecialistDescriptorBuilder(DESC_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_BOB.equals(editedBob));
    }

    @Test
    public void toStringMethod() {
        EditSpecialistDescriptor editSpecialistDescriptor = new EditSpecialistDescriptor();
        String expected = EditSpecialistDescriptor.class.getCanonicalName() + "{name="
                + editSpecialistDescriptor.getName().orElse(null) + ", phone="
                + editSpecialistDescriptor.getPhone().orElse(null) + ", email="
                + editSpecialistDescriptor.getEmail().orElse(null) + ", tags="
                + editSpecialistDescriptor.getTags().orElse(null) + ", location="
                + editSpecialistDescriptor.getLocation().orElse(null) + ", specialty="
                + editSpecialistDescriptor.getSpecialty().orElse(null) + "}";
        assertEquals(expected, editSpecialistDescriptor.toString());
    }
}
