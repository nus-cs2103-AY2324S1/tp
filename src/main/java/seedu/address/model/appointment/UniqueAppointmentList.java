package seedu.address.model.appointment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A list of appointments that enforces uniqueness between its elements.
 * <p>
 * The implementation ensures that there are no duplicate appointments
 * in the list. An appointment is considered unique based on its identity.
 * </p>
 */
public class UniqueAppointmentList {
    private ObservableList<Appointment> internalList = FXCollections.observableArrayList();
    private final ObservableList<Appointment> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

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
}
