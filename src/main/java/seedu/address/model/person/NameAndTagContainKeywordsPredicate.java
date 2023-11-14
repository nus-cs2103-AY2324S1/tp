package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Name} or {@code Tags} matches any of the keywords given.
 */
public class NameAndTagContainKeywordsPredicate implements Predicate<Person> {
    private final List<String> nameKeywords;
    private final List<String> tagKeywords;

    /**
     * Constructor for NameAndTagContainKeywordPredicate.
     *
     * @param nameKeywords List of keywords to search for in the name.
     * @param tagKeywords List of keywords to search for in the tags.
     */
    public NameAndTagContainKeywordsPredicate(List<String> nameKeywords, List<String> tagKeywords) {
        this.nameKeywords = nameKeywords;
        this.tagKeywords = tagKeywords;
    }

    @Override
    public boolean test(Person person) {
        return nameKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword))
            // Previously, the method was checking for keyword matches using person.getTags().toString(),
            // which converted all tags of a person into a single string representation.
            // This approach might not correctly identify individual tags if the toString() method for tags
            // includes additional characters or formatting.
            //
            // The updated approach streams through each individual tag of a Person and checks
            // if any of them match the keywords. This ensures that we accurately check
            // each tag against each keyword, providing a more precise match.
            || person.getTags().stream()
            .anyMatch(tag -> tagKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(tag.tagName, keyword)));

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NameAndTagContainKeywordsPredicate)) {
            return false;
        }

        NameAndTagContainKeywordsPredicate otherNameAndTagContainKeywordsPredicate =
                (NameAndTagContainKeywordsPredicate) other;
        return nameKeywords.equals(otherNameAndTagContainKeywordsPredicate.nameKeywords)
                && tagKeywords.equals(otherNameAndTagContainKeywordsPredicate.tagKeywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("nameKeywords", nameKeywords).add("tagKeywords", tagKeywords).toString();
    }

}
