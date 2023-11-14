package seedu.address.model.tutorial;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Tutorial} matches any of the slots given.
 */
public class TutorialContainsSlotsPredicate implements Predicate<Person> {
    private final List<String> slots;

    public TutorialContainsSlotsPredicate(List<String> slots) {
        this.slots = slots;
    }

    @Override
    public boolean test(Person person) {
        Set<Tutorial> tutorialSet = person.getTutorials();
        StringBuilder result = new StringBuilder();
        for (Tutorial tut : tutorialSet) {
            result.append(tut.value).append(" ");
        }
        return slots.stream()
                .anyMatch(slot -> StringUtil.containsTutorial(result.toString(), slot));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TutorialContainsSlotsPredicate)) {
            return false;
        }

        TutorialContainsSlotsPredicate otherTutorialContainsSlotsPredicate = (TutorialContainsSlotsPredicate) other;
        return slots.equals(otherTutorialContainsSlotsPredicate.slots);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("slots", slots).toString();
    }
}
