package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MAJOR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SM_LINKEDIN_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUT_FIRST_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_YEAR_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.testutil.EditPersonDescriptorBuilder;

public class EditPersonDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditPersonDescriptor descriptorWithSameValues = new EditPersonDescriptor(DESC_AMY);
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
        EditPersonDescriptor editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different major -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withMajor(VALID_MAJOR_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different year -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withYear(VALID_YEAR_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different description -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withDescription(VALID_DESCRIPTION_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tutorial -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withTutorials(VALID_TUT_FIRST_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different social media links -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withSocialMediaLinks(VALID_SM_LINKEDIN_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }

    @Test
    public void toStringMethod() {
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        String expected = EditPersonDescriptor.class.getCanonicalName() + "{name="
                + editPersonDescriptor.getName().orElse(null) + ", major="
                + editPersonDescriptor.getMajor().orElse(null) + ", year="
                + editPersonDescriptor.getYear().orElse(null) + ", email="
                + editPersonDescriptor.getEmail().orElse(null) + ", description="
                + editPersonDescriptor.getTutorials().orElse(null) + ", tutorials="
                + editPersonDescriptor.getDescription().orElse(null) + ", social media links="
                + editPersonDescriptor.getSocialMediaLinks().orElse(null) + ", nationality="
                + editPersonDescriptor.getNationality().orElse(null) + ", gender="
                + editPersonDescriptor.getGender().orElse(null) + "}";

        assertEquals(expected, editPersonDescriptor.toString());
    }
}
