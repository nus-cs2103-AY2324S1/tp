package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.appointment.exceptions.AppointmentNotFoundException;
import seedu.address.model.appointment.exceptions.DuplicateAppointmentException;

/**
 * A list of appointments that enforces uniqueness between its elements and does not allow nulls.
 * A appointment is considered unique by comparing using {@code Appointment#isSameAppointment(Appointment)}.
 * As such, adding and updating of appointments uses Appointment#isSameAppointment(Appointment) for equality
 * so as to ensure that the appointment being added or updated is unique in the UniqueAppointmentList.
 * However, the removal of a person uses Appointment#equals(Object) so as to ensure that the appointment
 * with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Appointment#isSameAppointment(Appointment)
 */
public class UniqueAppointmentList implements Iterable<Appointment> {

    private final ObservableList<Appointment> internalList = FXCollections.observableArrayList();
    private final ObservableList<Appointment> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent Appointment as the given argument.
     */
    public boolean contains(Appointment toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameAppointment);
    }

    /**
     * Adds an Appointment to the list.
     * The Appointment must not already exist in the list.
     */
    public void add(Appointment toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateAppointmentException();
        }
        internalList.add(toAdd);
        internalList.sort(Comparator.comparing(Appointment::getDateTime));
    }

    /**
     * Deletes an Appointment from the list.
     */
    public void delete(Appointment appointment) {
        requireAllNonNull(appointment);
        internalList.remove(appointment);
    }

    /**
     * Replaces the Appointment {@code target} in the list with {@code editedAppointment}.
     * {@code target} must exist in the list.
     * The Appointment of {@code editedAppointment} must not be the same as another existing Appointment in the list.
     */
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireAllNonNull(target, editedAppointment);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new AppointmentNotFoundException();
        }

        if (!target.isSameAppointment(editedAppointment) && contains(editedAppointment)) {
            throw new DuplicateAppointmentException();
        }

        internalList.set(index, editedAppointment);
        internalList.sort(Comparator.comparing(Appointment::getDateTime));
    }

    /**
     * Removes the equivalent Appointment from the list.
     * The Appointment must exist in the list.
     */
    public void remove(Appointment toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new AppointmentNotFoundException();
        }
    }

    public void setAppointments(UniqueAppointmentList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
        internalList.sort(Comparator.comparing(Appointment::getDateTime));
    }

    /**
     * Replaces the contents of this list with {@code appointments}.
     * {@code appointments} must not contain duplicate Appointments.
     */
    public void setAppointments(List<Appointment> appointments) {
        requireAllNonNull(appointments);
        if (!appointmentsAreUnique(appointments)) {
            throw new DuplicateAppointmentException();
        }

        internalList.setAll(appointments);
        internalList.sort(Comparator.comparing(Appointment::getDateTime));
    }

    /**
     * Replaces the Person Appointments with new Appointments with newly edited Person upon editing said Person.
     *
     * @param oldAppointments old Appointments of the Person
     * @param toReplace new Appointments of the Person
     */
    public void editPersonAppointments(ArrayList<Appointment> oldAppointments, ArrayList<Appointment> toReplace) {
        requireAllNonNull(oldAppointments, toReplace);

        // Create a copy of the internalList
        List<Appointment> appointmentsList = new ArrayList<>(internalList);

        // Iterate through oldAppointments and replace with toReplace at the same index
        for (int i = 0; i < appointmentsList.size(); i++) {
            if (oldAppointments.contains(appointmentsList.get(i))) {
                int replaceIndex = oldAppointments.indexOf(appointmentsList.get(i));
                if (replaceIndex < toReplace.size()) {
                    // Replace with the corresponding appointment from toReplace
                    appointmentsList.set(i, toReplace.get(replaceIndex));
                }
            }
        }

        // Clear the internalList and add all elements from the updated appointmentsList
        internalList.clear();
        internalList.addAll(appointmentsList);
        internalList.sort(Comparator.comparing(Appointment::getDateTime));
    }

    /**
     * Adds to the contents of this list with {@code appointments}.
     * {@code appointments} must not contain duplicate Appointments.
     */
    public void addAll(List<Appointment> appointments) {
        requireAllNonNull(appointments);
        if (!appointmentsAreUnique(appointments)) {
            throw new DuplicateAppointmentException();
        }

        internalList.addAll(appointments);
        internalList.sort(Comparator.comparing(Appointment::getDateTime));
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Appointment> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Appointment> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueAppointmentList)) {
            return false;
        }

        UniqueAppointmentList otherUniqueAppointmentList = (UniqueAppointmentList) other;
        return internalList.equals(otherUniqueAppointmentList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code appointments} contains only unique Appointments.
     */
    private boolean appointmentsAreUnique(List<Appointment> appointments) {
        for (int i = 0; i < appointments.size() - 1; i++) {
            for (int j = i + 1; j < appointments.size(); j++) {
                if (appointments.get(i).isSameAppointment(appointments.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
