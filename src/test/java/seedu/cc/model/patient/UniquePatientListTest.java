package seedu.cc.model.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.cc.testutil.TypicalPatients.ALICE;
import static seedu.cc.testutil.TypicalPatients.BENSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.cc.model.patient.exceptions.DuplicatePatientException;
import seedu.cc.model.patient.exceptions.PatientNotFoundException;

public class UniquePatientListTest {

    private final UniquePatientList uniquePatientList = new UniquePatientList();

    @Test
    public void contains_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.contains(null));
    }

    @Test
    public void contains_patientNotInList_returnsFalse() {
        assertFalse(uniquePatientList.contains(ALICE));
    }

    @Test
    public void contains_patientInList_returnsTrue() {
        uniquePatientList.add(ALICE);
        assertTrue(uniquePatientList.contains(ALICE));
    }

    @Test
    public void add_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.add(null));
    }

    @Test
    public void add_duplicatePatient_throwsDuplicatePatientException() {
        uniquePatientList.add(ALICE);
        assertThrows(DuplicatePatientException.class, () -> uniquePatientList.add(ALICE));
    }

    @Test
    public void setPatient_nullTargetPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.setPatient(null, ALICE));
    }

    @Test
    public void setPatient_nullEditedPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.setPatient(ALICE, null));
    }

    @Test
    public void setPatient_targetPatientNotInList_throwsPatientNotFoundException() {
        assertThrows(PatientNotFoundException.class, () -> uniquePatientList.setPatient(ALICE, ALICE));
    }

    @Test
    public void setPatient_editedPatientIsSamePatient_success() {
        uniquePatientList.add(ALICE);
        uniquePatientList.setPatient(ALICE, ALICE);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        expectedUniquePatientList.add(ALICE);
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setPatient_editedPatientHasDifferentIdentity_success() {
        uniquePatientList.add(ALICE);
        uniquePatientList.setPatient(ALICE, BENSON);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        expectedUniquePatientList.add(BENSON);
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void remove_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.remove(null));
    }

    @Test
    public void remove_patientDoesNotExist_throwsPatientNotFoundException() {
        assertThrows(PatientNotFoundException.class, () -> uniquePatientList.remove(ALICE));
    }

    @Test
    public void remove_existingPatient_removesPatient() {
        uniquePatientList.add(ALICE);
        uniquePatientList.remove(ALICE);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setPatients_nullUniquePatientList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.setPatients((UniquePatientList) null));
    }

    @Test
    public void setPatients_uniquePatientList_replacesOwnListWithProvidedUniquePatientList() {
        uniquePatientList.add(ALICE);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        expectedUniquePatientList.add(BENSON);
        uniquePatientList.setPatients(expectedUniquePatientList);
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setPatients_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniquePatientList.setPatients((List<Patient>) null));
    }

    @Test
    public void setPatients_list_replacesOwnListWithProvidedList() {
        uniquePatientList.add(ALICE);
        List<Patient> patientList = Collections.singletonList(BENSON);
        uniquePatientList.setPatients(patientList);
        UniquePatientList expectedUniquePatientList = new UniquePatientList();
        expectedUniquePatientList.add(BENSON);
        assertEquals(expectedUniquePatientList, uniquePatientList);
    }

    @Test
    public void setPatients_listWithDuplicatePatients_throwsDuplicatePatientException() {
        List<Patient> listWithDuplicatePatients = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicatePatientException.class, () -> uniquePatientList.setPatients(listWithDuplicatePatients));
    }



    @Test
    public void equals() {
        UniquePatientList uniquePatientListFirst = new UniquePatientList();
        UniquePatientList uniquePatientListSecond = new UniquePatientList();

        // same object -> returns true
        assertTrue(uniquePatientListFirst.equals(uniquePatientListFirst));

        // null -> returns false
        assertFalse(uniquePatientListFirst.equals(null));

        // different type -> returns false
        assertFalse(uniquePatientListFirst.equals(5));

        // different person -> returns false
        uniquePatientListFirst.add(ALICE);
        uniquePatientListSecond.add(BENSON);
        assertFalse(uniquePatientListFirst.equals(uniquePatientListSecond));

        // same persons -> returns true
        uniquePatientListSecond.setPatients(Arrays.asList(ALICE));
        assertTrue(uniquePatientListFirst.equals(uniquePatientListSecond));
    }

    @Test
    public void hashCode_sameList_sameHashCode() {
        UniquePatientList uniquePatientListFirst = new UniquePatientList();
        UniquePatientList uniquePatientListSecond = new UniquePatientList();

        uniquePatientListFirst.add(ALICE);
        uniquePatientListFirst.add(BENSON);

        uniquePatientListSecond.add(ALICE);
        uniquePatientListSecond.add(BENSON);

        assertEquals(uniquePatientListFirst.hashCode(), uniquePatientListSecond.hashCode());
    }

    @Test
    public void hashCode_differentList_differentHashCode() {
        UniquePatientList uniquePatientListFirst = new UniquePatientList();
        UniquePatientList uniquePatientListSecond = new UniquePatientList();

        uniquePatientListFirst.add(ALICE);

        uniquePatientListSecond.add(BENSON);

        assertNotEquals(uniquePatientListFirst.hashCode(), uniquePatientListSecond.hashCode());
    }
}
