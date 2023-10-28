package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

public class IllnessContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public IllnessContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(tag -> {
                    Tag currentTag = new Tag(tag);
                    return person.getTags().contains(currentTag);
                });
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof IllnessContainsKeywordsPredicate)) {
            return false;
        }

        IllnessContainsKeywordsPredicate otherIllnessContainsKeywordsPredicate = (IllnessContainsKeywordsPredicate)
                other;
        return keywords.equals(otherIllnessContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
