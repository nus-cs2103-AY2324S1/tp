package seedu.address.model.person.comparer;

import seedu.address.model.person.Person;

import java.util.Comparator;

/**
 * An abstract class that defines a comparator for sorting Person objects.
 */
public abstract class SortComparator implements Comparator<Person> {

    /**
     * Whether this comparator is currently active.
     */
    protected boolean isActive;

    /**
     * Whether to sort in reverse order.
     */
    protected boolean isReverse;

    /**
     * The priority of this comparator.
     */
    protected int priority;

    /**
     * Creates a new SortComparator with the given parameters.
     *
     * @param isActive Whether this comparator is currently active.
     * @param isReverse Whether to sort in reverse order.
     * @param priority The priority of this comparator.
     */
    public SortComparator(boolean isActive, boolean isReverse, int priority) {
        this.isActive = isActive;
        this.isReverse = isReverse;
        this.priority = isActive ? priority : -1;
    }

    /**
     * Returns whether this comparator is currently active.
     *
     * @return Whether this comparator is currently active.
     */
    public boolean getIsActive() {
        return this.isActive;
    }

    /**
     * Returns whether to sort in reverse order.
     *
     * @return Whether to sort in reverse order.
     */
    public boolean getIsReverse() {
        return this.isReverse;
    }

    /**
     * Returns the priority of this comparator.
     *
     * @return The priority of this comparator.
     */
    public int getPriority() {
        return this.priority;
    }

    /**
     * Compares two Person objects using this comparator's sorting criteria.
     *
     * @param p1 The first Person to compare.
     * @param p2 The second Person to compare.
     * @return A negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
     */
    public abstract int compare(Person p1, Person p2);
}
