package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.note.Note;
import seedu.address.model.note.UniqueNoteList;

/**
 * Wraps note data at the catalogue level
 * Duplicates are not allowed (by .isSameNote comparison)
 */
public class NoteCatalogue implements ReadOnlyNoteCatalogue {
    private final UniqueNoteList notes;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     * <p>
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     * among constructors.
     */

    {
        notes = new UniqueNoteList();
    }

    public NoteCatalogue() {
    }

    /**
     * Creates an NoteCatalogue using the Notes in the {@code toBeCopied}
     */
    public NoteCatalogue(ReadOnlyNoteCatalogue toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the Note list with {@code notes}.
     * {@code notes} must not contain duplicate Note.
     */
    public void setNotes(List<Note> notes) {
        this.notes.setNotes(notes);
    }

    /**
     * Resets the existing data of this {@code NoteCatalogue} with {@code newData}.
     */
    public void resetData(ReadOnlyNoteCatalogue newData) {
        requireNonNull(newData);
        setNotes(newData.getNoteList());
    }

    //// note-level operations

    /**
     * Returns true if a Note with the same identity as {@code noteQuery} exists in the catalogue.
     */
    public boolean hasNote(Note noteQuery) {
        requireNonNull(noteQuery);
        return notes.contains(noteQuery);
    }

    /**
     * Adds a Note to the NoteCatalogue.
     * The Note must not already exist in the note list.
     */
    public void addNote(Note note) {
        notes.add(note);
    }

    /**
     * Replaces the given LocalCourse {@code target} in the list with {@code editedLocalCourse}.
     * {@code target} must exist in the LocalCourseCatalogue.
     * The LocalCourse identity of {@code editedLocalCourse} must not be the same as another existing LocalCourse.
     */
    public void setNote(Note target, Note editedNote) {
        requireNonNull(editedNote);

        notes.setNote(target, editedNote);
    }

    /**
     * Removes {@code toRemove} from this {@code NoteCatalogue}.
     * {@code toRemove} must exist in the note catalogue.
     */
    public Note removeNote(int toRemove) {
        return notes.remove(toRemove);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("notes", notes)
                .toString();
    }

    @Override
    public ObservableList<Note> getNoteList() {
        return notes.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NoteCatalogue)) {
            return false;
        }

        NoteCatalogue otherNoteCatalogue = (NoteCatalogue) other;
        return notes.equals(otherNoteCatalogue.notes);
    }

    @Override
    public int hashCode() {
        return notes.hashCode();
    }
}
