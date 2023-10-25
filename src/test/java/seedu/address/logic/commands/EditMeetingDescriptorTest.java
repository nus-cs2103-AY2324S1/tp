package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_MEET1;
import static seedu.address.logic.commands.CommandTestUtil.DESC_MEET2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_MEETING2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_MEETING2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_MEETING2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_MEETING2;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditMeetingCommand.EditMeetingDescriptor;
import seedu.address.testutil.EditMeetingDescriptorBuilder;

public class EditMeetingDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        EditMeetingDescriptor descriptorWithSameValues = new EditMeetingDescriptor(DESC_MEET1);
        assertTrue(DESC_MEET1.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_MEET1.equals(DESC_MEET1));

        // null -> returns false
        assertFalse(DESC_MEET1.equals(null));

        // different types -> returns false
        assertFalse(DESC_MEET1.equals(5));

        // different values -> returns false
        assertFalse(DESC_MEET1.equals(DESC_MEET2));

        // different title -> returns false
        EditMeetingDescriptor editedMeet1 = new EditMeetingDescriptorBuilder(DESC_MEET1).withTitle(VALID_TITLE_MEETING2)
                .build();
        assertFalse(DESC_MEET1.equals(editedMeet1));

        // different location -> returns false
        editedMeet1 = new EditMeetingDescriptorBuilder(DESC_MEET1).withLocation(VALID_LOCATION_MEETING2).build();
        assertFalse(DESC_MEET1.equals(editedMeet1));

        // different start time -> returns false
        editedMeet1 = new EditMeetingDescriptorBuilder(DESC_MEET1).withStart(VALID_START_MEETING2).build();
        assertFalse(DESC_MEET1.equals(editedMeet1));

        // different end time -> returns false
        editedMeet1 = new EditMeetingDescriptorBuilder(DESC_MEET1).withEnd(VALID_END_MEETING2).build();
        assertFalse(DESC_MEET1.equals(editedMeet1));

        // different tags -> return false
        editedMeet1 = new EditMeetingDescriptorBuilder(DESC_MEET1).withTitle(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_MEET1.equals(editedMeet1));
    }

    @Test
    public void toStringMethod() {
        EditMeetingDescriptor editMeetingDescriptor = new EditMeetingDescriptor();
        String expected = EditMeetingDescriptor.class.getCanonicalName() + "{title="
                + editMeetingDescriptor.getTitle().orElse(null) + ", location="
                + editMeetingDescriptor.getLocation().orElse(null) + ", start="
                + editMeetingDescriptor.getStart().orElse(null) + ", end="
                + editMeetingDescriptor.getEnd().orElse(null) + ", tags="
                + editMeetingDescriptor.getTags().orElse(null) + "}";
        assertEquals(expected, editMeetingDescriptor.toString());
    }
}
