package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_LEADAMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_LEADBOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_KEY_MILESTONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_TIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditLeadDescriptorBuilder;

class EditLeadDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        EditCommand.EditLeadDescriptor descriptorWithSameValues = new EditCommand.EditLeadDescriptor(DESC_LEADAMY);
        assertTrue(DESC_LEADAMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_LEADAMY.equals(DESC_LEADAMY));

        // null -> returns false
        assertFalse(DESC_LEADAMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_LEADAMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_LEADAMY.equals(DESC_LEADBOB));

        // different name -> returns false
        EditCommand.EditLeadDescriptor editedAmy = new EditLeadDescriptorBuilder(DESC_LEADAMY)
                .withName(VALID_NAME_BOB).build();
        assertFalse(DESC_LEADAMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditLeadDescriptorBuilder(DESC_LEADAMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_LEADAMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditLeadDescriptorBuilder(DESC_LEADAMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_LEADAMY.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditLeadDescriptorBuilder(DESC_LEADAMY).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_LEADAMY.equals(editedAmy));

        // different meeting time -> returns false
        editedAmy = new EditLeadDescriptorBuilder(DESC_LEADAMY).withMeetingTime(VALID_MEETING_TIME_BOB).build();
        assertFalse(DESC_LEADAMY.equals(editedAmy));

        //different key milestone -> returns false
        editedAmy = new EditLeadDescriptorBuilder(DESC_LEADAMY).withKeyMilestone(VALID_KEY_MILESTONE_BOB).build();
        assertFalse(DESC_LEADAMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditLeadDescriptorBuilder(DESC_LEADAMY).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_LEADAMY.equals(editedAmy));
    }

    @Test
    public void toStringMethod() {
        //change to editPersonDescriptor
        EditCommand.EditPersonDescriptor editPersonDescriptor = new EditCommand.EditPersonDescriptor();
        String expected = EditCommand.EditPersonDescriptor.class.getCanonicalName() + "{name="
                + editPersonDescriptor.getName().orElse(null) + ", phone="
                + editPersonDescriptor.getPhone().orElse(null) + ", email="
                + editPersonDescriptor.getEmail().orElse(null) + ", address="
                + editPersonDescriptor.getAddress().orElse(null) + ", meetingTime="
                + editPersonDescriptor.getMeetingTime() + ", tags="
                + editPersonDescriptor.getTags().orElse(null) + "}";
        assertEquals(expected, editPersonDescriptor.toString());
    }
}
