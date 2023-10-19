package seedu.address.model.predicate;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Tag} matches the tag given.
 */
public class AbsentFromTutorialPredicate extends SerializablePredicate {
    private final Index index;
    private final Tag tag;

    /**
     * Constructor a {@code AbsentFromTutorialPredicate} with index and tutorial group.
     *
     * @param index Tutorial number to get the attendance list for.
     * @param tag Tutorial group to get the attendance list for.
     */
    public AbsentFromTutorialPredicate(Index index, Tag tag) {
        super(person -> {
            if (index.getOneBased() > person.getAttendanceRecords().size()) {
                return false;
            }

            Tag placeholder = new Tag("PLACEHOLDER");
            if (tag.equals(placeholder)) {
                return !person.getAttendanceRecords().get(index.getZeroBased()).isPresent();
            }

            // Implicit else
            return person.getTags().stream()
                    .anyMatch(personTag -> StringUtil.containsWordIgnoreCase(personTag.getTagName(), tag.getTagName()))
                    && !person.getAttendanceRecords().get(index.getZeroBased()).isPresent();
        });
        this.index = index;
        this.tag = tag;
    }

    @JsonCreator
    public static AbsentFromTutorialPredicate create(@JsonProperty("index") Index index, @JsonProperty("tag") Tag tag) {
        return new AbsentFromTutorialPredicate(index, tag);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AbsentFromTutorialPredicate)) {
            return false;
        }

        AbsentFromTutorialPredicate otherPredicate = (AbsentFromTutorialPredicate) other;
        return index.equals(otherPredicate.index)
                && tag.equals(otherPredicate.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, tag);
    }

    @Override
    public String toString() {
        return "Attendance Filter: " + index + " " + tag.getTagName();
    }
}
