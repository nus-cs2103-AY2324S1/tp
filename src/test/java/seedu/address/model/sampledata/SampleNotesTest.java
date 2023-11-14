package seedu.address.model.sampledata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.note.Content;
import seedu.address.model.note.Note;
import seedu.address.model.tag.Tag;

public class SampleNotesTest {
    @Test
    public void getSampleNotes_returnsCorrectNumberOfNotes() {
        Note[] sampleNotes = SampleNotes.getSampleNotes();
        assertEquals(3, sampleNotes.length);
    }

    @Test
    public void getSampleNotes_containsCorrectData() {
        Note[] sampleNotes = SampleNotes.getSampleNotes();

        assertEquals("Dummy note 1", sampleNotes[0].getContent().toString());
        assertTrue(sampleNotes[0].getTags().stream().findFirst().isPresent());
        assertEquals("dummy", sampleNotes[0].getTags().stream().findFirst().get().toString());

        assertEquals("Dummy note 2", sampleNotes[1].getContent().toString());
        assertTrue(sampleNotes[1].getTags().stream().findFirst().isPresent());
        assertEquals("dummy", sampleNotes[1].getTags().stream().findFirst().get().toString());

        assertEquals(
            "Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long "
                + "Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long "
                + "Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long "
                + "Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long "
                + "Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long "
                + "Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long "
                + "Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long "
                + "Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long "
                + "Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long "
                + "Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long "
                + "Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long "
                + "Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long "
                + "Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long "
                + "Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long "
                + "Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long "
                + "Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note",
            sampleNotes[2].getContent().toString());
        assertTrue(sampleNotes[2].getTags().stream().findFirst().isPresent());
        assertEquals("dummy", sampleNotes[2].getTags().stream().findFirst().get().toString());
    }

    @Test
    public void sampleNote_constructorAndGetterMethods() {
        SampleNotes.SampleNote sampleNote = SampleNotes.SampleNote.DUMMY_1;

        Note note = new Note(new Content(sampleNote.getContent()), new Tag(sampleNote.getTag()));

        assertEquals(sampleNote.getContent(), note.getContent().toString());
        assertTrue(note.getTags().stream().findFirst().isPresent());
        assertEquals(sampleNote.getTag(), note.getTags().stream().findFirst().get().toString());
    }


    @Test
    public void sampleNotes_haveValidContents() {
        Note[] sampleNotes = SampleNotes.getSampleNotes();
        for (Note note : sampleNotes) {
            assertTrue(Content.isValidContent(note.getContent().toString()));
        }
    }

    @Test
    public void sampleNotes_haveValidTags() {
        Note[] sampleNotes = SampleNotes.getSampleNotes();
        for (Note note : sampleNotes) {
            for (Tag tag : note.getTags()) {
                assertTrue(Tag.isValidTagName(tag.toString()));
            }
        }
    }
}
