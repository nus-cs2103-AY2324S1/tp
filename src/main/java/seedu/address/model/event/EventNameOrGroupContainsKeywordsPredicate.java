package seedu.address.model.event;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Event}'s {@code Name} matches any of the keywords given.
 */
public class EventNameOrGroupContainsKeywordsPredicate implements Predicate<Event> {
    private final List<String> keywords;

    /**
     * Constructs the EventNameContainsKeywordsPredicate with provided keywords.
     *
     * @param keywords keywords from user input.
     */
    public EventNameOrGroupContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }
    @Override
    public boolean test(Event event) {
        return this.keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(event.getName().toString(), keyword)
                        || event.getGroups().stream().anyMatch(group ->
                                StringUtil.containsWordIgnoreCase(group.groupName, keyword))
                        || event.getNames().stream().anyMatch(name -> StringUtil.containsWordIgnoreCase(
                                name.fullName, keyword)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EventNameOrGroupContainsKeywordsPredicate)) {
            return false;
        }

        EventNameOrGroupContainsKeywordsPredicate otherEventNameContainsKeywordsPredicate =
                (EventNameOrGroupContainsKeywordsPredicate) other;
        return keywords.equals(otherEventNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
