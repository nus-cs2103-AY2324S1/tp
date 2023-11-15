package seedu.address.logic.search;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class RangeTest {
    @Test
    void test_createWithFlippedLimits() {
        Range a = new Range(1, 5);
        Range b = new Range(5, 1);

        assertEquals(a, b);
    }

    @Test
    void test_overlap() {
        Range a = new Range(1, 5);
        Range b = new Range(4, 8);
        Range c = new Range(10, 15);

        assertTrue(Range.isOverlap(a, b));
        assertTrue(Range.isOverlap(b, a));

        assertFalse(Range.isOverlap(a, c));
        assertFalse(Range.isOverlap(c, a));
    }

    @Test
    void test_beforeFunctionality() {
        Range a = new Range(1, 5);
        Range b = new Range(6, 8);
        Range c = new Range(5, 7);

        assertTrue(Range.isBefore(a, b));
        assertFalse(Range.isBefore(b, a));
        assertFalse(Range.isBefore(a, c));
    }

    @SuppressWarnings("ConstantValue") //is the point of the test
    @Test
    void test_nulls() {
        Range a = new Range(1, 5);

        // isRangeOverlap
        assertFalse(Range.isOverlap(null, null));
        assertFalse(Range.isOverlap(a, null));
        assertFalse(Range.isOverlap(null, a));

        // isBefore
        assertTrue(Range.isBefore(null, null));
        assertTrue(Range.isBefore(null, a));
        assertTrue(Range.isBefore(a, null));

        // isAfter
        assertTrue(Range.isAfter(null, null));
        assertTrue(Range.isAfter(null, a));
        assertTrue(Range.isAfter(a, null));

        // union
        assertNull(Range.union(null, null));
        assertEquals(a, Range.union(a, null));
        assertEquals(a, Range.union(null, a));
    }

    @Test
    void test_after() {
        Range a = new Range(1, 5);
        Range b = new Range(6, 8);
        Range c = new Range(4, 6);

        assertTrue(Range.isAfter(b, a));
        assertFalse(Range.isAfter(a, b));
        assertFalse(Range.isAfter(b, c));
    }

    @Test
    void test_union() {
        Range a = new Range(1, 5);
        Range b = new Range(4, 8);
        Range c = new Range(10, 15);

        Range result1 = Range.union(a, b);
        assertEquals(new Range(1, 8), result1);

        Range result2 = Range.union(a, c);
        assertEquals(new Range(1, 15), result2);
    }

    @Test
    void test_getSubstring() {
        String str = "abcdef";

        assertEquals("abc", new Range(0, 2).getSubstring(str));
        assertEquals("cdef", new Range(2, 5).getSubstring(str));
        assertNull(new Range(10, 15).getSubstring(str));
        assertNull(new Range(5, 8).getSubstring(str));
    }

    @Test
    void testEquals() {
        Range a = new Range(1, 5);
        Range b = new Range(1, 5);
        Range c = new Range(1, 6);

        assertEquals(a, b);
        assertNotEquals(a, c);
    }

    @Test
    void testHashCode() {
        Range a = new Range(1, 5);
        Range b = new Range(1, 5);
        Range c = new Range(1, 6);

        assertEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a, c);
    }
}
