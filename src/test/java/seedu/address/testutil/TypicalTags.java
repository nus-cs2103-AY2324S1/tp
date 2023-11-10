package seedu.address.testutil;

import seedu.address.model.tag.Tag;

/**
 * A utility class for providing typical Tag objects and sets of tags for testing.
 */
public class TypicalTags {
    public static final String TEST_TAG_NAME_STRING = "developer";
    public static final String TEST_TAG_CATEGORY_STRING = "role";
    public static final String NO_MATCHING_TAG_NAME_STRING = "NoMatchingTag";
    public static final String NO_MATCHING_TAG_CATEGORY_STRING = "uncategorised";
    public static final String INVALID_TAG_STRING = "@";
    public static final Tag TEST_TAG = new Tag(TEST_TAG_NAME_STRING, TEST_TAG_CATEGORY_STRING);
    public static final Tag NO_MATCHING_TAG = new Tag(NO_MATCHING_TAG_NAME_STRING, NO_MATCHING_TAG_CATEGORY_STRING);
}
