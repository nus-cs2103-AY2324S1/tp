package seedu.address.logic.commands.musician;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.musician.EditCommand.EditMusicianDescriptor;
import seedu.address.testutil.EditMusicianDescriptorBuilder;

public class EditMusicianDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditMusicianDescriptor descriptorWithSameValues = new EditMusicianDescriptor(DESC_AMY);
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
        EditCommand.EditMusicianDescriptor editedAmy =
                new EditMusicianDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditMusicianDescriptorBuilder(DESC_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditMusicianDescriptorBuilder(DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));


        // different tags -> returns false
        editedAmy = new EditMusicianDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }

    @Test
    public void toStringMethod() {
        EditMusicianDescriptor editMusicianDescriptor = new EditCommand.EditMusicianDescriptor();
        String expected = EditCommand.EditMusicianDescriptor.class.getCanonicalName() + "{name="
                + editMusicianDescriptor.getName().orElse(null) + ", phone="
                + editMusicianDescriptor.getPhone().orElse(null) + ", email="
                + editMusicianDescriptor.getEmail().orElse(null) + ", tags="
                + editMusicianDescriptor.getTags().orElse(null) + ", instruments="
                + editMusicianDescriptor.getInstruments().orElse(null) + ", genres="
                + editMusicianDescriptor.getGenres().orElse(null) + "}";
        assertEquals(expected, editMusicianDescriptor.toString());
    }
}
