package seedu.ccacommander.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ccacommander.logic.commands.CommandTestUtil.DESC_AURORA;
import static seedu.ccacommander.logic.commands.CommandTestUtil.DESC_BOXING;
import static seedu.ccacommander.logic.commands.CommandTestUtil.VALID_DATE_BOXING;
import static seedu.ccacommander.logic.commands.CommandTestUtil.VALID_LOCATION_BOXING;
import static seedu.ccacommander.logic.commands.CommandTestUtil.VALID_NAME_BOXING;
import static seedu.ccacommander.logic.commands.CommandTestUtil.VALID_TAG_BOXING;

import org.junit.jupiter.api.Test;

import seedu.ccacommander.logic.commands.EditEventCommand.EditEventDescriptor;
import seedu.ccacommander.testutil.EditEventDescriptorBuilder;

public class EditEventDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditEventCommand.EditEventDescriptor descriptorWithSameValues = new EditEventDescriptor(DESC_AURORA);
        assertTrue(DESC_AURORA.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AURORA.equals(DESC_AURORA));

        // null -> returns false
        assertFalse(DESC_AURORA.equals(null));

        // different types -> returns false
        assertFalse(DESC_AURORA.equals(5));

        // different values -> returns false
        assertFalse(DESC_AURORA.equals(DESC_BOXING));

        // different name -> returns false
        EditEventDescriptor editedAurora = new EditEventDescriptorBuilder(DESC_AURORA)
                .withName(VALID_NAME_BOXING).build();
        assertFalse(DESC_AURORA.equals(editedAurora));

        // different location -> returns false
        editedAurora = new EditEventDescriptorBuilder(DESC_AURORA).withLocation(VALID_LOCATION_BOXING).build();
        assertFalse(DESC_AURORA.equals(editedAurora));

        // different date -> returns false
        editedAurora = new EditEventDescriptorBuilder(DESC_AURORA).withDate(VALID_DATE_BOXING).build();
        assertFalse(DESC_AURORA.equals(editedAurora));


        // different tags -> returns false
        editedAurora = new EditEventDescriptorBuilder(DESC_AURORA).withTags(VALID_TAG_BOXING).build();
        assertFalse(DESC_AURORA.equals(editedAurora));
    }

    @Test
    public void toStringMethod() {
        EditEventCommand.EditEventDescriptor editEventDescriptor = new EditEventCommand.EditEventDescriptor();
        String expected = EditEventCommand.EditEventDescriptor.class.getCanonicalName() + "{name="
                + editEventDescriptor.getName().orElse(null) + ", location="
                + editEventDescriptor.getLocation().orElse(null) + ", date="
                + editEventDescriptor.getDate().orElse(null) + ", tags="
                + editEventDescriptor.getTags().orElse(null) + "}";
        assertEquals(expected, editEventDescriptor.toString());
    }
}
