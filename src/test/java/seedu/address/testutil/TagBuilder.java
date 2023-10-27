package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building Tag objects.
 */
public class TagBuilder {
    public static final String DEFAULT_TAG = "Frontend";

    private String tag;

    /**
     * Creates a {@code TagBuilder} with the default details.
     */
    public TagBuilder() {
        this.tag = DEFAULT_TAG;
    }

    /**
     * Initializes the TagBuilder with the data of {@code tagToCopy}.
     */
    public TagBuilder(Tag tagToCopy) {
        this.tag = tagToCopy.tagName;
    }

    /**
     * Sets the tag that we are building.
     */
    public TagBuilder withTag(String tag) {
        this.tag = tag;
        return this;
    }

    /**
     * Returns the built {@code Tag} in a {@code Set}.
     */
    public Set<Tag> inSet() {
        final Set<Tag> tagSet = new HashSet<>();
        tagSet.add(this.build());
        return tagSet;
    }

    /**
     * Returns the built {@code Tag}.
     */
    public Tag build() {
        return new Tag(this.tag);
    }
}
