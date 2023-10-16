package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalSchedules.SCHEDULE_ALICE_FIRST_JAN;
import static seedu.address.testutil.TypicalSchedules.SCHEDULE_BOB_SECOND_JAN;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.schedule.EndTime;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.StartTime;
import seedu.address.model.schedule.exceptions.DuplicateScheduleException;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.ScheduleBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }

    @Test
    public void hasSchedule_nullSchedule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasSchedule(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasSchedule_scheduleNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasSchedule(SCHEDULE_ALICE_FIRST_JAN));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        assertTrue(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasSchedule_scheduleInAddressBook_returnsTrue() {
        addressBook.addSchedule(SCHEDULE_ALICE_FIRST_JAN);
        assertTrue(addressBook.hasSchedule(SCHEDULE_ALICE_FIRST_JAN));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void hasSchedule_scheduleWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addSchedule(SCHEDULE_ALICE_FIRST_JAN);
        Schedule editedSchedule = new ScheduleBuilder(SCHEDULE_ALICE_FIRST_JAN).build();
        assertTrue(addressBook.hasSchedule(editedSchedule));
    }

    @Test
    public void addSchedule_duplicateSchedule_throwsDuplicateScheduleException() {
        StartTime startTime = new StartTime(LocalDateTime.of(2023, 1,
                1, 0, 0, 0));
        EndTime endTime = new EndTime(LocalDateTime.of(2023, 1,
                1, 1, 0, 0));
        Schedule schedule = new Schedule(ALICE, startTime, endTime);
        addressBook.addSchedule(schedule);
        assertThrows(DuplicateScheduleException.class, () -> addressBook.addSchedule(schedule));
    }

    @Test
    public void setSchedule_scheduleInAddressBook_updatesSchedule() {
        addressBook.addSchedule(SCHEDULE_ALICE_FIRST_JAN);
        addressBook.setSchedule(SCHEDULE_ALICE_FIRST_JAN, SCHEDULE_BOB_SECOND_JAN);

        assertTrue(addressBook.hasSchedule(SCHEDULE_BOB_SECOND_JAN));
        assertFalse(addressBook.hasSchedule(SCHEDULE_ALICE_FIRST_JAN));
    }

    @Test
    public void removeSchedule_scheduleInAddressBook_removesSchedule() {
        addressBook.addSchedule(SCHEDULE_ALICE_FIRST_JAN);
        addressBook.removeSchedule(SCHEDULE_ALICE_FIRST_JAN);
        assertFalse(addressBook.hasSchedule(SCHEDULE_ALICE_FIRST_JAN));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    @Test
    public void getScheduleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getScheduleList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = AddressBook.class.getCanonicalName()
                + "{persons=" + addressBook.getPersonList() + ", schedules=" + addressBook.getScheduleList() + "}";
        assertEquals(expected, addressBook.toString());
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        private final ObservableList<Schedule> schedules = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Schedule> getScheduleList() {
            return schedules;
        }
    }

}
