
package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class AbsentFromTutorialNumPredicate implements Predicate<Person> {
    private final Index index;
    private final Tag tag;

    /**
     * Constructor a {@code AbsentFromTutorialNumPredicate} with index and tutorial group.
     *
     * @param index Tutorial number to get the attendance list for.
     * @param tag Tutorial group to get the attendance list for.
     */
    public AbsentFromTutorialNumPredicate(Index index, Tag tag) {
        this.index = index;
        this.tag = tag;
    }

    @Override
    public boolean test(Person person) {
        if (index.getOneBased() > person.getAttendanceRecords().size()) {
            return false;
        }

        Tag placeholder = new Tag("PLACEHOLDER");
        if (this.tag.equals(placeholder)) {
            return !person.getAttendanceRecords().get(index.getZeroBased()).isPresent();
        } else {
            return person.getTags().stream()
                    .anyMatch(personTag -> StringUtil.containsWordIgnoreCase(personTag.getTagName(), tag.getTagName()))
                    && !person.getAttendanceRecords().get(index.getZeroBased()).isPresent();
        }

    }
}
