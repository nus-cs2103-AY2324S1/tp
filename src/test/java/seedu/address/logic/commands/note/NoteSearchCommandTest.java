package seedu.address.logic.commands.note;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.transformation.FilteredList;
import seedu.address.logic.commands.SeplendidModelStub;
import seedu.address.model.NoteCatalogue;
import seedu.address.model.note.Note;
import seedu.address.model.note.NoteTagContainsKeywordsPredicate;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.NoteBuilder;

public class NoteSearchCommandTest {

    private ModelStubWithNotes modelStub;
    private NoteSearchCommand noteSearchCommand;

    @BeforeEach
    public void setUp() {
        List<Note> notes = new ArrayList<>();
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("tag"));

        notes.add(new NoteBuilder().withContent("Note 1").withTags(tags).build());
        notes.add(new NoteBuilder().withContent("Note 2").withTags(tags).build());

        modelStub = new ModelStubWithNotes(notes);
        noteSearchCommand = new NoteSearchCommand(new NoteTagContainsKeywordsPredicate("tag"));
    }

    @Test
    public void equals() {
        NoteTagContainsKeywordsPredicate firstPredicate = new NoteTagContainsKeywordsPredicate("tag");
        NoteTagContainsKeywordsPredicate secondPredicate = new NoteTagContainsKeywordsPredicate("differentTag");

        NoteSearchCommand searchCommandWithFirstPredicate = new NoteSearchCommand(firstPredicate);
        NoteSearchCommand searchCommandWithSecondPredicate = new NoteSearchCommand(secondPredicate);

        // same object -> returns true
        assertTrue(searchCommandWithFirstPredicate.equals(searchCommandWithFirstPredicate));

        // same values -> returns true
        NoteSearchCommand searchCommandWithFirstPredicateCopy = new NoteSearchCommand(firstPredicate);
        assertTrue(searchCommandWithFirstPredicate.equals(searchCommandWithFirstPredicateCopy));

        // different types -> returns false
        assertFalse(searchCommandWithFirstPredicate.equals(1));

        // null -> returns false
        assertFalse(searchCommandWithFirstPredicate.equals(null));

        // different note -> returns false
        assertFalse(searchCommandWithFirstPredicate.equals(searchCommandWithSecondPredicate));
    }

    private class ModelStubWithNotes extends SeplendidModelStub {
        private final NoteCatalogue noteCatalogue = new NoteCatalogue();
        private FilteredList<Note> notes;

        ModelStubWithNotes(List<Note> notes) {
            requireNonNull(notes);
            this.notes = new FilteredList<>(this.noteCatalogue.getNoteList());
            noteCatalogue.setNotes(this.notes);
        }

        @Override
        public void getSearchNoteIfExists(NoteTagContainsKeywordsPredicate notePredicate) {
            requireNonNull(notePredicate);
            updateFilteredNoteList(notePredicate); // Update the filtered notes
        }

        @Override
        public NoteCatalogue getNoteCatalogue() {
            return noteCatalogue;
        }
    }

}
