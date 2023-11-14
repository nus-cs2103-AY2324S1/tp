package connectify.model;

import java.util.List;
import java.util.function.Predicate;

import connectify.commons.util.StringUtil;
import connectify.commons.util.ToStringBuilder;

/**
 * Tests that an entity's {@code Name} matches any of the keywords given.
 * This entity can be a {@code Person} or a {@code Company}.
 */
public class EntityNameContainsKeywordsPredicate implements Predicate<Entity> {
    private final List<String> keywords;

    public EntityNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Entity entity) {
        String name = entity.getName().fullName;
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(name, keyword));
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EntityNameContainsKeywordsPredicate)) {
            return false;
        }

        EntityNameContainsKeywordsPredicate otherPredicate = (EntityNameContainsKeywordsPredicate) other;
        return keywords.equals(otherPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
