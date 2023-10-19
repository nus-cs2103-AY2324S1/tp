package seedu.address.model.util;

import java.util.HashSet;

/**
 * This class inherits from a HashSet but specifies a maximum size.
 *
 * @param <T> The type of the element in the LimitedHashSet.
 */
public class LimitedHashSet<T> extends HashSet<T> {

    private int maxSize;

    public LimitedHashSet(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public boolean add(T element) {
        if (size() >= maxSize) {
            return false;
        }

        return super.add(element);
    }
}
