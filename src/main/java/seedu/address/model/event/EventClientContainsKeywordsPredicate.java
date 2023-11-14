package seedu.address.model.event;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;



/**
 * Tests that a {@code Event}'s clients' names matches any of the keywords given.
 */
public class EventClientContainsKeywordsPredicate implements Predicate<Event> {

    private final List<String> keywords;

    public EventClientContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Event event) {
        return event.getClients().stream()
                .anyMatch(person -> keywords.stream()
                        .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().toString(), keyword))
                );
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EventClientContainsKeywordsPredicate)) {
            return false;
        }
        EventClientContainsKeywordsPredicate otherEventNameContainsKeywordsPredicate =
                (EventClientContainsKeywordsPredicate) other;
        return keywords.equals(otherEventNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
