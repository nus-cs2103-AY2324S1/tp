package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.exceptions.DuplicateObjectException;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.ObjectNotFoundException;

/**
 * A list of Appointments that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Appointment#equals(Appointment)} As such, adding and updating of
 * Appointments uses Appointment#equals(Appointment) for equality to ensure that the person being added or updated is
 * unique in terms of identity in the UniqueAppointmentList.
 *
 * Supports a minimal set of list operations.
 *
 */
public class UniqueAppointmentList extends UniqueObjectList<Appointment> {

    protected final ObservableList<Appointment> internalList = FXCollections.observableArrayList();
    protected final ObservableList<Appointment> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    public void setAppointments(UniqueAppointmentList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Returns true if the list contains an equivalent appointment as the given argument.
     */
    @Override
    public boolean contains(Appointment toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds an Appointment to the list.
     * The appointment must not already exist in the list.
     */
    @Override
    public void add(Appointment toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateObjectException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the appointment {@code target} in the list with {@code editedAppointment}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedAppointment} must not be the same as another existing appointment in the list.
     */
    @Override
    public void setObject(Appointment target, Appointment editedAppointment) {
        requireAllNonNull(target, editedAppointment);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ObjectNotFoundException();
        }

        if (!target.equals(editedAppointment) && contains(editedAppointment)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedAppointment);
    }

    /**
     * Removes the equivalent Appointment from the list.
     * The appointment must exist in the list.
     */
    @Override
    public void remove(Appointment toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ObjectNotFoundException();
        }
    }

    /**
     * Replaces the contents of this list with {@code Appointments}.
     * {@code Appointments} must not contain duplicate Appointments.
     */
    @Override
    public void setObjects(List<Appointment> appointments) {
        requireAllNonNull(appointments);
        if (!objectsAreUnique(appointments)) {
            throw new DuplicateObjectException();
        }

        internalList.setAll(appointments);
    }

    @Override
    public ObservableList<Appointment> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Returns true if {@code Appointments} contains only unique Appointments.
     */
    @Override
    protected boolean objectsAreUnique(List<Appointment> appointments) {
        for (int i = 0; i < appointments.size() - 1; i++) {
            for (int j = i + 1; j < appointments.size(); j++) {
                if (appointments.get(i).equals(appointments.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
