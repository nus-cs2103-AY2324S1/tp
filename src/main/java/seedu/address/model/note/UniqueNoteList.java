package seedu.address.model.note;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.note.exceptions.DuplicateNoteException;
import seedu.address.model.note.exceptions.NoteNotFoundException;


/**
 * A list of notes that enforces uniqueness between its elements and does not allow nulls.
 * A note is considered unique by comparing using {@code Note#isSameNote(Note)}. As such, adding and updating of
 * note uses Note#isSameNote(Note) for equality to ensure that the note being added or updated is
 * unique in terms of identity in the UniqueNoteList. However, the removal of a note uses Note#equals(Object)
 * to ensure that the note with exactly the same fields will be removed.
 * Supports a minimal set of list operations.
 *
 * @see Note#isSameNote(Note)
 */
public class UniqueNoteList implements Iterable<Note> {
    private final ObservableList<Note> internalList = FXCollections.observableArrayList();
    private final ObservableList<Note> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent note as the given argument.
     */
    public boolean contains(Note toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameNote);
    }

    /**
     * Adds a note to the list.
     * The note must not already exist in the list.
     */
    public void add(Note toAdd) {
        requireNonNull(toAdd);
        if (toAdd.getIndex() != null && contains(toAdd)) {
            throw new DuplicateNoteException();
        }
        toAdd.setIndex(internalList.size() + 1);
        internalList.add(toAdd);
    }

    /**
     * Replaces the note {@code target} in the list with {@code editedNote}.
     * {@code target} must exist in the list.
     * The note identity of {@code editedNote} must not be the same as another existing note in the list.
     */
    public void setNote(Note target, Note editedNote) {
        requireAllNonNull(target, editedNote);

        int index = internalList.indexOf(target);
        internalList.set(index, editedNote);
    }

    /**
     * Removes the equivalent note from the list.
     * The note must exist in the list.
     */
    public Note remove(Integer toRemove) {
        requireNonNull(toRemove);

        for (int i = toRemove; i < internalList.size(); i++) {
            internalList.get(i).setIndex(i);
        }

        Note deletedNote;
        try {
            deletedNote = internalList.remove(toRemove - 1);
        } catch (Exception e) {
            throw new NoteNotFoundException();
        }

        return deletedNote;
    }

    /**
     * Replaces all the notes in the list.
     * @param replacement The new list of notes.
     */
    public void setNotes(UniqueNoteList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code notes}.
     * {@code notes} must not contain duplicate notes.
     */
    public void setNotes(List<Note> notes) {
        requireAllNonNull(notes);
        if (!notesAreUnique(notes)) {
            throw new DuplicateNoteException();
        }

        internalList.setAll(notes);
    }

    /**
     * Returns true if {@code notes} contains only unique notes.
     */
    private boolean notesAreUnique(List<Note> notes) {
        for (int i = 0; i < notes.size() - 1; i++) {
            for (int j = i + 1; j < notes.size(); j++) {
                if (notes.get(i).isSameNote(notes.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Note> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Note> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueNoteList)) {
            return false;
        }

        UniqueNoteList otherUniqueNoteList = (UniqueNoteList) other;
        return internalList.equals(otherUniqueNoteList.internalList);
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
