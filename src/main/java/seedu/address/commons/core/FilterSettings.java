package seedu.address.commons.core;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * A Serializable class that contains the filter settings.
 * Guarantees: immutable.
 */
public class FilterSettings implements Serializable {
    
    private static final HashSet<Predicate<Person>> DEFAULT_FILTER = new HashSet<>();

    private final HashSet<Predicate<Person>> filterList;

    /**
     * Constructs a {@code FilterSettings} with the default filter.
     */
    public FilterSettings() {
        this.filterList = DEFAULT_FILTER;
    }

    /**
     * Constructs a {@code FilterSettings} with the specified filter.
     */
    public FilterSettings(HashSet<Predicate<Person>> filter) {
        @SuppressWarnings("unchecked")
        HashSet<Predicate<Person>> clonedFilter = (HashSet<Predicate<Person>>) filter.clone();
        this.filterList = clonedFilter;
    }

    public Set<Predicate<Person>> getFilterList() {
        @SuppressWarnings("unchecked")
        HashSet<Predicate<Person>> filterList = (HashSet<Predicate<Person>>) this.filterList.clone();
        return filterList;
    }

    public Predicate<Person> getComposedFilter() {
        Predicate<Person> composedFilter = unused -> true;

        for (Predicate<Person> predicate : filterList) {
            composedFilter = predicate.and(predicate);
        }

        return composedFilter.or(Model.PREDICATE_SHOW_ALL_PERSONS);
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
        return filterList.equals(otherFilterSettings.filterList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filterList);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("filterList", filterList)
                .toString();
    }
}
