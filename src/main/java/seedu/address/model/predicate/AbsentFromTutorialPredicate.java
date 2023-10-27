package seedu.address.model.predicate;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Attendance;
import seedu.address.model.tag.Tag;
import seedu.address.model.week.Week;

/**
 * Tests that a {@code Person}'s {@code Tag} matches the tag given.
 */
public class AbsentFromTutorialPredicate extends SerializablePredicate {
    private final Week week;
    private final Optional<Tag> tag;

    /**
     * Constructor a {@code AbsentFromTutorialPredicate} with index and tutorial group.
     *
     * @param week Week number to get the attendance list for.
     * @param tag  Tutorial group to get the attendance list for.
     */
    public AbsentFromTutorialPredicate(Week week, Optional<Tag> tag) {
        super(person -> {
            Stream<Attendance> attendanceStream = person.getAttendanceRecords().stream();

            boolean isAbsentForWeek = !attendanceStream.anyMatch(atd -> atd.getWeek().equals(week) && atd.isPresent());

            if (tag.isEmpty()) {
                return isAbsentForWeek;
            }

            // Implicit else
            return person.getTags().stream().anyMatch(personTag ->
                    StringUtil.containsWordIgnoreCase(personTag.getTagName(), tag.get().getTagName()))
                    && isAbsentForWeek;
        });
        this.week = week;
        this.tag = tag;
    }

    @JsonCreator
    public static AbsentFromTutorialPredicate create(@JsonProperty("week") Week week,
                                                     @JsonProperty("tag") Optional<Tag> tag) {
        return new AbsentFromTutorialPredicate(week, tag);
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
        return week.equals(otherPredicate.week) && tag.equals(otherPredicate.tag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(week, tag);
    }

    @Override
    public String toString() {
        return "Attendance Filter: " + week + " " + tag.get().getTagName();
    }
}
