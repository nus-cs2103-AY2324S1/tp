package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_FIRST_REC;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SECOND_REC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONDITION_DIARRHEA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_THYROID_CHECK;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditRecordDescriptorBuilder;

public class EditRecordDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        EditRecordCommand.EditRecordDescriptor descriptorWithSameValues =
                new EditRecordCommand.EditRecordDescriptor(DESC_FIRST_REC);
        assertTrue(DESC_FIRST_REC.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_FIRST_REC.equals(DESC_FIRST_REC));

        // null -> returns false
        assertFalse(DESC_FIRST_REC.equals(null));

        // different types -> returns false
        assertFalse(DESC_FIRST_REC.equals(5));

        // different values -> returns false
        assertFalse(DESC_FIRST_REC.equals(DESC_SECOND_REC));

        // different dateTime -> returns false
        EditRecordCommand.EditRecordDescriptor editedFirstRecord =
                new EditRecordDescriptorBuilder(DESC_FIRST_REC).withDateTime(VALID_DATETIME_THYROID_CHECK).build();
        assertFalse(DESC_FIRST_REC.equals(editedFirstRecord));

        // different condition -> returns false
        EditRecordCommand.EditRecordDescriptor editedSecondRecord =
                new EditRecordDescriptorBuilder(DESC_SECOND_REC).withConditions(VALID_CONDITION_DIARRHEA).build();
        assertFalse(DESC_SECOND_REC.equals(editedSecondRecord));
    }

    @Test
    public void toStringMethod() {
        EditRecordCommand.EditRecordDescriptor editRecordDescriptor = new EditRecordCommand.EditRecordDescriptor();
        String expected = EditRecordCommand.EditRecordDescriptor.class.getCanonicalName() + "{dateTime="
                + editRecordDescriptor.getDateTime().orElse(null) + ", conditions="
                + editRecordDescriptor.getConditions().orElse(null) + ", medications="
                + editRecordDescriptor.getMedications().orElse(null) + ", filePath="
                + editRecordDescriptor.getFilePath().orElse(null) + ", patientIndex="
                + editRecordDescriptor.getPatientIndex().orElse(null) + "}";

        assertEquals(expected, editRecordDescriptor.toString());
    }

}
