package seedu.address.model.event;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Event}'s {@code Name} matches any of the keywords given.
 */
public class EventNameContainsKeywordsPredicate implements Predicate<Event> {
    private final List<String> keywords;

    /**
     * Constructs the EventNameContainsKeywordsPredicate with provided keywords.
     *
     * @param keywords keywords from user input.
     */
    public EventNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }
    @Override
    public boolean test(Event event) {
        return this.keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(event.getName().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EventNameContainsKeywordsPredicate)) {
            return false;
        }

        EventNameContainsKeywordsPredicate otherEventNameContainsKeywordsPredicate =
                (EventNameContainsKeywordsPredicate) other;
        return keywords.equals(otherEventNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
