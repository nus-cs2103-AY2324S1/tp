package seedu.address.commons.core;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

/**
 * A Serializable class that contains the filter settings.
 * Guarantees: immutable.
 */
public class FilterSettings implements Serializable {
    private static final HashSet<Predicate<Person>> DEFAULT_FILTERS = new HashSet<>();

    private final HashSet<Predicate<Person>> filterSet;

    /**
     * Constructs a {@code FilterSettings} with the default filter.
     */
    public FilterSettings() {
        this.filterSet = DEFAULT_FILTERS;
    }

    /**
     * Constructs a {@code FilterSettings} with the specified filter.
     */
    public FilterSettings(HashSet<Predicate<Person>> filterSet) {
        @SuppressWarnings("unchecked")
        HashSet<Predicate<Person>> clonedFilter = (HashSet<Predicate<Person>>) filterSet.clone();
        this.filterSet = clonedFilter;
    }

    public HashSet<Predicate<Person>> getFilterSet() {
        @SuppressWarnings("unchecked")
        HashSet<Predicate<Person>> filters = (HashSet<Predicate<Person>>) this.filterSet.clone();
        return filters;
    }

    public Predicate<Person> getComposedFilter() {
        Predicate<Person> composedFilter = unused -> true;

        for (Predicate<Person> predicate : filterSet) {
            composedFilter = predicate.and(predicate);
        }

        return composedFilter;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterSettings)) {
            return false;
        }

        FilterSettings otherFilterSettings = (FilterSettings) other;
        return filterSet.equals(otherFilterSettings.filterSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filterSet);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("filterList", filterSet)
                .toString();
    }
}
