
package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.core.index.Index;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class AbsentFromTutorialNumPredicate implements Predicate<Person> {
    private final Index index;
    private final TutorialGroup tutorialGroup;

    /**
     * Constructor a {@code AbsentFromTutorialNumPredicate} with index and tutorial group.
     *
     * @param index Tutorial number to get the attendance list for.
     * @param tutorialGroup Tutorial group to get the attendance list for.
     */
    public AbsentFromTutorialNumPredicate(Index index, TutorialGroup tutorialGroup) {
        this.index = index;
        this.tutorialGroup = tutorialGroup;
    }

    @Override
    public boolean test(Person person) {
        if (index.getOneBased() > person.getAttendanceRecords().size()) {
            return false;
        }

        TutorialGroup placeholder = new TutorialGroup("PLACEHOLDER");
        if (this.tutorialGroup.equals(placeholder)) {
            return !person.getAttendanceRecords().get(index.getZeroBased()).isPresent();
        } else {
            return person.getTutorialGroup().equals(tutorialGroup)
                    && !person.getAttendanceRecords().get(index.getZeroBased()).isPresent();
        }

    }
}
