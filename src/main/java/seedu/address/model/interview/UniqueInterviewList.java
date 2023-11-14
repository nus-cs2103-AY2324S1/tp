package seedu.address.model.interview;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.interview.exceptions.DuplicateInterviewException;
import seedu.address.model.interview.exceptions.InterviewClashException;
import seedu.address.model.interview.exceptions.InterviewNotFoundException;

/**
 * A list of interviews that enforces uniqueness between its elements and does not allow nulls.
 * A interview is considered unique by comparing using {@code Interview#isNotValidOrNewInterview(Interview)}.
 * As such, adding and updating of Interviews uses Interview#isNotValidOrNewInterview(Interview) for equality
 * so as to ensure that the interview being added or updated is unique in terms of identity in the UniqueInterviewList.
 * However, the removal of an interview uses Interview#equals(Object) so as to ensure that the interview
 * with exactly the same fields will be removed.
 * Supports a minimal set of list operations.
 * Adapted from AB3's UniquePersonList
 */
public class UniqueInterviewList implements Iterable<Interview> {

    private final ObservableList<Interview> internalList = FXCollections.observableArrayList();
    private final ObservableList<Interview> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent interview as the given argument.
     */
    public boolean contains(Interview toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameInterview);
    }

    /**
     * Returns true if any interview in the list clashes with the interview in the given argument.
     */
    public boolean anyClash(Interview toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isClashingWith);
    }

    /**
     * Adds a interview to the list.
     * The interview must not already exist in the list.
     */
    public void add(Interview toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateInterviewException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the interview {@code target} in the list with {@code editedInterview}.
     * {@code target} must exist in the list.
     * The interview identity of {@code editedInterview} must not be the same as another existing interview in the list.
     */
    public void setInterview(Interview target, Interview editedInterview) {
        requireAllNonNull(target, editedInterview);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new InterviewNotFoundException();
        }

        if (!target.isSameInterview(editedInterview) && contains(editedInterview)) {
            throw new DuplicateInterviewException();
        }

        internalList.set(index, editedInterview);
    }

    /**
     * Removes the equivalent interview from the list.
     * The interview must exist in the list.
     */
    public void remove(Interview toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new InterviewNotFoundException();
        }
    }

    public void setInterviews(UniqueInterviewList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code interviews}.
     * {@code interviews} must not contain duplicate interviews.
     */
    public void setInterviews(List<Interview> interviews) {
        requireAllNonNull(interviews);
        if (!interviewsAreUnique(interviews)) {
            throw new DuplicateInterviewException();
        }

        if (!interviewsDoNotClash(interviews)) {
            throw new InterviewClashException();
        }

        internalList.setAll(interviews);
    }

    /**
     * Sorts the interview list of the address book.
     * The interviews list will be sorted based on the comparator
     */
    public void sort(Comparator<Interview> comparator) {
        FXCollections.sort(internalList, comparator);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Interview> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Interview> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueInterviewList)) {
            return false;
        }

        UniqueInterviewList otherUniqueInterviewList = (UniqueInterviewList) other;
        return internalList.equals(otherUniqueInterviewList.internalList);
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
     * Returns true if {@code interviews} contains only unique interviews.
     */
    private boolean interviewsAreUnique(List<Interview> interviews) {
        for (int i = 0; i < interviews.size() - 1; i++) {
            for (int j = i + 1; j < interviews.size(); j++) {
                if (interviews.get(i).isSameInterview(interviews.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns true if {@code interviews} contains only interviews that do not clash.
     */
    private boolean interviewsDoNotClash(List<Interview> interviews) {
        for (int i = 0; i < interviews.size() - 1; i++) {
            for (int j = i + 1; j < interviews.size(); j++) {
                if (interviews.get(i).isClashingWith(interviews.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
