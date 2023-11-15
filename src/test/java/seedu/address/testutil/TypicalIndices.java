package seedu.address.testutil;

import seedu.address.commons.core.index.Indices;

/**
 * A utility class containing a list of {@code Indices} objects to be used in tests.
 */
public class TypicalIndices {
    public static final Indices ONEBASED_ONE = Indices.fromOneBased(new int[]{1});

    public static final Indices ONEBASED_ONE_TO_THREE = Indices.fromOneBased(new int[]{1, 2, 3});

    public static final Indices ONEBASED_FOUR_TO_SIX = Indices.fromOneBased(new int[]{4, 5, 6});

    public static final Indices ONEBASED_ONE_TO_THREE_JUMBLED = Indices.fromOneBased(new int[]{2, 1, 3});

    public static final Indices ZEROBASED_ZERO_TO_TWO = Indices.fromZeroBased(new int[]{0, 1, 2});

    public static final Indices ZEROBASED_THREE_TO_FIVE = Indices.fromZeroBased(new int[]{3, 4, 5});

}
