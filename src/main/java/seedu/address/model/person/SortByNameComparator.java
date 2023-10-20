package seedu.address.model.person;

import java.util.Comparator;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Compares a {@code Person}'s {@code Name} to another {@code Person}'s {@code Name} to determine
 * lexicographical ordering of the names.
 */
public class SortByNameComparator implements Comparator<Person> {


    @Override
    public int compare(Person o1, Person o2) {
        return o1.getName().compareTo(o2.getName());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortByNameComparator)) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this).toString();
    }
}
