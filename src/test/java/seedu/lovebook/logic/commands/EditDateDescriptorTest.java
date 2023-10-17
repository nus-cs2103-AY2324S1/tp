package seedu.lovebook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.lovebook.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.lovebook.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.lovebook.logic.commands.CommandTestUtil.VALID_AGE_BOB;
import static seedu.lovebook.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.lovebook.logic.commands.CommandTestUtil.VALID_HEIGHT_BOB;
import static seedu.lovebook.logic.commands.CommandTestUtil.VALID_NAME_BOB;

import org.junit.jupiter.api.Test;

import seedu.lovebook.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.lovebook.testutil.EditPersonDescriptorBuilder;

public class EditDateDescriptorTest {

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

        // different age -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withAge(VALID_AGE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different gender -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withGender(VALID_GENDER_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different lovebook -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withHeight(VALID_HEIGHT_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

    }

    @Test
    public void toStringMethod() {
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        String expected = EditPersonDescriptor.class.getCanonicalName() + "{name="
                + editPersonDescriptor.getName().orElse(null) + ", age="
                + editPersonDescriptor.getAge().orElse(null) + ", gender="
                + editPersonDescriptor.getGender().orElse(null) + ", height="
                + editPersonDescriptor.getHeight().orElse(null) + ", income="
                + editPersonDescriptor.getIncome().orElse(null) + "}";
        assertEquals(expected, editPersonDescriptor.toString());
    }
}
