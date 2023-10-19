package seedu.address.model.predicate;

import java.util.HashSet;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code ID} matches the id given.
 */
public class IdContainsKeywordsPredicate extends SerializablePredicate {
    private final List<String> keywords;

    /**
     * Constructor a {@code IdContainsKeywordsPredicate} with keywords.
     *
     * @param keywords Keywords to check for in a person's id.
     */
    public IdContainsKeywordsPredicate(List<String> keywords) {
        super(person -> keywords.stream().anyMatch(keyword ->
                StringUtil.containsWordIgnoreCase(person.getId().value, keyword)));
        this.keywords = keywords;
    }

    @JsonCreator
    public static IdContainsKeywordsPredicate create(@JsonProperty("keywords") List<String> keywords) {
        return new IdContainsKeywordsPredicate(keywords);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof IdContainsKeywordsPredicate)) {
            return false;
        }

        IdContainsKeywordsPredicate otherPredicate = (IdContainsKeywordsPredicate) other;
        HashSet<String> keywords = new HashSet<String>(this.keywords);
        HashSet<String> otherKeywords = new HashSet<String>(otherPredicate.keywords);
        return keywords.equals(otherKeywords);
    }

    @Override
    public int hashCode() {
        return keywords.hashCode();
    }

    @Override
    public String toString() {
        return "ID Filter: " + keywords;
    }
}
