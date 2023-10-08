package seedu.lovebook.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.lovebook.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.lovebook.model.person.exceptions.DuplicatePersonException;
import seedu.lovebook.model.person.exceptions.PersonNotFoundException;

/**
 * A list of dates that enforces uniqueness between its elements and does not allow nulls.
 * A date is considered unique by comparing using {@code Date#isSamePerson(Date)}. As such, adding and updating of
 * dates uses Date#isSamePerson(Date) for equality so as to ensure that the date being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a date uses Date#equals(Object) so
 * as to ensure that the date with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Date#isSamePerson(Date)
 */
public class UniquePersonList implements Iterable<Date> {

    private final ObservableList<Date> internalList = FXCollections.observableArrayList();
    private final ObservableList<Date> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent date as the given argument.
     */
    public boolean contains(Date toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerson);
    }

    /**
     * Adds a date to the list.
     * The date must not already exist in the list.
     */
    public void add(Date toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the date {@code target} in the list with {@code editedDate}.
     * {@code target} must exist in the list.
     * The date identity of {@code editedDate} must not be the same as another existing date in the list.
     */
    public void setPerson(Date target, Date editedDate) {
        requireAllNonNull(target, editedDate);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSamePerson(editedDate) && contains(editedDate)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedDate);
    }

    /**
     * Removes the equivalent date from the list.
     * The date must exist in the list.
     */
    public void remove(Date toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setPersons(UniquePersonList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code dates}.
     * {@code dates} must not contain duplicate dates.
     */
    public void setPersons(List<Date> dates) {
        requireAllNonNull(dates);
        if (!datesAreUnique(dates)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(dates);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Date> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Date> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniquePersonList)) {
            return false;
        }

        UniquePersonList otherUniquePersonList = (UniquePersonList) other;
        return internalList.equals(otherUniquePersonList.internalList);
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
     * Returns true if {@code dates} contains only unique dates.
     */
    private boolean datesAreUnique(List<Date> dates) {
        for (int i = 0; i < dates.size() - 1; i++) {
            for (int j = i + 1; j < dates.size(); j++) {
                if (dates.get(i).isSamePerson(dates.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
