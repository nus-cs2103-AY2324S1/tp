package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NotesTest {

    private Person person;

    @BeforeEach
    public void setUp() {
        Name name = new Name("John Doe");
        Phone phone = new Phone("12345678");
        Email email = new Email("john.doe@example.com");
        Address address = new Address("123 Main St");
        person = new Person(name, phone, email, address, Collections.emptySet());
    }

    @Test
    public void addNote_addSingleNote_noteAdded() {
        Note note = new Note("Sample Note");
        person.addNote(note);
        assertEquals(1, person.getNotes().size());
        assertEquals(note, person.getNotes().get(0));
    }

    @Test
    public void removeNote_removeValidIndex_noteRemoved() {
        Note note1 = new Note("Sample Note 1");
        Note note2 = new Note("Sample Note 2");
        person.addNote(note1);
        person.addNote(note2);

        person.removeNote(0);
        assertEquals(1, person.getNotes().size());
        assertEquals(note2, person.getNotes().get(0));
    }

    @Test
    public void removeNote_removeInvalidIndex_throwsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> person.removeNote(0));
    }

    @Test
    public void getNotes_verifyListOfNotes_returnCorrectList() {
        Note note1 = new Note("Sample Note 1");
        Note note2 = new Note("Sample Note 2");
        person.addNote(note1);
        person.addNote(note2);

        assertEquals(2, person.getNotes().size());
        assertEquals(note1, person.getNotes().get(0));
        assertEquals(note2, person.getNotes().get(1));
    }
}
