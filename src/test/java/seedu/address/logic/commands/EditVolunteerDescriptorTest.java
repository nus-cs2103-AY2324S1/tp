package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_HUSBAND;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.volunteercommands.VolunteerEditCommand.EditVolunteerDescriptor;
import seedu.address.testutil.EditVolunteerDescriptorBuilder;

public class EditVolunteerDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditVolunteerDescriptor descriptorWithSameValues = new EditVolunteerDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different phone -> returns false
        EditVolunteerDescriptor editedAmy = new EditVolunteerDescriptorBuilder(DESC_AMY)
                .withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditVolunteerDescriptorBuilder(DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different skills -> returns false
        editedAmy = new EditVolunteerDescriptorBuilder(DESC_AMY).withSkills(VALID_SKILL_HUSBAND).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }

    @Test
    public void toStringMethod() {
        EditVolunteerDescriptor editVolunteerDescriptor = new EditVolunteerDescriptor();
        String expected = EditVolunteerDescriptor.class.getCanonicalName() + "{phone="
                + editVolunteerDescriptor.getPhone().orElse(null) + ", email="
                + editVolunteerDescriptor.getEmail().orElse(null) + ", skills="
                + editVolunteerDescriptor.getSkills().orElse(null) + ", assigned events="
                + editVolunteerDescriptor.getAssignedEvents().orElse(null) + "}";
        assertEquals(expected, editVolunteerDescriptor.toString());
    }
}
