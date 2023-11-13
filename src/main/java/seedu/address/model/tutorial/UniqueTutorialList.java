package seedu.address.model.tutorial;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.tutorial.exceptions.DuplicateTutorialException;
import seedu.address.model.tutorial.exceptions.TutorialNotFoundException;

/**
 * A list of tutorials that enforces uniqueness between its elements and does not allow nulls.
 * Supports a minimal set of list operations.
 */
public class UniqueTutorialList implements Iterable<Tutorial> {

    private ObservableList<Tutorial> internalList = FXCollections.observableArrayList();

    private final ObservableList<Tutorial> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent tutorial as the given argument.
     */
    public boolean contains(Tutorial toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a tutorial to the list.
     * The tutorial must not already exist in the list.
     */
    public void add(Tutorial toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTutorialException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent tutorial from the list.
     * The tutorial must exist in the list.
     */
    public void remove(Tutorial toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TutorialNotFoundException();
        }
    }

    /**
     * Replaces the contents of this list with {@code tutorials}.
     * {@code tutorials} must not contain duplicate tutorials.
     */
    public void setTutorials(List<Tutorial> tutorials) {
        requireAllNonNull(tutorials);

        internalList.setAll(tutorials);
    }

    public ObservableList<Tutorial> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Tutorial> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueTutorialList)) {
            return false;
        }

        UniqueTutorialList otherUniqueTutorialList = (UniqueTutorialList) other;
        return internalList.equals(otherUniqueTutorialList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }
}
