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
    public void contains_nullEnrolment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEnrolmentList.contains(null));
    }

    @Test
    public void contains_enrolmentNotInList_returnsFalse() {
        assertFalse(uniqueEnrolmentList.contains(ALICE_AURORA));
    }

    @Test
    public void contains_enrolmentInList_returnsTrue() {
        uniqueEnrolmentList.createEnrolment(ALICE_AURORA);
        assertTrue(uniqueEnrolmentList.contains(ALICE_AURORA));
    }

    @Test
    public void contains_eventWithSameIdentityFieldsInList_returnsTrue() {
        uniqueEnrolmentList.createEnrolment(ALICE_AURORA);
        Enrolment editedHours = new EnrolmentBuilder(ALICE_AURORA).withHours(VALID_HOURS_AURORA)
                .build();
        assertTrue(uniqueEnrolmentList.contains(editedHours));
    }

    @Test
    public void add_nullEnrolment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEnrolmentList.createEnrolment(null));
    }

    @Test
    public void add_duplicateEnrolment_throwsDuplicateEnrolmentException() {
        uniqueEnrolmentList.createEnrolment(ALICE_AURORA);
        assertThrows(DuplicateEnrolmentException.class, () -> uniqueEnrolmentList.createEnrolment(ALICE_AURORA));
    }

    @Test
    public void setEnrolment_nullTargetEnrolment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEnrolmentList.setEnrolment(null, ALICE_AURORA));
    }

    @Test
    public void setEnrolment_nullEditedEnrolment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEnrolmentList.setEnrolment(ALICE_AURORA, null));
    }

    @Test
    public void setEnrolment_targetEnrolmentNotInList_throwsEnrolmentNotFoundException() {
        assertThrows(EnrolmentNotFoundException.class, () ->
                uniqueEnrolmentList.setEnrolment(ALICE_AURORA, ALICE_AURORA));
    }

    @Test
    public void setEnrolment_editedEnrolmentIsSameEnrolment_success() {
        uniqueEnrolmentList.createEnrolment(ALICE_AURORA);
        uniqueEnrolmentList.setEnrolment(ALICE_AURORA, ALICE_AURORA);
        UniqueEnrolmentList expectedUniqueEnrolmentList = new UniqueEnrolmentList();
        expectedUniqueEnrolmentList.createEnrolment(ALICE_AURORA);
        assertEquals(expectedUniqueEnrolmentList, uniqueEnrolmentList);
    }

    @Test
    public void setEvent_editedEnrolmentHasSameIdentity_success() {
        uniqueEnrolmentList.createEnrolment(ALICE_AURORA);
        Enrolment editedHours = new EnrolmentBuilder(ALICE_AURORA).withHours(VALID_HOURS_AURORA)
                .build();
        uniqueEnrolmentList.setEnrolment(ALICE_AURORA, editedHours);
        UniqueEnrolmentList expectedUniqueEventList = new UniqueEnrolmentList();
        expectedUniqueEventList.createEnrolment(editedHours);
        assertEquals(expectedUniqueEventList, uniqueEnrolmentList);
    }

    @Test
    public void setEnrolment_editedEnrolmentHasDifferentIdentity_success() {
        uniqueEnrolmentList.createEnrolment(ALICE_AURORA);
        uniqueEnrolmentList.setEnrolment(ALICE_AURORA, BENSON_BOXING);
        UniqueEnrolmentList expectedUniqueEnrolmentList = new UniqueEnrolmentList();
        expectedUniqueEnrolmentList.createEnrolment(BENSON_BOXING);
        assertEquals(expectedUniqueEnrolmentList, uniqueEnrolmentList);
    }

    @Test
    public void setEnrolment_editedEnrolmentIsNonUnique_throwsDuplicateEnrolmentException() {
        uniqueEnrolmentList.createEnrolment(ALICE_AURORA);
        uniqueEnrolmentList.createEnrolment(BENSON_BOXING);
        assertThrows(DuplicateEnrolmentException.class, () ->
                uniqueEnrolmentList.setEnrolment(ALICE_AURORA, BENSON_BOXING));
    }

    @Test
    public void remove_nullEnrolment_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEnrolmentList.remove(null));
    }

    @Test
    public void remove_enrolmentDoesNotExist_throwsEnrolmentNotFoundException() {
        assertThrows(EnrolmentNotFoundException.class, () -> uniqueEnrolmentList.remove(ALICE_AURORA));
    }

    @Test
    public void remove_existingEnrolment_removesEnrolment() {
        uniqueEnrolmentList.createEnrolment(ALICE_AURORA);
        uniqueEnrolmentList.remove(ALICE_AURORA);
        UniqueEnrolmentList expectedUniqueEnrolmentList = new UniqueEnrolmentList();
        assertEquals(expectedUniqueEnrolmentList, uniqueEnrolmentList);
    }

    @Test
    public void setEnrolments_nullUniqueEnrolmentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueEnrolmentList.setEnrolments((UniqueEnrolmentList) null));
    }

    @Test
    public void setEnrolments_uniqueEnrolmentList_replacesOwnListWithProvidedUniqueEnrolmentList() {
        uniqueEnrolmentList.createEnrolment(ALICE_AURORA);
        UniqueEnrolmentList expectedUniqueEnrolmentList = new UniqueEnrolmentList();
        expectedUniqueEnrolmentList.createEnrolment(BENSON_BOXING);
        uniqueEnrolmentList.setEnrolments(expectedUniqueEnrolmentList);
        assertEquals(expectedUniqueEnrolmentList, uniqueEnrolmentList);
    }

    @Test
    public void setEnrolments_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEnrolmentList.setEnrolments((List<Enrolment>) null));
    }

    @Test
    public void setEnrolments_list_replacesOwnListWithProvidedList() {
        uniqueEnrolmentList.createEnrolment(ALICE_AURORA);
        List<Enrolment> enrolmentList = Collections.singletonList(BENSON_BOXING);
        uniqueEnrolmentList.setEnrolments(enrolmentList);
        UniqueEnrolmentList expectedUniqueEnrolmentList = new UniqueEnrolmentList();
        expectedUniqueEnrolmentList.createEnrolment(BENSON_BOXING);
        assertEquals(expectedUniqueEnrolmentList, uniqueEnrolmentList);
    }

    @Test
    public void setEnrolments_listWithDuplicateEnrolments_throwsDuplicateEnrolmentException() {
        List<Enrolment> listWithDuplicateEnrolments = Arrays.asList(ALICE_AURORA, ALICE_AURORA);
        assertThrows(DuplicateEnrolmentException.class, () ->
                uniqueEnrolmentList.setEnrolments(listWithDuplicateEnrolments));
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
