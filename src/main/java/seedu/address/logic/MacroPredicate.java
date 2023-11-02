package seedu.address.logic;

import java.util.LinkedList;
import java.util.function.Predicate;

/**
 * A composite predicate that can be used to filter a list of objects.
 */
public class MacroPredicate<T> implements Predicate<T> {
    private LinkedList<Predicate<T>> predicates = new LinkedList<>();
    @Override
    public boolean test(T t) {
        for (Predicate<T> predicate : predicates) {
            if (!predicate.test(t)) {
                return false;
            }
        }
        return true;
    }
    public void addPredicate(Predicate<T> predicate) {
        predicates.add(predicate);
    }
    public boolean isEmpty() {
        return predicates.isEmpty();
    }
}
