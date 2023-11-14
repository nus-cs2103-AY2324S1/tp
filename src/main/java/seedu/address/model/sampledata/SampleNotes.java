package seedu.address.model.sampledata;

import java.util.Arrays;

import seedu.address.model.note.Content;
import seedu.address.model.note.Note;
import seedu.address.model.tag.Tag;

/**
 * A utility class that provides sample notes for seeding the application.
 */
public class SampleNotes {
    /**
     * The `SampleNote` enum represents sample note tags.
     */
    public enum SampleNote {
        DUMMY_1("Dummy note 1", "dummy"),
        DUMMY_2("Dummy note 2", "dummy"),
        DUMMY_LONG_NOTE(
            "Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note "
                + "Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy "
                + "Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long "
                + "Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note "
                + "Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy "
                + "Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long "
                + "Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note "
                + "Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy "
                + "Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long "
                + "Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note "
                + "Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy "
                + "Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long "
                + "Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note "
                + "Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy "
                + "Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long "
                + "Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note "
                + "Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note Long Dummy Note",
            "dummy");


        private final String content;

        private final String tag;

        /**
         * Constructs a `SampleNote` enum with the given formatted value.
         *
         * @param content The formatted string representation of the note tag.
         */
        SampleNote(String content, String tag) {
            this.content = content;
            this.tag = tag;
        }

        /**
         * Gets the formatted string representation of the `SampleNoteContent`.
         *
         * @return The formatted string representation of the `SampleNoteContent`.
         */
        public String getContent() {
            return this.content;
        }

        /**
         * Gets the formatted string representation of the `SampleNoteTag`.
         *
         * @return The formatted string representation of the `SampleNoteTag`.
         */
        public String getTag() {
            return this.tag;
        }
    }

    /**
     * Returns an array of sample notes.
     *
     * @return An array of sample {@code Note} objects.
     */
    public static Note[] getSampleNotes() {
        return Arrays.stream(SampleNote.values())
                     .map(data -> new Note(
                         new Content(data.getContent()),
                         new Tag(data.getTag())))
                     .toArray(Note[]::new);
    }
}
