package seedu.address.logic.search;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


/**
 * Used to represent a SearchPredicate match,
 * determines the fields and corresponding sections of the fields where a match occurs.
 */
class FieldRanges extends HashMap<String, Range> {

    private boolean isMatch = false;

    @Override
    public Range put(String key, Range value) {
        if (value == null) {
            return null;
        }
        isMatch = true;
        return super.put(key, value);
    }

    public void setIsMatch(boolean isMatch) {
        this.isMatch = isMatch;
    }

    public static FieldRanges union(FieldRanges a, FieldRanges b) {
        if (a == null && b == null) {
            return null;
        } else if (a == null) {
            return (FieldRanges) b.clone();
        } else if (b == null) {
            return (FieldRanges) a.clone();
        }
        FieldRanges ranges = new FieldRanges();
        Set<String> keys = new HashSet<>(a.keySet());
        keys.addAll(b.keySet());
        for (String key : keys) {
            ranges.put(key, Range.union(a.get(key), b.get(key)));
        }
        ranges.isMatch = a.isMatch | b.isMatch;
        return ranges;
    }

    public static boolean isMatch(FieldRanges ranges) {
        if (ranges == null) {
            return false;
        }
        return ranges.isMatch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        FieldRanges that = (FieldRanges) o;

        return isMatch == that.isMatch;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 3 * result + (isMatch ? 1 : 0);
        return result;
    }
}
