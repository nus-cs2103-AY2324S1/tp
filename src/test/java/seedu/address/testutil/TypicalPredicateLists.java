package seedu.address.testutil;

import static seedu.address.testutil.TypicalTags.NO_MATCHING_TAG_NAME_STRING;
import static seedu.address.testutil.TypicalTags.TEST_TAG_NAME_STRING;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.person.Person;
import seedu.address.model.person.StatusContainsKeywordsPredicate;
import seedu.address.model.person.StatusTypes;
import seedu.address.model.person.TagContainsKeywordsPredicate;

/**
 * A utility class that provides typical predicate lists for testing purposes.
 */
public class TypicalPredicateLists {
    // Predicates containing only tags
    public static final TagContainsKeywordsPredicate TAG_PREDICATE_ONE =
            new TagContainsKeywordsPredicate(List.of(TEST_TAG_NAME_STRING));
    public static final TagContainsKeywordsPredicate TAG_PREDICATE_TWO =
            new TagContainsKeywordsPredicate(List.of(NO_MATCHING_TAG_NAME_STRING));
    public static final List<Predicate<Person>> PREDICATE_LIST_CONTAINING_TAG_ONE = new ArrayList<>() {{
            add(TAG_PREDICATE_ONE);
        }};
    public static final List<Predicate<Person>> PREDICATE_LIST_CONTAINING_TAG_TWO = new ArrayList<>() {{
            add(TAG_PREDICATE_TWO);
        }};

    // Predicates containing only status
    public static final String TEST_STATUS_ONE = StatusTypes.OFFERED.toString();
    public static final StatusContainsKeywordsPredicate STATUS_PREDICATE_ONE =
            new StatusContainsKeywordsPredicate(List.of(TEST_STATUS_ONE));
    public static final String TEST_STATUS_TWO = StatusTypes.PRELIMINARY.toString();
    public static final StatusContainsKeywordsPredicate STATUS_PREDICATE_TWO =
            new StatusContainsKeywordsPredicate(List.of(TEST_STATUS_TWO));
    public static final List<Predicate<Person>> PREDICATE_LIST_CONTAINING_STATUS_ONE = new ArrayList<>() {{
            add(STATUS_PREDICATE_ONE);
        }};
    public static final List<Predicate<Person>> PREDICATE_LIST_CONTAINING_STATUS_TWO = new ArrayList<>() {{
            add(STATUS_PREDICATE_TWO);
        }};

    // Predicates containing tags and status
    public static final List<Predicate<Person>> PREDICATE_LIST_CONTAINING_TAG_AND_STATUS_ONE = new ArrayList<>() {{
            add(STATUS_PREDICATE_ONE);
            add(TAG_PREDICATE_ONE);
        }};
    public static final List<Predicate<Person>> PREDICATE_LIST_CONTAINING_TAG_AND_STATUS_TWO = new ArrayList<>() {{
            add(TAG_PREDICATE_TWO);
            add(STATUS_PREDICATE_TWO);
        }};
    public static final List<Predicate<Person>> PREDICATE_LIST_CONTAINING_TAG_AND_STATUS_MIX = new ArrayList<>() {{
            add(TAG_PREDICATE_ONE);
            add(STATUS_PREDICATE_TWO);
        }};
}
