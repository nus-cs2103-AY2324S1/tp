package seedu.address.model.note;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.SeplendidDataType;
import seedu.address.model.tag.Tag;

/**
 * Represents a note in SEPlendid
 */
public class Note implements SeplendidDataType {
    private final Content content;
    private final Set<Tag> tags = new HashSet<>();
    private Integer index;

    /**
     * Constructs an {@code Note}.
     * Every field must be present and not null.
     *
     * @param content Content for the Note
     * @param tag     Tag for the note
     */
    public Note(Content content, Tag tag) {
        requireAllNonNull(content, tag);
        this.content = content;
        this.tags.add(tag);
        this.index = null;
    }

    /**
     * Constructs an {@code Note}.
     * Every field must be present and not null.
     *
     * @param content Content for the Note
     * @param tags    Tags for the note
     */
    public Note(Content content, Set<Tag> tags) {
        requireAllNonNull(content, tags);
        this.content = content;
        this.tags.addAll(tags);
        this.index = null;
    }

    /**
     * Constructs an {@code Note}.
     * Every field must be present and not null.
     *
     * @param content Content for the Note
     * @param tag     Tag for the note
     * @param index   Index for the note
     */
    public Note(Content content, Set<Tag> tag, Integer index) {
        requireAllNonNull(content, tag);
        this.content = content;
        this.tags.addAll(tag);
        this.index = index;
    }

    /**
     * Gets the tags of the note.
     */
    public Set<Tag> getTags() {
        return tags;
    }

    /**
     * Gets the content of the note.
     */
    public Content getContent() {
        return content;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getIndex() {
        return index;
    }

    public Note getCopy() {
        return new Note(this.content, this.tags, this.index);
    }

    /**
     * Returns true if both notes have the same content.
     * This defines a weaker notion of equality between two notes.
     */
    public boolean isSameNote(Note otherNote) {
        if (otherNote == this) {
            return true;
        }

        return this.equals(otherNote);
    }

    /**
     * Returns true if both notes have the same identity and data fields.
     * This defines a stronger notion of equality between two notes.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Note)) {
            return false;
        }

        Note otherNote = (Note) other;
        if (index == null) {
            return content.equals(otherNote.content);
        }
        return index.equals(otherNote.index) || content.equals(otherNote.content);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(content, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("content", content)
                .add("tags", tags)
                .toString();
    }
}
