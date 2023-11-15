package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DOSAGE_PROPRANOLOL;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPrescriptions.ASPIRIN;
import static seedu.address.testutil.TypicalPrescriptions.getTypicalPrescriptionList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.prescription.exceptions.DuplicatePrescriptionException;
import seedu.address.testutil.PrescriptionBuilder;

public class PrescriptionListTest {

    private final PrescriptionList prescriptionList = new PrescriptionList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), prescriptionList.getPrescriptionList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> prescriptionList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyPrescriptionList_replacesData() {
        PrescriptionList newData = getTypicalPrescriptionList();
        prescriptionList.resetData(newData);
        assertEquals(newData, prescriptionList);
    }

    @Test
    public void resetData_withDuplicatePrescriptions_throwsDuplicatePrescriptionException() {
        // Two prescription with the same identity fields
        Prescription editedAspirin = new PrescriptionBuilder(ASPIRIN).withDosage(VALID_DOSAGE_PROPRANOLOL)
            .build();
        List<Prescription> newPrescriptions = Arrays.asList(ASPIRIN, editedAspirin);
        PrescriptionListStub newData = new PrescriptionListStub(newPrescriptions);

        assertThrows(DuplicatePrescriptionException.class, () -> prescriptionList.resetData(newData));
    }

    @Test
    public void hasPrescription_nullPrescription_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> prescriptionList.hasPrescription(null));
    }

    @Test
    public void hasPrescription_prescriptionNotInPrescriptionList_returnsFalse() {
        assertFalse(prescriptionList.hasPrescription(ASPIRIN));
    }

    @Test
    public void hasPrescription_prescriptionInPrescriptionList_returnsTrue() {
        prescriptionList.addPrescription(ASPIRIN);
        assertTrue(prescriptionList.hasPrescription(ASPIRIN));
    }

    @Test
    public void hasPrescription_prescriptionWithSameIdentityFieldsInPrescriptionList_returnsTrue() {
        prescriptionList.addPrescription(ASPIRIN);
        Prescription editedAspirin = new PrescriptionBuilder(ASPIRIN).withDosage(VALID_DOSAGE_PROPRANOLOL).build();
        assertTrue(prescriptionList.hasPrescription(editedAspirin));
    }

    @Test
    public void getPrescriptionList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> prescriptionList.getPrescriptionList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = PrescriptionList.class.getCanonicalName()
                + "{prescriptions=" + prescriptionList.getPrescriptionList() + "}";
        assertEquals(expected, prescriptionList.toString());
    }

    /**
     * A stub ReadOnlyPrescriptionList whose prescriptions list can violate interface constraints.
     */
    private static class PrescriptionListStub implements ReadOnlyPrescriptionList {
        private final ObservableList<Prescription> prescriptions = FXCollections.observableArrayList();

        PrescriptionListStub(Collection<Prescription> prescriptions) {
            this.prescriptions.setAll(prescriptions);
        }

        @Override
        public ObservableList<Prescription> getPrescriptionList() {
            return prescriptions;
        }
    }

}
