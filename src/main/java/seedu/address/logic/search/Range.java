package seedu.address.logic.search;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Attached to a String representation of the value of a field,
 * represents the start and end of a SearchMatcher match on that field.
 * Intended for use by {@link SearchMatcher#test}, to enable certain features such as order and non-overlap.
 * or enabling/disabling overlaps.
 */
class Range {
    private final int start;
    private final int end;

    Range(int start, int end) {
        if (start >= end) { //swap
            start = start ^ end;
            end = start ^ end;
            start = start ^ end;
        }
        this.start = start;
        this.end = end;
    }

    static boolean isOverlap(Range a, Range b) {
        if (a == null || b == null) {
            return false;
        }
        return !(isBefore(a,b) || isAfter(a,b));
    }

    static boolean isBefore(Range a, Range b) {
        if (a == null || b == null) {
            return true;
        }
        return a.end < b.start;
    }

    static boolean isAfter(Range a, Range b) {
        if (a == null || b == null) {
            return true;
        }
        return a.start > b.end;
    }

    static Range union(Range a, Range b) {
        if (a == null) {
            return b;
        } else if (b == null) {
            return a;
        }
        return new Range(min(a.start, b.start), max(a.end, b.end));
    }

    public String getSubstring(String string) {
        if (string.length() < this.end+1) {
            return null;
        }
        return string.substring(this.start, this.end+1);
    }

    @Override
    public boolean equals(Object o) { //IntelliJ generated
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Range range = (Range) o;

        if (start != range.start) return false;
        return end == range.end;
    }

    @Override
    public int hashCode() { //IntelliJ generated
        int result = start;
        result = 31 * result + end;
        return result;
    }
}