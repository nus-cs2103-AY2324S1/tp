package seedu.address.commons.core.index;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndices.ONEBASED_FOUR_TO_SIX;
import static seedu.address.testutil.TypicalIndices.ONEBASED_ONE;
import static seedu.address.testutil.TypicalIndices.ONEBASED_ONE_TO_THREE;
import static seedu.address.testutil.TypicalIndices.ZEROBASED_THREE_TO_FIVE;
import static seedu.address.testutil.TypicalIndices.ZEROBASED_ZERO_TO_TWO;

import org.junit.jupiter.api.Test;

public class IndicesTest {

    public static final int[] ZERO_TO_TWO = new int[]{0, 1, 2};
    public static final int[] ONE_TO_THREE = new int[]{1, 2, 3};

    public static final int[] THREE_TO_FIVE = new int[]{3, 4, 5};
    public static final int[] FOUR_TO_SIX = new int[]{4, 5, 6};

    @Test
    public void createZeroBasedIndices() {
        // invalid index
        assertThrows(IndexOutOfBoundsException.class, () -> Indices.fromZeroBased(new int[]{-1, 0}));

        // check equality using the same base
        assertArrayEquals(ZERO_TO_TWO, Indices.fromZeroBased(ZERO_TO_TWO).getZeroBased());
        assertArrayEquals(THREE_TO_FIVE, Indices.fromZeroBased(THREE_TO_FIVE).getZeroBased());

        // convert from zero-based index to one-based index
        assertArrayEquals(ONE_TO_THREE, Indices.fromZeroBased(ZERO_TO_TWO).getOneBased());
        assertArrayEquals(FOUR_TO_SIX, Indices.fromZeroBased(THREE_TO_FIVE).getOneBased());
    }

    @Test
    public void createOneBasedIndices() {
        // invalid index
        assertThrows(IndexOutOfBoundsException.class, () -> Indices.fromOneBased(ZERO_TO_TWO));

        // check equality using the same base
        assertArrayEquals(ONE_TO_THREE, Indices.fromOneBased(ONE_TO_THREE).getOneBased());
        assertArrayEquals(FOUR_TO_SIX, Indices.fromOneBased(FOUR_TO_SIX).getOneBased());

        // convert from one-based index to zero-based index
        assertArrayEquals(ZERO_TO_TWO, Indices.fromOneBased(ONE_TO_THREE).getZeroBased());
        assertArrayEquals(THREE_TO_FIVE, Indices.fromOneBased(FOUR_TO_SIX).getZeroBased());
    }

    @Test
    public void getZeroBasedStrings() {
        assertEquals("0 1 2", ONEBASED_ONE_TO_THREE.getZeroBasedString());
        assertEquals("3 4 5", ONEBASED_FOUR_TO_SIX.getZeroBasedString());
        assertEquals("0 1 2", ZEROBASED_ZERO_TO_TWO.getZeroBasedString());
        assertEquals("3 4 5", ZEROBASED_THREE_TO_FIVE.getZeroBasedString());
    }

    @Test
    public void getOneBasedStrings() {
        assertEquals("1 2 3", ZEROBASED_ZERO_TO_TWO.getOneBasedString());
        assertEquals("4 5 6", ZEROBASED_THREE_TO_FIVE.getOneBasedString());
        assertEquals("1 2 3", ONEBASED_ONE_TO_THREE.getOneBasedString());
        assertEquals("4 5 6", ONEBASED_FOUR_TO_SIX.getOneBasedString());
    }

    @Test
    public void getSize() {
        assertEquals(1, ONEBASED_ONE.getSize());
        assertEquals(3, ONEBASED_ONE_TO_THREE.getSize());
    }

    @Test
    public void getZeroBasedMin() {
        assertEquals(0, ZEROBASED_ZERO_TO_TWO.getZeroBasedMin());
        assertEquals(3, ZEROBASED_THREE_TO_FIVE.getZeroBasedMin());
        assertEquals(0, ONEBASED_ONE_TO_THREE.getZeroBasedMin());
        assertEquals(3, ONEBASED_FOUR_TO_SIX.getZeroBasedMin());
    }

    @Test
    public void getZeroBasedMax() {
        assertEquals(2, ZEROBASED_ZERO_TO_TWO.getZeroBasedMax());
        assertEquals(5, ZEROBASED_THREE_TO_FIVE.getZeroBasedMax());
        assertEquals(2, ONEBASED_ONE_TO_THREE.getZeroBasedMax());
        assertEquals(5, ONEBASED_FOUR_TO_SIX.getZeroBasedMax());
    }

    @Test
    public void equals() {
        // same values -> returns true
        assertTrue(ONEBASED_ONE_TO_THREE.equals(ZEROBASED_ZERO_TO_TWO));
        assertTrue(ONEBASED_ONE_TO_THREE.equals(Indices.fromOneBased(new int[]{1, 2, 3})));

        // same object -> returns true
        assertTrue(ONEBASED_ONE_TO_THREE.equals(ONEBASED_ONE_TO_THREE));

        // null -> returns false
        assertFalse(ONEBASED_ONE_TO_THREE.equals(null));

        // different types -> returns false
        assertFalse(ONEBASED_ONE_TO_THREE.equals(5.0f));

        // different index -> returns false
        assertFalse(ONEBASED_ONE_TO_THREE.equals(ONEBASED_FOUR_TO_SIX));
    }

    @Test
    public void toStringMethod() {
        String expected = Indices.class.getCanonicalName() + "{zeroBasedIndices="
                + ONEBASED_ONE_TO_THREE.getZeroBasedString() + "}";
        assertEquals(expected, ONEBASED_ONE_TO_THREE.toString());
    }
}

