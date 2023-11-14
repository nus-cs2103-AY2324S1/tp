package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.appointment.exceptions.AppointmentNotFoundException;
import seedu.address.model.appointment.exceptions.DuplicateAppointmentException;
import seedu.address.model.person.Person;

/**
 * A list of appointments that enforces uniqueness between its elements.
 * The implementation ensures that there are no duplicate appointments
 * in the list. An appointment is considered unique based on its identity.
 */
public class UniqueAppointmentList implements Iterable<Appointment> {
    private ObservableList<Appointment> internalList = FXCollections.observableArrayList();
    private final ObservableList<Appointment> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent appointment as the given argument.
     */
    public boolean contains(Appointment toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds an appointment to the list.
     * The appointment must not already exist in the list.
     */
    public void add(Appointment toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateAppointmentException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the appointment {@code target} in the list with {@code editedAppointment}.
     * {@code target} must exist in the list.
     * The appointment identity of {@code editedAppointment} must not be the same as
     * another existing appointment in the list.
     */
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireAllNonNull(target, editedAppointment);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new AppointmentNotFoundException();
        }

        if (!target.equals(editedAppointment) && contains(editedAppointment)) {
            throw new DuplicateAppointmentException();
        }

        internalList.set(index, editedAppointment);
    }

    /**
     * Removes the equivalent appointment from the list.
     * The appointment must exist in the list.
     */
    public void remove(Appointment toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new AppointmentNotFoundException();
        }
    }

    /**
     * Removes the appointments from the list associated with the given person.
     */
    public void remove(Person toRemove) {
        requireNonNull(toRemove);
        internalList.removeIf(appointment -> appointment.getPerson().equals(toRemove));
    }

    public void setAppointments(UniqueAppointmentList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code appointments}.
     * {@code appointments} must not contain duplicate appointments.
     */
    public void setAppointments(List<Appointment> appointments) {
        requireAllNonNull(appointments);
        if (!appointmentsAreUnique(appointments)) {
            throw new DuplicateAppointmentException();
        }

        internalList.setAll(appointments);
    }

    /**
     * Replaces the appointments patients {@code target} to {@code editedPerson}
     */
    public void setAppointments(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        for (int i = 0; i < internalList.size(); ++i) {
            if (internalList.get(i).getPerson().equals(target)) {
                Appointment editedAppointment = new Appointment(editedPerson, internalList.get(i).getAppointmentTime(),
                        internalList.get(i).getAppointmentDescription(), internalList.get(i).getPriorityTag());
                internalList.set(i, editedAppointment);
            }
        }
    }

    /**
     * Replaces the current list with a sorted list based on attribute in isAscending order
     */
    public void sort(boolean isAscending, String attribute) {
        if (attribute.equals("time")) {
            sortByDateAndTime(isAscending);
        } else if (attribute.equals("priority")) {
            sortByPriority(isAscending);
        }
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

    /**
     * Returns true if both lists have the same appointments.
     *
     * @param other The object to be compared with.
     * @return true if both lists have the same appointments.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueAppointmentList // instanceof handles nulls
                && internalList.equals(((UniqueAppointmentList) other).internalList));
    }

    /**
     * Returns the hash code value for this list.
     *
     * @return the hash code value for this list.
     */
    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
    * Returns true if {@code appointments} contains only unique appointments.
    */
    private boolean appointmentsAreUnique(List<Appointment> appointments) {
        for (int i = 0; i < appointments.size() - 1; i++) {
            for (int j = i + 1; j < appointments.size(); j++) {
                if (appointments.get(i).equals(appointments.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * Sorts Appointment List by date in order of {@code isAscending}
     * @param isAscending
     */
    private void sortByDateAndTime(boolean isAscending) {
        if (isAscending) {
            internalList.sort(Comparator.naturalOrder());
        } else {
            internalList.sort((appointment1, appointment2) -> -appointment1.compareTo(appointment2));
        }
    }
    /**
     * Sorts Appointment List by priority in order of {@code isAscending}
     * @param isAscending
     */
    public void sortByPriority(boolean isAscending) {
        if (isAscending) {
            internalList.sort(
                    Comparator.comparing(Appointment::getPriorityTag));
        } else {
            internalList.sort((appointment1, appointment2) ->
                    -appointment1.getPriorityTag().compareTo(appointment2.getPriorityTag()));
        }
    }
}
