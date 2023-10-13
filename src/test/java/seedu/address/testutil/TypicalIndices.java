package seedu.address.testutil;

import seedu.address.commons.core.index.Indices;

/**
 * A utility class containing a list of {@code Indices} objects to be used in tests.
 */
public class TypicalIndices {
    public static final Indices INDICES_ONE = Indices.fromOneBased(new int[]{1});

    public static final Indices INDICES_ONE_TO_THREE = Indices.fromOneBased(new int[]{1, 2, 3});

    public static final Indices INDICES_FOUR_TO_SIX = Indices.fromOneBased(new int[]{4, 5, 6});

    public static final Indices INDICES_ONE_TO_THREE_JUMBLED = Indices.fromOneBased(new int[]{2, 1, 3});

    public static final Indices INDICES_ONE_THREE_FIVE = Indices.fromOneBased(new int[]{1, 3, 5});
}
