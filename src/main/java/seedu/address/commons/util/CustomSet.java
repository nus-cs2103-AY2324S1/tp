package seedu.address.commons.util;

import java.util.HashSet;

/**
 * A custom set implementation that extends the functionality of a standard HashSet.
 * This set allows the 'contains' method to return true for any element when the set is empty.
 *
 * @param <E> the type of elements stored in the set
 */
public class CustomSet<E> extends HashSet<E> {
    @Override
    public boolean contains(Object o) {
        if (isEmpty()) {
            return true;
        }
        return super.contains(o);
    }
}
