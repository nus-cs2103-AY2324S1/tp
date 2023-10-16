package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TestData.VALID_EMAIL_BOB;
import static seedu.address.testutil.TestData.VALID_NAME_BOB;
import static seedu.address.testutil.TestData.VALID_NOTE_BOB;
import static seedu.address.testutil.TestData.VALID_PHONE_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditContactDescriptor;
import seedu.address.testutil.EditContactDescriptorBuilder;
import seedu.address.testutil.TestData;



public class EditContactDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        EditContactDescriptor descriptorWithSameValues = new EditContactDescriptor(TestData.Valid.EditDescriptor.AMY);
        assertTrue(TestData.Valid.EditDescriptor.AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(TestData.Valid.EditDescriptor.AMY.equals(TestData.Valid.EditDescriptor.AMY));

        // null -> returns false
        assertFalse(TestData.Valid.EditDescriptor.AMY.equals(null));

        // different types -> returns false
        assertFalse(TestData.Valid.EditDescriptor.AMY.equals(5));

        // different values -> returns false
        assertFalse(TestData.Valid.EditDescriptor.AMY.equals(TestData.Valid.EditDescriptor.BOB));

        // different name -> returns false
        EditContactDescriptor editedAmy =
                new EditContactDescriptorBuilder(TestData.Valid.EditDescriptor.AMY)
                .withName(VALID_NAME_BOB)
                .build();
        assertFalse(TestData.Valid.EditDescriptor.AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy =
                new EditContactDescriptorBuilder(TestData.Valid.EditDescriptor.AMY)
                .withPhone(VALID_PHONE_BOB)
                .build();
        assertFalse(TestData.Valid.EditDescriptor.AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy =
                new EditContactDescriptorBuilder(TestData.Valid.EditDescriptor.AMY)
                .withEmail(VALID_EMAIL_BOB)
                .build();
        assertFalse(TestData.Valid.EditDescriptor.AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy =
                new EditContactDescriptorBuilder(TestData.Valid.EditDescriptor.AMY)
                .withNote(VALID_NOTE_BOB)
                .build();
        assertFalse(TestData.Valid.EditDescriptor.AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy =
                new EditContactDescriptorBuilder(TestData.Valid.EditDescriptor.AMY)
                .withTags(TestData.Valid.Tag.ALPHANUMERIC_SPACES)
                .build();
        assertFalse(TestData.Valid.EditDescriptor.AMY.equals(editedAmy));
    }

    @Test
    public void toStringMethod() {
        EditContactDescriptor editContactDescriptor = new EditContactDescriptor();
        String expected = EditContactDescriptor.class.getCanonicalName() + "{name="
                + editContactDescriptor.getName().orElse(null) + ", phone="
                + editContactDescriptor.getPhone().orElse(null) + ", email="
                + editContactDescriptor.getEmail().orElse(null) + ", address="
                + editContactDescriptor.getNote().orElse(null) + ", tags="
                + editContactDescriptor.getTags().orElse(null) + "}";
        assertEquals(expected, editContactDescriptor.toString());
    }
}
