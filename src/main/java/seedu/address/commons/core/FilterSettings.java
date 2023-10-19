package seedu.address.commons.core;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;
import seedu.address.model.predicate.SerializablePredicate;

/**
 * A Serializable class that contains the filter settings.
 * Guarantees: immutable.
 */
public class FilterSettings implements Serializable {
    private static final HashSet<SerializablePredicate> DEFAULT_FILTERS = new HashSet<>();

    private final HashSet<SerializablePredicate> filters;

    /**
     * Constructs a {@code FilterSettings} with the default filter.
     */
    public FilterSettings() {
        this.filters = DEFAULT_FILTERS;
    }

    /**
     * Constructs a {@code FilterSettings} with the specified filter.
     */
    public FilterSettings(HashSet<SerializablePredicate> filters) {
        @SuppressWarnings("unchecked")
        HashSet<SerializablePredicate> clonedFilter = (HashSet<SerializablePredicate>) filters.clone();
        this.filters = clonedFilter;
    }

    public HashSet<SerializablePredicate> getFilters() {
        @SuppressWarnings("unchecked")
        HashSet<SerializablePredicate> filters = (HashSet<SerializablePredicate>) this.filters.clone();
        return filters;
    }

    public Predicate<Person> getComposedFilter() {
        SerializablePredicate composedFilter = new SerializablePredicate(unused -> true);

        for (SerializablePredicate predicate : filters) {
            composedFilter = composedFilter.and(predicate);
        }

        return composedFilter.getPredicate();
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
        return filters.equals(otherFilterSettings.filters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filters);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("filters", filters)
                .toString();
    }
}
