package seedu.address.model.person;

import java.util.List;

/**
 * Represents a factory for creating keyword predicates of a specified type.
 *
 * @param <T> The type of objects that the generated keyword predicate will filter.
 */
@FunctionalInterface
public interface KeywordPredicateFactory<T> {

    /**
     * Creates a keyword predicate for filtering objects based on a list of keywords.
     *
     * @param keywords A list of keywords to be used for filtering.
     * @return A keyword predicate for filtering objects based on the given keywords.
     */
    KeywordPredicate<T> createPredicate(List<String> keywords);
}
