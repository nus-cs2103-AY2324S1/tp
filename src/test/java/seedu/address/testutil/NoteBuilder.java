package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.note.Content;
import seedu.address.model.note.Note;
import seedu.address.model.tag.Tag;

/**
 * A builder class for creating instances of the Note class.
 */
public class NoteBuilder {
    private Content content;
    private Set<Tag> tags = new HashSet<>();
    private Integer index;

    /**
     * Sets the content for the Note being built.
     *
     * @param content The content of the Note.
     * @return This NoteBuilder for method chaining.
     */
    public NoteBuilder withContent(String content) {
        this.content = new Content(content);
        return this;
    }

    /**
     * Adds a tag to the Note being built.
     *
     * @param tag The tag to add to the Note.
     * @return This NoteBuilder for method chaining.
     */
    public NoteBuilder withTag(Tag tag) {
        this.tags.add(tag);
        return this;
    }

    /**
     * Adds a set of tags to the Note being built.
     *
     * @param tags The set of tags to add to the Note.
     * @return This NoteBuilder for method chaining.
     */
    public NoteBuilder withTags(Set<Tag> tags) {
        this.tags.addAll(tags);
        return this;
    }

    /**
     * Sets the index for the Note being built.
     *
     * @param index The index of the Note.
     * @return This NoteBuilder for method chaining.
     */
    public NoteBuilder withIndex(Integer index) {
        this.index = index;
        return this;
    }

    /**
     * Builds and returns the Note instance with the specified attributes.
     *
     * @return The Note instance with the specified content, tags, and index.
     */
    public Note build() {
        Note note;
        if (index != null) {
            note = new Note(content, tags, index);
        } else {
            note = new Note(content, tags);
        }
        return note;
    }
}
