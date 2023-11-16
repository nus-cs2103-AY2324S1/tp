package seedu.address.testutil;

import java.util.List;

import seedu.address.commons.core.index.Index;

/**
 * A utility class containing a list of {@code Index} objects to be used in tests.
 */
public class TypicalIndexes {
    public static final Index INDEX_FIRST_PERSON = Index.fromOneBased(1);
    public static final Index INDEX_SECOND_PERSON = Index.fromOneBased(2);
    public static final Index INDEX_THIRD_PERSON = Index.fromOneBased(3);
    public static final Index INDEX_FIRST_PROJECT = Index.fromOneBased(1);
    public static final Index INDEX_SECOND_PROJECT = Index.fromOneBased(2);
    public static final Index INVALID_INDEX_PROJECT = Index.fromOneBased(5);
    public static final List<Index> INDEX_FIRST_PROJECT_AND_DEADLINE = List.of(Index.fromOneBased(1),
            Index.fromOneBased(1));
}
