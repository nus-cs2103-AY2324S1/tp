package seedu.address.model.note;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.NoteBuilder;

public class NoteTest {

    @Test
    public void isSameNote_sameContentAndIndex_returnsTrue() {
        // Create two notes with the same content and index using the NoteBuilder
        Note note1 = new NoteBuilder()
                .withContent("Content A")
                .withIndex(1)
                .build();

        Note note2 = new NoteBuilder()
                .withContent("Content A")
                .withIndex(1)
                .build();

        assertTrue(note1.isSameNote(note2));
    }

    @Test
    public void isSameNote_differentContentSameIndex_returnsTrue() {
        // Create two notes with different content but the same index using the NoteBuilder
        Note note1 = new NoteBuilder()
                .withContent("Content A")
                .withIndex(1)
                .build();

        Note note2 = new NoteBuilder()
                .withContent("Content B")
                .withIndex(1)
                .build();

        assertTrue(note1.isSameNote(note2));
    }

    @Test
    public void equals_equalNotes_returnsTrue() {
        // Create two notes with the same content, tags, and index using the NoteBuilder
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("Tag1"));

        Note note1 = new NoteBuilder()
                .withContent("Content A")
                .withTags(tags)
                .withIndex(1)
                .build();

        Note note2 = new NoteBuilder()
                .withContent("Content A")
                .withTags(tags)
                .withIndex(1)
                .build();

        assertTrue(note1.equals(note2));
    }

    @Test
    public void equals_samContentdifferentIndex_returnsTrue() {
        // Create two notes with the same content and tags but different indices using the NoteBuilder
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("Tag1"));

        Note note1 = new NoteBuilder()
                .withContent("Content A")
                .withTags(tags)
                .withIndex(1)
                .build();

        Note note2 = new NoteBuilder()
                .withContent("Content A")
                .withTags(tags)
                .withIndex(2)
                .build();

        assertTrue(note1.equals(note2));
    }

    @Test
    public void equals_nullObject_returnsFalse() {
        // Create a note and compare it to a null object
        Note note = new NoteBuilder()
                .withContent("Content A")
                .withIndex(1)
                .build();

        assertFalse(note.equals(null));
    }

    @Test
    public void hashCode_equalNotes_returnsTrue() {
        // Create two notes with the same content, tags, and index using the NoteBuilder
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("Tag1"));

        Note note1 = new NoteBuilder()
                .withContent("Content A")
                .withTags(tags)
                .withIndex(1)
                .build();

        Note note2 = new NoteBuilder()
                .withContent("Content A")
                .withTags(tags)
                .withIndex(1)
                .build();

        assertEquals(note1.hashCode(), note2.hashCode());
    }

    @Test
    public void hashCode_differentNotes_returnsFalse() {
        // Create two notes with different content using the NoteBuilder
        Note note1 = new NoteBuilder()
                .withContent("Content A")
                .withIndex(1)
                .build();

        Note note2 = new NoteBuilder()
                .withContent("Content B")
                .withIndex(1)
                .build();

        assertNotEquals(note1.hashCode(), note2.hashCode());
    }
}
