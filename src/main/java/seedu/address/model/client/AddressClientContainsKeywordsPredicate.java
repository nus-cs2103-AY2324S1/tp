package seedu.address.model.client;

import java.util.List;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.KeywordPredicate;

/**
 * Tests that a {@code client}'s {@code Address} matches any of the keywords given.
 */
public class AddressClientContainsKeywordsPredicate implements KeywordPredicate<Client> {
    private final List<String> keywords;

    public AddressClientContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Client client) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsPartialWordIgnoreCase(client.getAddress().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressClientContainsKeywordsPredicate)) {
            return false;
        }

        AddressClientContainsKeywordsPredicate otherPredicate = (AddressClientContainsKeywordsPredicate) other;
        return keywords.equals(otherPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
