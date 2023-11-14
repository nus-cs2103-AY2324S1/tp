package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Tags} matches any of the keywords given.
 */
public class PersonContainsTagsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public PersonContainsTagsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    // Returns true if person's tags contain any of the keywords
    @Override
    public boolean test(Person person) {

        // Convert each tag of the person to its string representation and then concatenate them using spaces.
        // The square brackets encapsulate each tag to ensure distinctiveness when checking against keywords.
        // For example, tags such as [[Engineer], [Developer]] will translate to the string "[Engineer] [Developer]".
        String personTags = person.getTags()
                .stream()
                .map(Object::toString)
                .collect(Collectors.joining(" "));

        // Match against the concatenated string of tags.
        // The square brackets ensure that keywords match against full tags rather than partial matches.
        // This way, a keyword like "[Eng]" won't match with the tag "[Engineer]".
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(personTags, "[" + keyword + "]"));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonContainsTagsPredicate)) {
            return false;
        }

        PersonContainsTagsPredicate otherPersonContainsTagsPredicate = (PersonContainsTagsPredicate) other;
        return keywords.equals(otherPersonContainsTagsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
