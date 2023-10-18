package seedu.address.model.filter;

import java.util.HashSet;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Name} contains the keywords given.
 */
public class NameContainsKeywordsPredicate extends SerializablePredicate {
    private final List<String> keywords;

    /**
     * Constructor a {@code NameContainsKeywordsPredicate} with keywords.
     *
     * @param keywords Keywords to check for in a person's name.
     */
    public NameContainsKeywordsPredicate(List<String> keywords) {
        super(person -> keywords.stream().anyMatch(keyword ->
                StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword)));
        this.keywords = keywords;
    }

    @JsonCreator
    public static NameContainsKeywordsPredicate create(@JsonProperty("keywords") List<String> keywords) {
        return new NameContainsKeywordsPredicate(keywords);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NameContainsKeywordsPredicate)) {
            return false;
        }

        NameContainsKeywordsPredicate otherPredicate = (NameContainsKeywordsPredicate) other;
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
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
