//@@author devanshubisht
package seedu.codesphere.model.student.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.codesphere.commons.util.StringUtil;
import seedu.codesphere.commons.util.ToStringBuilder;
import seedu.codesphere.model.student.Student;

/**
 * Tests that a {@code Student}'s {@code Name} matches any of the keywords given.
 */
public class TagFilterPredicate implements Predicate<Student> {
    private final List<String> keywords;

    public TagFilterPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Student student) {
        return keywords.stream()
                .allMatch(keyword -> StringUtil.containsWordIgnoreCase(student.getTag().getStringRanking(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagFilterPredicate)) {
            return false;
        }

        TagFilterPredicate otherTagFilterPredicate = (TagFilterPredicate) other;
        return keywords.equals(otherTagFilterPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
