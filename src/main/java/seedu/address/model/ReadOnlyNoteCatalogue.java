package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.note.Note;

/**
 * Unmodifiable view of note catalogue.
 */
public interface ReadOnlyNoteCatalogue {

    /**
     * Returns an unmodifiable view of the note list.
     * This list will not contain any duplicate note.
     * ObservableList will aid interoperability.
     */
    ObservableList<Note> getNoteList();
}
