package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * A utility class for providing typical Tag objects and sets of tags for testing.
 * This class contains constants for typical tag strings and predefined Tag objects
 * and sets of tags used in testing scenarios.
 */
public class TypicalTags {
    public static final String TEST_TAG_STRING = "owesMoney";
    public static final String NO_MATCHING_TAG_STRING = "NoMatchingTag";
    public static final Tag TEST_TAG = new Tag(TEST_TAG_STRING);
    public static final Tag NO_MATCHING_TAG = new Tag(NO_MATCHING_TAG_STRING);

    // A set of typical tags, used by Typical Tag Person
    public static final Set<Tag> TEST_TAG_SET = new HashSet<>() {{
            add(TEST_TAG);
        }};

    // An empty set of tags
    public static final Set<Tag> EMPTY_TAG_SET = new HashSet<>();

    // A set of tags used by no person
    public static final Set<Tag> NO_MATCHING_TAG_SET = new HashSet<>() {{
            add(NO_MATCHING_TAG);
        }};
}
