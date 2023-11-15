package seedu.address.model.prescription;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DOSAGE_PROPRANOLOL;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPrescriptions.ASPIRIN;
import static seedu.address.testutil.TypicalPrescriptions.PROPRANOLOL;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.prescription.exceptions.DuplicatePrescriptionException;
import seedu.address.model.prescription.exceptions.PrescriptionNotFoundException;
import seedu.address.testutil.PrescriptionBuilder;

public class UniquePrescriptionListTest {

    private final UniquePrescriptionList uniquePrescriptionList = new UniquePrescriptionList();

    @Test
    public void contains_nullPrescription_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePrescriptionList.contains(null));
    }

    @Test
    public void contains_prescriptionNotInList_returnsFalse() {
        assertFalse(uniquePrescriptionList.contains(ASPIRIN));
    }

    @Test
    public void contains_prescriptionInList_returnsTrue() {
        uniquePrescriptionList.add(ASPIRIN);
        assertTrue(uniquePrescriptionList.contains(ASPIRIN));
    }

    @Test
    public void contains_prescriptionWithSameIdentityFieldsInList_returnsTrue() {
        uniquePrescriptionList.add(ASPIRIN);
        Prescription editedAspirin = new PrescriptionBuilder(ASPIRIN).withDosage(VALID_DOSAGE_PROPRANOLOL).build();
        assertTrue(uniquePrescriptionList.contains(editedAspirin));
    }

    @Test
    public void add_nullPrescription_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePrescriptionList.add(null));
    }

    @Test
    public void add_duplicatePrescription_throwsDuplicatePrescriptionException() {
        uniquePrescriptionList.add(ASPIRIN);
        assertThrows(DuplicatePrescriptionException.class, () -> uniquePrescriptionList.add(ASPIRIN));
    }

    @Test
    public void setPrescription_nullTargetPrescription_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePrescriptionList.setPrescription(null, ASPIRIN));
    }

    @Test
    public void setPrescription_nullEditedPrescription_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePrescriptionList.setPrescription(ASPIRIN, null));
    }

    @Test
    public void setPrescription_targetPrescriptionNotInList_throwsPrescriptionNotFoundException() {
        assertThrows(PrescriptionNotFoundException.class, () ->
            uniquePrescriptionList.setPrescription(ASPIRIN, ASPIRIN));
    }

    @Test
    public void setPrescription_editedPrescriptionIsSamePrescription_success() {
        uniquePrescriptionList.add(ASPIRIN);
        uniquePrescriptionList.setPrescription(ASPIRIN, ASPIRIN);
        UniquePrescriptionList expectedUniquePrescriptionList = new UniquePrescriptionList();
        expectedUniquePrescriptionList.add(ASPIRIN);
        assertEquals(expectedUniquePrescriptionList, uniquePrescriptionList);
    }

    @Test
    public void setPrescription_editedPrescriptionHasSameIdentity_success() {
        uniquePrescriptionList.add(ASPIRIN);
        Prescription editedAspirin = new PrescriptionBuilder(ASPIRIN).withDosage(VALID_DOSAGE_PROPRANOLOL).build();
        uniquePrescriptionList.setPrescription(ASPIRIN, editedAspirin);
        UniquePrescriptionList expectedUniquePrescriptionList = new UniquePrescriptionList();
        expectedUniquePrescriptionList.add(editedAspirin);
        assertEquals(expectedUniquePrescriptionList, uniquePrescriptionList);
    }

    @Test
    public void setPrescription_editedPrescriptionHasDifferentIdentity_success() {
        uniquePrescriptionList.add(ASPIRIN);
        uniquePrescriptionList.setPrescription(ASPIRIN, PROPRANOLOL);
        UniquePrescriptionList expectedUniquePrescriptionList = new UniquePrescriptionList();
        expectedUniquePrescriptionList.add(PROPRANOLOL);
        assertEquals(expectedUniquePrescriptionList, uniquePrescriptionList);
    }

    @Test
    public void setPrescription_editedPrescriptionHasNonUniqueIdentity_throwsDuplicatePrescriptionException() {
        uniquePrescriptionList.add(ASPIRIN);
        uniquePrescriptionList.add(PROPRANOLOL);
        assertThrows(DuplicatePrescriptionException.class, () ->
            uniquePrescriptionList.setPrescription(ASPIRIN, PROPRANOLOL));
    }

    @Test
    public void remove_nullPrescription_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePrescriptionList.remove(null));
    }

    @Test
    public void remove_prescriptionDoesNotExist_throwsPrescriptionNotFoundException() {
        assertThrows(PrescriptionNotFoundException.class, () -> uniquePrescriptionList.remove(ASPIRIN));
    }

    @Test
    public void remove_existingPrescription_removesPrescription() {
        uniquePrescriptionList.add(ASPIRIN);
        uniquePrescriptionList.remove(ASPIRIN);
        UniquePrescriptionList expectedUniquePrescriptionList = new UniquePrescriptionList();
        assertEquals(expectedUniquePrescriptionList, uniquePrescriptionList);
    }

    @Test
    public void setPrescriptions_nullUniquePrescriptionList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
            uniquePrescriptionList.setPrescriptions((UniquePrescriptionList) null));
    }

    @Test
    public void setPrescriptions_uniquePrescriptionList_replacesOwnListWithProvidedUniquePrescriptionList() {
        uniquePrescriptionList.add(ASPIRIN);
        UniquePrescriptionList expectedUniquePrescriptionList = new UniquePrescriptionList();
        expectedUniquePrescriptionList.add(PROPRANOLOL);
        uniquePrescriptionList.setPrescriptions(expectedUniquePrescriptionList);
        assertEquals(expectedUniquePrescriptionList, uniquePrescriptionList);
    }

    @Test
    public void setPrescriptions_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
            uniquePrescriptionList.setPrescriptions((List<Prescription>) null));
    }

    @Test
    public void setPrescriptions_list_replacesOwnListWithProvidedList() {
        uniquePrescriptionList.add(ASPIRIN);
        List<Prescription> prescriptionList = Collections.singletonList(PROPRANOLOL);
        uniquePrescriptionList.setPrescriptions(prescriptionList);
        UniquePrescriptionList expectedUniquePrescriptionList = new UniquePrescriptionList();
        expectedUniquePrescriptionList.add(PROPRANOLOL);
        assertEquals(expectedUniquePrescriptionList, uniquePrescriptionList);
    }

    @Test
    public void setPrescriptions_listWithDuplicatePrescriptions_throwsDuplicatePrescriptionException() {
        List<Prescription> listWithDuplicatePrescriptions = Arrays.asList(ASPIRIN, ASPIRIN);
        assertThrows(DuplicatePrescriptionException.class, () ->
            uniquePrescriptionList.setPrescriptions(listWithDuplicatePrescriptions));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
            uniquePrescriptionList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniquePrescriptionList.asUnmodifiableObservableList().toString(),
            uniquePrescriptionList.toString());
    }
}
