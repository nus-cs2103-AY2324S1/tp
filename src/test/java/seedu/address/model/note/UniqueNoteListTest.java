package seedu.address.model.note;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.note.exceptions.DuplicateNoteException;
import seedu.address.model.note.exceptions.NoteNotFoundException;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.NoteBuilder;

public class UniqueNoteListTest {

    @Test
    public void contains_nullNote_throwsNullPointerException() {
        UniqueNoteList uniqueNoteList = new UniqueNoteList();
        assertThrows(NullPointerException.class, () -> uniqueNoteList.contains(null));
    }

    @Test
    public void contains_noteNotInList_returnsFalse() {
        UniqueNoteList uniqueNoteList = new UniqueNoteList();
        Note note = new NoteBuilder().withContent("Content A").withTag(new Tag("Tag1")).build();
        assertFalse(uniqueNoteList.contains(note));
    }

    @Test
    public void contains_noteInList_returnsTrue() {
        UniqueNoteList uniqueNoteList = new UniqueNoteList();
        Note note = new NoteBuilder().withContent("Content A").withTag(new Tag("Tag1")).build();
        uniqueNoteList.add(note);
        assertTrue(uniqueNoteList.contains(note));
    }

    @Test
    public void add_nullNote_throwsNullPointerException() {
        UniqueNoteList uniqueNoteList = new UniqueNoteList();
        assertThrows(NullPointerException.class, () -> uniqueNoteList.add(null));
    }

    @Test
    public void add_duplicateNote_throwsDuplicateNoteException() {
        UniqueNoteList uniqueNoteList = new UniqueNoteList();
        Note note = new NoteBuilder().withContent("Content A").withTag(new Tag("Tag1")).withIndex(1).build();
        uniqueNoteList.add(note);

        Note duplicateNote = new NoteBuilder().withContent("Content A").withTag(new Tag("Tag1")).withIndex(1).build();
        assertThrows(DuplicateNoteException.class, () -> uniqueNoteList.add(duplicateNote));
    }

    @Test
    public void setNote_nullTarget_throwsNullPointerException() {
        UniqueNoteList uniqueNoteList = new UniqueNoteList();
        Note editedNote = new NoteBuilder().withContent("Edited Content").withTag(new Tag("EditedTag")).build();
        assertThrows(NullPointerException.class, () -> uniqueNoteList.setNote(null, editedNote));
    }

    @Test
    public void setNote_nullEditedNote_throwsNullPointerException() {
        UniqueNoteList uniqueNoteList = new UniqueNoteList();
        Note targetNote = new NoteBuilder().withContent("Content A").withTag(new Tag("Tag1")).build();
        assertThrows(NullPointerException.class, () -> uniqueNoteList.setNote(targetNote, null));
    }

    @Test
    public void remove_nullIndex_throwsNullPointerException() {
        UniqueNoteList uniqueNoteList = new UniqueNoteList();
        assertThrows(NullPointerException.class, () -> uniqueNoteList.remove(null));
    }

    @Test
    public void remove_nonExistentIndex_throwsNoteNotFoundException() {
        UniqueNoteList uniqueNoteList = new UniqueNoteList();
        assertThrows(NoteNotFoundException.class, () -> uniqueNoteList.remove(1));
    }

    @Test
    public void remove_existingIndex_removesNote() {
        UniqueNoteList uniqueNoteList = new UniqueNoteList();
        Note note = new NoteBuilder().withContent("Content A").withTag(new Tag("Tag1")).build();
        uniqueNoteList.add(note);

        Note removedNote = uniqueNoteList.remove(1);
        assertEquals(note, removedNote);
        assertFalse(uniqueNoteList.contains(note));
    }

    @Test
    public void setNotes_nullReplacement_throwsNullPointerException() {
        UniqueNoteList uniqueNoteList = new UniqueNoteList();
        assertThrows(NullPointerException.class, () -> uniqueNoteList.setNotes((UniqueNoteList) null));
    }

    @Test
    public void setNotes_replacementSetsUniqueNotes() {
        UniqueNoteList uniqueNoteList = new UniqueNoteList();
        List<Note> notes = new ArrayList<>();
        notes.add(new NoteBuilder().withContent("Content A").withTag(new Tag("Tag1")).withIndex(1).build());
        notes.add(new NoteBuilder().withContent("Content B").withTag(new Tag("Tag2")).withIndex(2).build());
        UniqueNoteList replacement = new UniqueNoteList();
        replacement.setNotes(notes);

        uniqueNoteList.setNotes(replacement);

        assertEquals(notes, uniqueNoteList.asUnmodifiableObservableList());
    }

    @Test
    public void setNotes_nullNotesList_throwsNullPointerException() {
        UniqueNoteList uniqueNoteList = new UniqueNoteList();
        assertThrows(NullPointerException.class, () -> uniqueNoteList.setNotes((List<Note>) null));
    }

    @Test
    public void setNotes_duplicateNotesList_throwsDuplicateNoteException() {
        UniqueNoteList uniqueNoteList = new UniqueNoteList();
        List<Note> notes = new ArrayList<>();
        notes.add(new NoteBuilder().withContent("Content A").withTag(new Tag("Tag1")).withIndex(1).build());
        notes.add(new NoteBuilder().withContent("Content A").withTag(new Tag("Tag1")).withIndex(1).build());
        assertThrows(DuplicateNoteException.class, () -> uniqueNoteList.setNotes(notes));
    }
}
