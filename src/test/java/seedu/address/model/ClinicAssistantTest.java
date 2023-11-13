package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TestUtil.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.doctor.Doctor;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.timeslots.Timeslot;
import seedu.address.testutil.PersonBuilder;

public class ClinicAssistantTest {

    private final ClinicAssistant clinicAssistant = new ClinicAssistant();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), clinicAssistant.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> clinicAssistant.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        ClinicAssistant newData = getTypicalAddressBook();
        clinicAssistant.resetData(newData);
        assertEquals(newData, clinicAssistant);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        ClinicAssistantStub newData = new ClinicAssistantStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> clinicAssistant.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> clinicAssistant.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(clinicAssistant.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        clinicAssistant.addPerson(ALICE);
        assertTrue(clinicAssistant.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        clinicAssistant.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(clinicAssistant.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> clinicAssistant.getPersonList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = ClinicAssistant.class.getCanonicalName() + "{persons=" + clinicAssistant.getPersonList()
                + "}";
        assertEquals(expected, clinicAssistant.toString());
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class ClinicAssistantStub implements ReadOnlyClinicAssistant {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        private final ObservableList<Timeslot> timeslots = FXCollections.observableArrayList();

        private final ObservableList<Doctor> doctors = FXCollections.observableArrayList();

        ClinicAssistantStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Appointment> getAppointmentList() {
            return appointments;
        }

        @Override
        public boolean hasAppointment(Appointment appointment) {
            return appointments.contains(appointment);
        }

        @Override
        public ObservableList<Doctor> getDoctorList() {
            return doctors;
        }

        @Override
        public ObservableList<Timeslot> getTimeSlotList() {
            return timeslots;
        }
    }

}
