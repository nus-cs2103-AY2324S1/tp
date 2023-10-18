package seedu.address.logic.search;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

class FieldRangesTest {

    @Test
    public void test_put_null() {
        FieldRanges fr = new FieldRanges();
        assertNull(fr.put("", null));
        assertFalse(FieldRanges.isMatch(fr));
    }

    @SuppressWarnings("ConstantValue")
    @Test
    public void test_isMatch_null() {
        assertFalse(FieldRanges.isMatch(null));
    }

    @Test
    public void test_setIsMatch() {
        FieldRanges fr = new FieldRanges();
        assertFalse(FieldRanges.isMatch(fr));
        fr.setIsMatch(true);
        assertTrue(FieldRanges.isMatch(fr));
        fr.setIsMatch(false);
        assertFalse(FieldRanges.isMatch(fr));
    }

    @Test
    public void test_unionSameRange() {
        FieldRanges frA = new FieldRanges();
        FieldRanges frB = new FieldRanges();
        frA.put("a", new Range(1, 5));
        frB.put("a", new Range(3, 8));
        FieldRanges actual = FieldRanges.union(frA, frB);

        FieldRanges expected = new FieldRanges();
        expected.put("a", new Range(1, 8));

        assertEquals(expected, actual);
    }

    @Test
    public void test_unionDifferentRange() {
        FieldRanges frA = new FieldRanges();
        FieldRanges frB = new FieldRanges();
        frA.put("a", new Range(1, 5));
        frB.put("b", new Range(3, 8));
        FieldRanges actual = FieldRanges.union(frA, frB);

        FieldRanges expected = new FieldRanges();
        expected.put("a", new Range(1, 5));
        expected.put("b", new Range(3, 8));

        assertEquals(expected, actual);
    }

    @Test
    public void test_unionNulls() {
        assertNull(FieldRanges.union(null, null));

        FieldRanges fr = new FieldRanges();
        fr.put("a", new Range(1, 5));

        assertEquals(FieldRanges.union(fr, null), FieldRanges.union(null, fr));
        assertEquals(FieldRanges.union(fr, null), FieldRanges.union(null, fr));
    }

    @Test
    public void test_equals() {
        FieldRanges frA = new FieldRanges();
        FieldRanges frB = new FieldRanges();

        assertEquals(frA, frB);
        frA.setIsMatch(true);
        assertNotEquals(frA, frB);
    }

    @Test
    public void test_unionEmptyMatches() {
        FieldRanges frA = new FieldRanges();
        FieldRanges frB = new FieldRanges();
        frA.setIsMatch(true);
        FieldRanges actual = FieldRanges.union(frA, frB);

        FieldRanges expected = new FieldRanges();
        expected.setIsMatch(true);

        assertEquals(expected, actual);
    }

    @Test
    public void test_union_deepCopy() {
        FieldRanges frA = new FieldRanges();
        FieldRanges frB = new FieldRanges();
        frA.put("a", new Range(1, 5));
        frB.put("a", new Range(1, 5));
        FieldRanges.union(frA, frB);
        FieldRanges actual = FieldRanges.union(frA, frB);
        actual.put("x", new Range(6, 10));
        assertEquals(frA, frB);
        assertNotEquals(frA, actual);
        assertTrue(frA != frB && actual != frA && actual != frB);

        actual = FieldRanges.union(null, frA);
        assertTrue(actual != frA && actual != null);

        actual = FieldRanges.union(frA, null);
        assertTrue(actual != frA && actual != null);
    }

    @Test
    public void test_hashCode() {
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                FieldRanges newFr = new FieldRanges();
                newFr.put("a", new Range(i, i + j));
                int hash = newFr.hashCode();
                assertFalse(hashSet.contains(hash));
                hashSet.add(hash);
            }
        }
    }
}
