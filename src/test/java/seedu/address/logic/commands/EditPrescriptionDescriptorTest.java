package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_ASPIRIN;
import static seedu.address.logic.commands.CommandTestUtil.DESC_PROPRANOLOL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONSUMPTION_PROPRANOLOL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DOSAGE_PROPRANOLOL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_PROPRANOLOL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_PROPRANOLOL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FREQUENCY_PROPRANOLOL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_PROPRANOLOL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOTE_PROPRANOLOL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_PROPRANOLOL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STOCK_PROPRANOLOL;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditPrescriptionDescriptor;
import seedu.address.testutil.EditPrescriptionDescriptorBuilder;

public class EditPrescriptionDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        EditPrescriptionDescriptor descriptorWithSameValues = new EditPrescriptionDescriptor(DESC_ASPIRIN);
        assertTrue(DESC_ASPIRIN.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_ASPIRIN.equals(DESC_ASPIRIN));

        // null -> returns false
        assertFalse(DESC_ASPIRIN.equals(null));

        // different types -> returns false
        assertFalse(DESC_ASPIRIN.equals(5));

        // different values -> returns false
        assertFalse(DESC_ASPIRIN.equals(DESC_PROPRANOLOL));

        // different name -> returns false
        EditPrescriptionDescriptor editedAspirin = new EditPrescriptionDescriptorBuilder(DESC_ASPIRIN)
                .withName(VALID_NAME_PROPRANOLOL).build();
        assertFalse(DESC_ASPIRIN.equals(editedAspirin));

        // different dosage -> returns false
        editedAspirin = new EditPrescriptionDescriptorBuilder(DESC_ASPIRIN)
                .withDosage(VALID_DOSAGE_PROPRANOLOL).build();
        assertFalse(DESC_ASPIRIN.equals(editedAspirin));

        // different frequency -> returns false
        editedAspirin = new EditPrescriptionDescriptorBuilder(DESC_ASPIRIN)
                .withFrequency(VALID_FREQUENCY_PROPRANOLOL).build();
        assertFalse(DESC_ASPIRIN.equals(editedAspirin));

        // different start date -> returns false
        editedAspirin = new EditPrescriptionDescriptorBuilder(DESC_ASPIRIN)
                .withStartDate(VALID_START_DATE_PROPRANOLOL).build();
        assertFalse(DESC_ASPIRIN.equals(editedAspirin));

        // different end date -> returns false
        editedAspirin = new EditPrescriptionDescriptorBuilder(DESC_ASPIRIN)
                .withEndDate(VALID_END_DATE_PROPRANOLOL).build();
        assertFalse(DESC_ASPIRIN.equals(editedAspirin));

        // different expiry date -> returns false
        editedAspirin = new EditPrescriptionDescriptorBuilder(DESC_ASPIRIN)
                .withExpiryDate(VALID_EXPIRY_DATE_PROPRANOLOL).build();
        assertFalse(DESC_ASPIRIN.equals(editedAspirin));

        // different total stock -> returns false
        editedAspirin = new EditPrescriptionDescriptorBuilder(DESC_ASPIRIN)
                .withTotalStock(VALID_STOCK_PROPRANOLOL).build();
        assertFalse(DESC_ASPIRIN.equals(editedAspirin));

        //different consumption count -> returns false
        editedAspirin = new EditPrescriptionDescriptorBuilder(DESC_ASPIRIN)
                .withConsumptionCount(VALID_CONSUMPTION_PROPRANOLOL).build();
        assertFalse(DESC_ASPIRIN.equals(editedAspirin));

        // different note -> returns false
        editedAspirin = new EditPrescriptionDescriptorBuilder(DESC_ASPIRIN)
                .withNote(VALID_NOTE_PROPRANOLOL).build();
        assertFalse(DESC_ASPIRIN.equals(editedAspirin));
    }
}
