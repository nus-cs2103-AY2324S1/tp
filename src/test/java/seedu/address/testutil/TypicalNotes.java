package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.note.Note;

/**
 * A utility class containing a list of {@code Note} objects to be used in tests.
 */
public class TypicalNotes {
    public static final Note NOTE_A = new NoteBuilder().withTitle("Preferred Qualifications")
            .withContent("Machine Learning Frameworks").build();
    public static final Note NOTE_B = new NoteBuilder().withTitle("Preferred Major")
            .withContent("Computer Science").build();

    private TypicalNotes() {} // prevents instantiation

    public static List<Note> getTypicalNotes() {
        return new ArrayList<>(Arrays.asList(NOTE_A, NOTE_B));
    }
}
