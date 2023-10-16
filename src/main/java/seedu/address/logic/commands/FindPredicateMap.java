package seedu.address.logic.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Person;

/**
 *  Stores mapping of prefixes to their respective predicates for a {@code find} command.
 *  Keys are unique and each key should be associated with one predicate value.
 *  Values for a given key are stored in a HashSet, and the insertion ordering is not maintained.
 */
public class FindPredicateMap {

    /** Prefixes mapped to their respective predicates **/
    private final Map<Prefix, Predicate<Person>> map = new HashMap<>();

    /**
     * Associates the specified predicate value with {@code prefix} key in this map.
     *
     * @param prefix prefix key with which the specified argument value is to be associated
     * @param predicateValue predicate value to be associated with the specified prefix key
     */
    public void put(Prefix prefix, Predicate<Person> predicateValue) {
        map.put(prefix, predicateValue);
    }

    /**
     * Returns the predicate value of {@code prefix}.
     */
    public Optional<Predicate<Person>> getPredicateValue(Prefix prefix) {
        return Optional.ofNullable(map.get(prefix));
    }

    /**
     * Returns all predicate value of {@code prefix}.
     */
    public List<Predicate<Person>> getAllPredicates() {
        if (map.isEmpty()) {
            return new ArrayList<>();
        }
        return new ArrayList<>(map.values());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindPredicateMap)) {
            return false;
        }

        FindPredicateMap otherFindPredicateMap = (FindPredicateMap) other;

        if (this.map.size() != otherFindPredicateMap.map.size()) {
            return false;
        }

        boolean isHashMapEquals = this.map.equals(otherFindPredicateMap.map);
        boolean isKeyValueEquals = this.map.keySet().stream()
                .map(key -> otherFindPredicateMap.getPredicateValue(key).equals(this.getPredicateValue(key)))
                .reduce(true, (x, y) -> x && y);
        return isHashMapEquals || isKeyValueEquals;
    }
}
