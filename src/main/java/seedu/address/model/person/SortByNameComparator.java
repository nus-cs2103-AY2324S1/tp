package seedu.address.model.person;

import java.util.Comparator;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class SortByNameComparator implements Comparator<Person> {

    public SortByNameComparator() {
    }


    @Override
    public int compare(Person o1, Person o2) {
        return o1.getName().compareTo(o2.getName());
    }

//    public boolean equals(Object other) {
//        if (other == this) {
//            return true;
//        }
//
//        // instanceof handles nulls
//        if (!(other instanceof NameContainsKeywordsPredicate)) {
//            return false;
//        }
//
//        NameContainsKeywordsPredicate otherNameContainsKeywordsPredicate = (NameContainsKeywordsPredicate) other;
//        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
//    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).toString();
    }
}
