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
    public static final Tag TEST_TAG_2 = new Tag("software", "dept");
    public static final Tag TEST_TAG_3 = new Tag("interview1", "assessment");
    public static final Tag TEST_TAG_4 = new Tag("intern", "employment");
    public static final Tag TEST_TAG_5 = new Tag("novice", "experience");
    public static final Tag TEST_TAG_6 = new Tag("ComputerScience", "major");
    public static final Tag TEST_TAG_7 = new Tag("fulltime", "employment");
    public static final Tag NO_MATCHING_TAG = new Tag(NO_MATCHING_TAG_NAME_STRING, NO_MATCHING_TAG_CATEGORY_STRING);
}
