package seedu.address.model.schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalSchedules.SCHEDULE_ALICE_FIRST_JAN;
import static seedu.address.testutil.TypicalSchedules.SCHEDULE_BOB_SECOND_JAN;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.schedule.exceptions.DuplicateScheduleException;
import seedu.address.model.schedule.exceptions.ScheduleNotFoundException;
import seedu.address.testutil.ScheduleBuilder;

public class UniqueScheduleListTest {
    private final UniqueScheduleList uniqueScheduleList = new UniqueScheduleList();

    @Test
    public void contains_nullSchedule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueScheduleList.contains(null));
    }

    @Test
    public void contains_scheduleNotInList_returnsFalse() {
        assertFalse(uniqueScheduleList.contains(SCHEDULE_ALICE_FIRST_JAN));
    }

    @Test
    public void contains_scheduleInList_returnsTrue() {
        uniqueScheduleList.add(SCHEDULE_ALICE_FIRST_JAN);
        assertTrue(uniqueScheduleList.contains(SCHEDULE_ALICE_FIRST_JAN));
    }

    @Test
    public void contains_scheduleWithSameFieldsInList_returnsTrue() {
        uniqueScheduleList.add(SCHEDULE_ALICE_FIRST_JAN);
        Schedule editedAlice = new ScheduleBuilder(SCHEDULE_ALICE_FIRST_JAN).build();
        assertTrue(uniqueScheduleList.contains(editedAlice));
    }

    @Test
    public void add_nullSchedule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueScheduleList.add(null));
    }

    @Test
    public void add_duplicateSchedule_throwsDuplicateScheduleException() {
        uniqueScheduleList.add(SCHEDULE_ALICE_FIRST_JAN);
        assertThrows(DuplicateScheduleException.class, () -> uniqueScheduleList.add(SCHEDULE_ALICE_FIRST_JAN));
    }

    @Test
    public void setSchedule_nullTargetSchedule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueScheduleList
            .setSchedule(null, SCHEDULE_ALICE_FIRST_JAN));
    }

    @Test
    public void setSchedule_nullEditedSchedule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueScheduleList
            .setSchedule(SCHEDULE_ALICE_FIRST_JAN, null));
    }

    @Test
    public void setSchedule_targetScheduleNotInList_throwsScheduleNotFoundException() {
        assertThrows(ScheduleNotFoundException.class, () -> uniqueScheduleList.setSchedule(SCHEDULE_ALICE_FIRST_JAN,
            SCHEDULE_ALICE_FIRST_JAN));
    }

    @Test
    public void setSchedule_editedScheduleIsSameSchedule_success() {
        uniqueScheduleList.add(SCHEDULE_ALICE_FIRST_JAN);
        uniqueScheduleList.setSchedule(SCHEDULE_ALICE_FIRST_JAN, SCHEDULE_ALICE_FIRST_JAN);
        UniqueScheduleList expectedUniqueScheduleList = new UniqueScheduleList();
        expectedUniqueScheduleList.add(SCHEDULE_ALICE_FIRST_JAN);
        assertEquals(expectedUniqueScheduleList, uniqueScheduleList);
    }

    @Test
    public void setSchedule_editedScheduleHasSameFields_success() {
        uniqueScheduleList.add(SCHEDULE_ALICE_FIRST_JAN);
        Schedule editedAlice = new ScheduleBuilder(SCHEDULE_ALICE_FIRST_JAN).build();
        uniqueScheduleList.setSchedule(SCHEDULE_ALICE_FIRST_JAN, editedAlice);
        UniqueScheduleList expectedUniqueScheduleList = new UniqueScheduleList();
        expectedUniqueScheduleList.add(editedAlice);
        assertEquals(expectedUniqueScheduleList, uniqueScheduleList);
    }

    @Test
    public void setSchedule_editedScheduleHasDifferentFields_success() {
        uniqueScheduleList.add(SCHEDULE_ALICE_FIRST_JAN);
        uniqueScheduleList.setSchedule(SCHEDULE_ALICE_FIRST_JAN, SCHEDULE_BOB_SECOND_JAN);
        UniqueScheduleList expectedUniqueScheduleList = new UniqueScheduleList();
        expectedUniqueScheduleList.add(SCHEDULE_BOB_SECOND_JAN);
        assertEquals(expectedUniqueScheduleList, uniqueScheduleList);
    }

    @Test
    public void setSchedule_editedScheduleHasNonUniqueFields_throwsDuplicateScheduleException() {
        uniqueScheduleList.add(SCHEDULE_ALICE_FIRST_JAN);
        uniqueScheduleList.add(SCHEDULE_BOB_SECOND_JAN);
        assertThrows(DuplicateScheduleException.class, () -> uniqueScheduleList
            .setSchedule(SCHEDULE_ALICE_FIRST_JAN, SCHEDULE_BOB_SECOND_JAN));
    }

    @Test
    public void remove_nullSchedule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueScheduleList.remove(null));
    }

    @Test
    public void remove_scheduleDoesNotExist_throwsScheduleNotFoundException() {
        assertThrows(ScheduleNotFoundException.class, () -> uniqueScheduleList.remove(SCHEDULE_ALICE_FIRST_JAN));
    }

    @Test
    public void remove_existingSchedule_removesSchedule() {
        uniqueScheduleList.add(SCHEDULE_ALICE_FIRST_JAN);
        uniqueScheduleList.remove(SCHEDULE_ALICE_FIRST_JAN);
        UniqueScheduleList expectedUniqueScheduleList = new UniqueScheduleList();
        assertEquals(expectedUniqueScheduleList, uniqueScheduleList);
    }

    @Test
    public void setSchedules_nullUniqueScheduleList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueScheduleList.setSchedules((UniqueScheduleList) null));
    }

    @Test
    public void setSchedules_uniqueScheduleList_replacesOwnListWithProvidedUniqueScheduleList() {
        uniqueScheduleList.add(SCHEDULE_ALICE_FIRST_JAN);
        UniqueScheduleList expectedUniqueScheduleList = new UniqueScheduleList();
        expectedUniqueScheduleList.add(SCHEDULE_BOB_SECOND_JAN);
        uniqueScheduleList.setSchedules(expectedUniqueScheduleList);
        assertEquals(expectedUniqueScheduleList, uniqueScheduleList);
    }

    @Test
    public void setSchedules_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueScheduleList.setSchedules((List<Schedule>) null));
    }

    @Test
    public void setSchedules_list_replacesOwnListWithProvidedList() {
        uniqueScheduleList.add(SCHEDULE_ALICE_FIRST_JAN);
        List<Schedule> scheduleList = Collections.singletonList(SCHEDULE_BOB_SECOND_JAN);
        uniqueScheduleList.setSchedules(scheduleList);
        UniqueScheduleList expectedUniqueScheduleList = new UniqueScheduleList();
        expectedUniqueScheduleList.add(SCHEDULE_BOB_SECOND_JAN);
        assertEquals(expectedUniqueScheduleList, uniqueScheduleList);
    }

    @Test
    public void setSchedules_listWithDuplicateSchedules_throwsDuplicateScheduleException() {
        List<Schedule> listWithDuplicateSchedules = Arrays.asList(SCHEDULE_ALICE_FIRST_JAN, SCHEDULE_ALICE_FIRST_JAN);
        assertThrows(DuplicateScheduleException.class, () -> uniqueScheduleList
            .setSchedules(listWithDuplicateSchedules));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueScheduleList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueScheduleList.asUnmodifiableObservableList().toString(), uniqueScheduleList.toString());
    }
}
