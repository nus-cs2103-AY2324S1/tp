package seedu.address.model.record;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Record} matches any of the keywords given.
 */
public class RecordContainsKeywordsPredicate implements Predicate<Record> {
    private final List<String> keywords;
    public RecordContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }
    @Override
    public boolean test(Record record) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(record.getDateTime().toString(), keyword)
                        ||
                        record.getConditions().stream()
                                .anyMatch(condition -> StringUtil.containsWordIgnoreCase(condition.condition, keyword))
                        ||
                        record.getMedications().stream()
                                .anyMatch(medication -> StringUtil.containsWordIgnoreCase(medication.medication,
                                        keyword)));
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof RecordContainsKeywordsPredicate)) {
            return false;
        }
        RecordContainsKeywordsPredicate otherRecordContainsKeywordsPredicate = (RecordContainsKeywordsPredicate) other;
        return keywords.equals(otherRecordContainsKeywordsPredicate.keywords);
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("keywords", keywords)
                .toString();
    }
}
