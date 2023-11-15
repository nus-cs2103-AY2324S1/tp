package seedu.address.model.person.sorter;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;

import seedu.address.model.person.Person;

/**
 * A sorter for persons in the address book based on name in ascending lexicographic order.
 * Subclass of {@link PersonSorter}.
 */
public class PersonNameAscendingSorter extends PersonSorter {

    /**
     * Constructs a {@code PersonNameAscendingSorter}.
     */
    public PersonNameAscendingSorter() {
        criterion = "name-ascending";
    }

    @Override
    public int compare(Person person1, Person person2) {
        requireAllNonNull(person1, person2);
        Comparator<String> caseInsensitiveOrder = String
                .CASE_INSENSITIVE_ORDER;

        Comparator<String> caseInsensitiveThenNaturalOrder = String
                .CASE_INSENSITIVE_ORDER
                .thenComparing(Comparator.naturalOrder());

        String person1Name = person1.getName().fullName;
        String person2Name = person2.getName().fullName;

        requireAllNonNull(person1Name, person2Name);

        int person1NameLength = person1Name.length();
        int person2NameLength = person2Name.length();

        int compareInsensitive = caseInsensitiveOrder
                .compare(person1Name, person2Name);

        // Case-insensitive sort determines that both strings are equal
        // Solution below adapted from
        // https://stackoverflow.com/questions/57277475/sort-results-in-ascending-return-in-the-form-a-a-b-b-in-java
        if (compareInsensitive == 0) {
            for (int i = 0; i < Math.min(person1NameLength, person2NameLength); i++) {
                // Compare each character in the string until a difference in character is found.
                int compare = -1 * caseInsensitiveThenNaturalOrder
                        .compare(person1Name.substring(i, i + 1), person2Name.substring(i, i + 1));
                if (compare != 0) {
                    return compare;
                }
            }
            // If we have reached the end of the shorter string, but no differences have been found, then
            // the longer string should come after the shorter string.
            return Integer.compare(person1Name.length(), person2Name.length());
        }
        return compareInsensitive;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PersonNameAscendingSorter)) {
            return false;
        }

        PersonNameAscendingSorter otherPersonNameAscendingSorter = (PersonNameAscendingSorter) other;
        return this.getSortingCriterion().equals(otherPersonNameAscendingSorter.getSortingCriterion());
    }

    @Override
    public int hashCode() {
        return criterion.hashCode();
    }
}
