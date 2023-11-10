package seedu.address.model.person;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ListEntryField;
import seedu.address.model.tag.Tag;

/**
 * Represents a ListEntry's tags in the address book.
 */
public class Tags extends ListEntryField {
    public static final Tags DEFAULT_TAGS = new Tags();
    private Set<Tag> tags;
    public Tags() {
        tags = new HashSet<>();
    }
    public Tags(Set<Tag> tags) {
        this.tags = tags;
    }
    /**
     * Returns a clone of the tags.
     */
    @Override
    public Tags clone() {
        if (this == DEFAULT_TAGS) {
            return DEFAULT_TAGS;
        }
        return new Tags(getTagSetClone());
    }
    /**
     * Constructs a {@code Tags} from input of format "tag1, tag2, tag3".
     */
    public static Tags of(String input) throws ParseException {
        Tags t = new Tags();
        try {
            Arrays.stream(input.split(",")).forEach(str -> t.add(new Tag(str.trim())));
        } catch (IllegalArgumentException e) {
            throw new ParseException(e.getMessage());
        }
        return t;
    }

    /**
     * Adds a tag to the set of tags.
     */
    public void add(Tag tag) throws IllegalArgumentException {
        if (tags.contains(tag)) {
            throw new IllegalArgumentException("Tag already exists");
        }
        tags.add(tag);
    }
    public Set<Tag> getTagSetClone() {
        HashSet<Tag> tagsClone = new HashSet<>();
        for (Tag tag : tags) {
            tagsClone.add(tag.clone());
        }
        return tagsClone;
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Tags)) {
            return false;
        }
        Tags otherTags = (Tags) other;
        return tags.equals(otherTags.tags);
    }
    public boolean containAll(Tags other) {
        return tags.containsAll(other.tags);
    }
}
