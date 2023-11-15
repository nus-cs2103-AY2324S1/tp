package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_ASIAN;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BRITISH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_BRITISH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BRITISH;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditStallCommand.EditStallDescriptor;
import seedu.address.testutil.EditStallDescriptorBuilder;

public class EditStallDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditStallCommand.EditStallDescriptor descriptorWithSameValues = new EditStallDescriptor(DESC_ASIAN);
        assertTrue(DESC_ASIAN.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_ASIAN.equals(DESC_ASIAN));

        // null -> returns false
        assertFalse(DESC_ASIAN.equals(null));

        // different types -> returns false
        assertFalse(DESC_ASIAN.equals(5));

        // different values -> returns false
        assertFalse(DESC_ASIAN.equals(DESC_BRITISH));

        // different name -> returns false
        EditStallDescriptor editedAsian = new EditStallDescriptorBuilder(DESC_ASIAN)
                .withName(VALID_NAME_BRITISH).build();
        assertFalse(DESC_ASIAN.equals(editedAsian));

        // different location -> returns false
        editedAsian = new EditStallDescriptorBuilder(DESC_ASIAN).withLocation(VALID_LOCATION_BRITISH).build();
        assertFalse(DESC_ASIAN.equals(editedAsian));

    }

    @Test
    public void toStringMethod() {
        EditStallCommand.EditStallDescriptor editStallDescriptor = new EditStallCommand.EditStallDescriptor();
        String expected = EditStallCommand.EditStallDescriptor.class.getCanonicalName() + "{name="
                + editStallDescriptor.getName().orElse(null) + ", location="
                + editStallDescriptor.getLocation().orElse(null) + ", menu="
                + editStallDescriptor.getMenu().orElse(null) + ", rating="
                + editStallDescriptor.getRating().orElse(null) + ", description="
                + editStallDescriptor.getDescription().orElse(null)
                + "}";
        assertEquals(expected, editStallDescriptor.toString());
    }
}
