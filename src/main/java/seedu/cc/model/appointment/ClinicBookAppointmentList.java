package seedu.cc.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.cc.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.cc.model.appointment.exceptions.AppointmentNotFoundException;
import seedu.cc.model.appointment.exceptions.DuplicateAppointmentException;
import seedu.cc.model.patient.Patient;


/**
 * A list of appointment events that enforces uniqueness between its elements and does not allow nulls.
 * A appointment event is considered unique by comparing using {@code AppointmentEvent#equals(Object)}.
 */
public class ClinicBookAppointmentList implements Iterable<AppointmentEvent> {

    private final ObservableList<AppointmentEvent> internalList = FXCollections.observableArrayList();
    private final ObservableList<AppointmentEvent> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    private Patient currentPatient = null;

    /**
     * Returns true if the list contains an equivalent appointment event as the given argument.
     */
    public boolean contains(AppointmentEvent toCheck) {
        return internalList.contains(toCheck);
    }

    /**
     * Adds a appointment event to the list.
     * The event must not already exist in the list.
     */
    public void add(AppointmentEvent toAdd, Patient patient) {

        if (!patient.equals(currentPatient)) {
            return;
        }

        internalList.add(toAdd);
    }

    /**
     * Replaces the given appointment event {@code target} in the list with {@code editedEvent}.
     * {@code target} must exist in the list.
     * The appointment event must not be the same as another existing event in the list.
     */
    public void setAppointment(AppointmentEvent target, AppointmentEvent editedEvent, Patient patient) {
        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new AppointmentNotFoundException();
        }

        if (!target.equals(editedEvent) && contains(editedEvent)) {
            throw new DuplicateAppointmentException();
        }

        if (!patient.equals(currentPatient)) {
            return;
        }

        internalList.set(index, editedEvent);
    }

    /**
     * Deletes the given appointment event.
     * The event must exist in the list.
     */
    public void delete(AppointmentEvent toDelete, Patient patient) {
        requireAllNonNull(toDelete, patient);

        if (!patient.equals(currentPatient)) {
            return;
        }

        if (!internalList.remove(toDelete)) {
            throw new AppointmentNotFoundException();
        }
    }

    /**
     * Lists all appointment events associated with a specific patient.
     *
     * @param patient The patient for whom to list the appointment events.
     */
    public void listAppointments(Patient patient) {
        requireNonNull(patient);
        currentPatient = patient;
        internalList.setAll(patient.getClinicBookAppointmentList());
    }

    /**
     * Returns a list of all appointment events in this list.
     */
    public List<AppointmentEvent> getAllAppointments() {
        return new ArrayList<>(internalList);
    }

    public ObservableList<AppointmentEvent> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<AppointmentEvent> iterator() {
        return internalList.iterator();
    }

}
