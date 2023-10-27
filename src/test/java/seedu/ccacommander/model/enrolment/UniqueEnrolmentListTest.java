package seedu.ccacommander.model.enrolment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ccacommander.logic.commands.CommandTestUtil.VALID_HOURS_AURORA;
import static seedu.ccacommander.testutil.Assert.assertThrows;
import static seedu.ccacommander.testutil.TypicalEnrolments.ALICE_AURORA;
import static seedu.ccacommander.testutil.TypicalEnrolments.BENSON_BOXING;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.ccacommander.model.enrolment.exceptions.DuplicateEnrolmentException;
import seedu.ccacommander.model.enrolment.exceptions.EnrolmentNotFoundException;
import seedu.ccacommander.testutil.EnrolmentBuilder;

public class UniqueEnrolmentListTest {

    private final UniqueEnrolmentList uniqueEnrolmentList = new UniqueEnrolmentList();

    @Test
    public void contains_nullAttendance_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEnrolmentList.contains(null));
    }

    @Test
    public void contains_attendanceNotInList_returnsFalse() {
        assertFalse(uniqueEnrolmentList.contains(ALICE_AURORA));
    }

    @Test
    public void contains_attendanceInList_returnsTrue() {
        uniqueEnrolmentList.createAttendance(ALICE_AURORA);
        assertTrue(uniqueEnrolmentList.contains(ALICE_AURORA));
    }

    @Test
    public void contains_eventWithSameIdentityFieldsInList_returnsTrue() {
        uniqueEnrolmentList.createAttendance(ALICE_AURORA);
        Enrolment editedHours = new EnrolmentBuilder(ALICE_AURORA).withHours(VALID_HOURS_AURORA)
                .build();
        assertTrue(uniqueEnrolmentList.contains(editedHours));
    }

    @Test
    public void add_nullAttendance_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEnrolmentList.createAttendance(null));
    }

    @Test
    public void add_duplicateAttendance_throwsDuplicateAttendanceException() {
        uniqueEnrolmentList.createAttendance(ALICE_AURORA);
        assertThrows(DuplicateEnrolmentException.class, () -> uniqueEnrolmentList.createAttendance(ALICE_AURORA));
    }

    @Test
    public void setAttendance_nullTargetAttendance_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEnrolmentList.setAttendance(null, ALICE_AURORA));
    }

    @Test
    public void setAttendance_nullEditedAttendance_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEnrolmentList.setAttendance(ALICE_AURORA, null));
    }

    @Test
    public void setAttendance_targetAttendanceNotInList_throwsAttendanceNotFoundException() {
        assertThrows(EnrolmentNotFoundException.class, () ->
                uniqueEnrolmentList.setAttendance(ALICE_AURORA, ALICE_AURORA));
    }

    @Test
    public void setAttendance_editedAttendanceIsSameAttendance_success() {
        uniqueEnrolmentList.createAttendance(ALICE_AURORA);
        uniqueEnrolmentList.setAttendance(ALICE_AURORA, ALICE_AURORA);
        UniqueEnrolmentList expectedUniqueEnrolmentList = new UniqueEnrolmentList();
        expectedUniqueEnrolmentList.createAttendance(ALICE_AURORA);
        assertEquals(expectedUniqueEnrolmentList, uniqueEnrolmentList);
    }

    @Test
    public void setEvent_editedAttendanceHasSameIdentity_success() {
        uniqueEnrolmentList.createAttendance(ALICE_AURORA);
        Enrolment editedHours = new EnrolmentBuilder(ALICE_AURORA).withHours(VALID_HOURS_AURORA)
                .build();
        uniqueEnrolmentList.setAttendance(ALICE_AURORA, editedHours);
        UniqueEnrolmentList expectedUniqueEventList = new UniqueEnrolmentList();
        expectedUniqueEventList.createAttendance(editedHours);
        assertEquals(expectedUniqueEventList, uniqueEnrolmentList);
    }

    @Test
    public void setAttendance_editedAttendanceHasDifferentIdentity_success() {
        uniqueEnrolmentList.createAttendance(ALICE_AURORA);
        uniqueEnrolmentList.setAttendance(ALICE_AURORA, BENSON_BOXING);
        UniqueEnrolmentList expectedUniqueEnrolmentList = new UniqueEnrolmentList();
        expectedUniqueEnrolmentList.createAttendance(BENSON_BOXING);
        assertEquals(expectedUniqueEnrolmentList, uniqueEnrolmentList);
    }

    @Test
    public void setAttendance_editedAttendanceIsNonUnique_throwsDuplicateAttendanceException() {
        uniqueEnrolmentList.createAttendance(ALICE_AURORA);
        uniqueEnrolmentList.createAttendance(BENSON_BOXING);
        assertThrows(DuplicateEnrolmentException.class, () ->
                uniqueEnrolmentList.setAttendance(ALICE_AURORA, BENSON_BOXING));
    }

    @Test
    public void remove_nullAttendance_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEnrolmentList.remove(null));
    }

    @Test
    public void remove_attendanceDoesNotExist_throwsAttendanceNotFoundException() {
        assertThrows(EnrolmentNotFoundException.class, () -> uniqueEnrolmentList.remove(ALICE_AURORA));
    }

    @Test
    public void remove_existingAttendance_removesAttendance() {
        uniqueEnrolmentList.createAttendance(ALICE_AURORA);
        uniqueEnrolmentList.remove(ALICE_AURORA);
        UniqueEnrolmentList expectedUniqueEnrolmentList = new UniqueEnrolmentList();
        assertEquals(expectedUniqueEnrolmentList, uniqueEnrolmentList);
    }

    @Test
    public void setAttendances_nullUniqueAttendanceList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueEnrolmentList.setAttendances((UniqueEnrolmentList) null));
    }

    @Test
    public void setAttendances_uniqueAttendanceList_replacesOwnListWithProvidedUniqueAttendanceList() {
        uniqueEnrolmentList.createAttendance(ALICE_AURORA);
        UniqueEnrolmentList expectedUniqueEnrolmentList = new UniqueEnrolmentList();
        expectedUniqueEnrolmentList.createAttendance(BENSON_BOXING);
        uniqueEnrolmentList.setAttendances(expectedUniqueEnrolmentList);
        assertEquals(expectedUniqueEnrolmentList, uniqueEnrolmentList);
    }

    @Test
    public void setAttendances_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEnrolmentList.setAttendances((List<Enrolment>) null));
    }

    @Test
    public void setAttendances_list_replacesOwnListWithProvidedList() {
        uniqueEnrolmentList.createAttendance(ALICE_AURORA);
        List<Enrolment> enrolmentList = Collections.singletonList(BENSON_BOXING);
        uniqueEnrolmentList.setAttendances(enrolmentList);
        UniqueEnrolmentList expectedUniqueEnrolmentList = new UniqueEnrolmentList();
        expectedUniqueEnrolmentList.createAttendance(BENSON_BOXING);
        assertEquals(expectedUniqueEnrolmentList, uniqueEnrolmentList);
    }

    @Test
    public void setAttendances_listWithDuplicateAttendances_throwsDuplicateAttendanceException() {
        List<Enrolment> listWithDuplicateEnrolments = Arrays.asList(ALICE_AURORA, ALICE_AURORA);
        assertThrows(DuplicateEnrolmentException.class, () ->
                uniqueEnrolmentList.setAttendances(listWithDuplicateEnrolments));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueEnrolmentList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueEnrolmentList.asUnmodifiableObservableList().toString(), uniqueEnrolmentList.toString());
    }
}
