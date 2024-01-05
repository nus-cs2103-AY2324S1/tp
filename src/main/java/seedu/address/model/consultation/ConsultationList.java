package seedu.address.model.consultation;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.consultation.exceptions.ConsultationNotFoundException;
import seedu.address.model.consultation.exceptions.DuplicateConsultationException;
import seedu.address.model.person.Person;

/**
 * A list of Consultations.
 */
public class ConsultationList implements Iterable<Consultation> {
    private ObservableList<Consultation> internalList = FXCollections.observableArrayList();
    private final ObservableList<Consultation> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    public void setConsultationList(List<Consultation> consultationList) {
        requireAllNonNull(consultationList);
        internalList.setAll(consultationList);
    }

    /**
     * Returns true if the list contains an equivalent consultation as the given argument.
     */
    public boolean contains(Consultation toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameConsultation);
    }

    /**
     * Adds a consultation to the list.
     */
    public void addConsultation(Consultation toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateConsultationException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent consultation from the list.
     * The consultation must exist in the list.
     */
    public void remove(Consultation toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ConsultationNotFoundException();
        }
    }

    /**
     * Removes the {@code student} from all consultations in the consultation list.
     */
    public void removePerson(Person student) {
        requireNonNull(student);
        List<Consultation> newConsultations = new ArrayList<>();

        for (Consultation consultation : internalList) {
            Consultation updatedConsultation = consultation.removeStudent(student);
            if (!updatedConsultation.isEmpty()) {
                newConsultations.add(updatedConsultation);
            }
        }
        internalList.setAll(newConsultations);
    }

    /**
     * Replaces the student {@code target} in the list with {@code editedStudent}.
     */
    public void setStudent(Person target, Person editedStudent) {
        requireAllNonNull(target, editedStudent);
        List<Consultation> newConsultations = new ArrayList<>();

        for (Consultation consultation : internalList) {
            if (consultation.contains(target)) {
                consultation.replaceStudent(target, editedStudent);
            }
            newConsultations.add(consultation);
        }
        internalList.setAll(newConsultations);
    }

    /**
     * Replaces the consultation {@code target} in the list with {@code updatedConsultation}.
     * {@code target} must exist in the list.
     * The consultation identity of {@code updatedConsultation} must not be the same as another existing consultation
     * in the list.
     */
    public void setConsultation(Consultation target, Consultation updatedConsultation) {
        requireAllNonNull(target, updatedConsultation);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ConsultationNotFoundException();
        }

        if (!target.isSameConsultation(updatedConsultation) && contains(updatedConsultation)) {
            throw new DuplicateConsultationException();
        }

        internalList.set(index, updatedConsultation);
    }

    public ObservableList<Consultation> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Consultation> iterator() {
        return internalList.iterator();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true; // Same object, equal
        }

        if (!(other instanceof ConsultationList)) {
            return false; // Not the same class, not equal
        }

        ConsultationList otherList = (ConsultationList) other;

        // Check if the lists have the same sessions in the same order
        return internalList.equals(otherList.internalList);
    }
}
