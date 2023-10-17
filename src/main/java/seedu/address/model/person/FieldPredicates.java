package seedu.address.model.person;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;


/**
 * An abstract class to represent a Predicate
 * Subclasses must implement the abstract methods to test for the Predicate
 */
public abstract class FieldPredicates implements Predicate<Person> {

    protected final List<String> keywords;

    public FieldPredicates(String keyword) {
        keywords = Collections.singletonList(keyword);
    }

    @Override
    public abstract boolean test(Person person);

    @Override
    public abstract boolean equals(Object other);

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }

    /**
     * Checks if the list of keywords in the predicate contain an empty string.
     *
     * @return true if the list of keywords contain an empty string, false otherwise.
     */
    public boolean isEmpty() {
        return keywords.contains("");
    }
}
