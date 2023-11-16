package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * An interface representing a predicate that filters objects based on keywords.
 *
 * @param <Person> The type of objects that this predicate filters.
 */
public interface KeywordPredicate<Person> extends Predicate<Person> {

    // You can define common methods if needed.
    // For now, this interface will be empty and serve mainly as a type.
}
