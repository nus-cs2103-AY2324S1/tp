package seedu.address.testutil;

import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;

/**
 * A utility class containing a list of {@code Index} objects to be used in tests.
 */
public class TypicalIndexes {
    public static final Index INDEX_FIRST_PERSON = Index.fromOneBased(1);
    public static final Index INDEX_SECOND_PERSON = Index.fromOneBased(2);
    public static final Index INDEX_THIRD_PERSON = Index.fromOneBased(3);
    public static final Index INDEX_FOURTH_PERSON = Index.fromOneBased(4);
    public static final List<Index> FIRST_INDEXES = Arrays.asList(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON,
        INDEX_THIRD_PERSON);
    public static final List<Index> SECOND_INDEXES = Arrays.asList(INDEX_SECOND_PERSON, INDEX_THIRD_PERSON,
            INDEX_FOURTH_PERSON);
    public static final Index INDEX_NINTH_PERSON = Index.fromOneBased(9);
    public static final Index INDEX_TENTH_PERSON = Index.fromOneBased(10);
    public static final Index INDEX_ELEVENTH_PERSON = Index.fromOneBased(11);
    public static final Index INDEX_TWELFTH_PERSON = Index.fromOneBased(12);
}
