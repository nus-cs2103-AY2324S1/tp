package seedu.address.model.meeting;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Meetings}'s {@code Attendee} matches any of the keywords given.
 */
public class AttendeeContainsKeywordsPredicate implements Predicate<Meeting> {
    private final List<String> keywords;

    public AttendeeContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Meeting meeting) {
        return keywords.stream()
                .anyMatch(keyword -> keyword.isEmpty() || meeting.getAttendees().stream()
                        .anyMatch(attendee -> StringUtil.containsWordIgnoreCase(attendee.getAttendeeName(), keyword)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AttendeeContainsKeywordsPredicate)) {
            return false;
        }

        AttendeeContainsKeywordsPredicate otherAttendeeContainsKeywordsPredicate =
                (AttendeeContainsKeywordsPredicate) other;
        return keywords.equals(otherAttendeeContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
