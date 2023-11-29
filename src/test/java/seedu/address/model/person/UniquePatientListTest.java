package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPatients.PATIENT_ALICE;
import static seedu.address.testutil.TypicalPatients.PATIENT_BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PatientNotFoundException;
import seedu.address.model.person.patients.Patient;
import seedu.address.testutil.PatientBuilder;

public class UniquePatientListTest {

    private final UniquePatientList uniquePatientList = new UniquePatientList();

    @Test
    public void contains_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.contains(null));
    }

    @Test
    public void contains_patientNotInList_returnsFalse() {
        assertFalse(uniquePatientList.contains(PATIENT_ALICE));
    }

    @Test
    public void contains_patientInList_returnsTrue() {
        uniquePatientList.add(PATIENT_ALICE);
        assertTrue(uniquePatientList.contains(PATIENT_ALICE));
    }

    @Test
    public void contains_patientWithSameIdentityFieldsInList_returnsTrue() {
        uniquePatientList.add(PATIENT_ALICE);
        Patient editedAlice = new PatientBuilder(PATIENT_ALICE)
                .withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniquePatientList.contains(editedAlice));
    }

    @Test
    public void add_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.add(null));
    }

    @Test
    public void add_duplicatePatient_throwsDuplicatePersonException() {
        uniquePatientList.add(PATIENT_ALICE);
        assertThrows(DuplicatePersonException.class, () -> uniquePatientList.add(PATIENT_ALICE));
    }

    @Test
    public void setPerson_nullTargetPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.setPatient(null, PATIENT_ALICE));
    }

    @Test
    public void setPerson_nullEditedPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.setPatient(PATIENT_ALICE, null));
    }

    @Test
    public void setPerson_targetPatientNotInList_throwsPersonNotFoundException() {
        assertThrows(PatientNotFoundException.class, () -> uniquePatientList.setPatient(PATIENT_ALICE, PATIENT_ALICE));
    }

    @Test
    public void setPerson_editedPatientIsSamePerson_success() {
        uniquePatientList.add(PATIENT_ALICE);
        uniquePatientList.setPatient(PATIENT_ALICE, PATIENT_ALICE);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        expectedUniquePatientList.add(PATIENT_ALICE);
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setPerson_editedPatientHasSameIdentity_success() {
        uniquePatientList.add(PATIENT_ALICE);
        Patient editedAlice = new PatientBuilder(PATIENT_ALICE)
                .withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND)
                .build();
        uniquePatientList.setPatient(PATIENT_ALICE, editedAlice);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        expectedUniquePatientList.add(editedAlice);
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setPerson_editedPatientHasDifferentIdentity_success() {
        uniquePatientList.add(PATIENT_ALICE);
        uniquePatientList.setPatient(PATIENT_ALICE, PATIENT_BOB);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        expectedUniquePatientList.add(PATIENT_BOB);
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setPerson_editedPatientHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniquePatientList.add(PATIENT_ALICE);
        uniquePatientList.add(PATIENT_BOB);
        assertThrows(DuplicatePersonException.class, () -> uniquePatientList.setPatient(PATIENT_ALICE, PATIENT_BOB));
    }

    @Test
    public void remove_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.remove(null));
    }

    @Test
    public void remove_patientDoesNotExist_throwsPatientNotFoundException() {
        assertThrows(PatientNotFoundException.class, () -> uniquePatientList.remove(PATIENT_ALICE));
    }

    @Test
    public void remove_existingPatient_removesPatient() {
        uniquePatientList.add(PATIENT_ALICE);
        uniquePatientList.remove(PATIENT_ALICE);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setPatients_nullUniquePatientList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.setPatients((UniquePatientList) null));
    }

    @Test
    public void setPatients_uniquePatientList_replacesOwnListWithProvidedUniquePatientList() {
        uniquePatientList.add(PATIENT_ALICE);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        expectedUniquePatientList.add(PATIENT_BOB);
        uniquePatientList.setPatients(expectedUniquePatientList);
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setPatients_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.setPatients((List<Patient>) null));
    }

    @Test
    public void setPatients_list_replacesOwnListWithProvidedList() {
        uniquePatientList.add(PATIENT_ALICE);
        List<Patient> patientList = Collections.singletonList(PATIENT_BOB);
        uniquePatientList.setPatients(patientList);
        UniquePatientList expecteduniquePatientList = new UniquePatientList();
        expecteduniquePatientList.add(PATIENT_BOB);
        assertEquals(expecteduniquePatientList, uniquePatientList);
    }

    @Test
    public void setPatients_listWithDuplicatePatients_throwsDuplicatePersonException() {
        List<Patient> listWithDuplicatePatients = Arrays.asList(PATIENT_ALICE, PATIENT_ALICE);
        assertThrows(DuplicatePersonException.class, () -> uniquePatientList.setPatients(listWithDuplicatePatients));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniquePatientList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniquePatientList.asUnmodifiableObservableList().toString(), uniquePatientList.toString());
    }
}
