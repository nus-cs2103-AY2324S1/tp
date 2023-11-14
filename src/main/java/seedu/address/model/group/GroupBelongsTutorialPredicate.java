package seedu.address.model.group;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Group}'s {@code Tutorial} matches the tutorial given.
 */
public class GroupBelongsTutorialPredicate implements Predicate<Group> {
    private final String tutorial;

    public GroupBelongsTutorialPredicate(String tutorial) {
        this.tutorial = tutorial;
    }

    @Override
    public boolean test(Group group) {
        String grpTutorial = group.getTutorial().value;
        return StringUtil.containsTutorial(grpTutorial, tutorial);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GroupBelongsTutorialPredicate)) {
            return false;
        }

        GroupBelongsTutorialPredicate otherGroupBelongsTutorialPredicate = (GroupBelongsTutorialPredicate) other;
        return tutorial.equals(otherGroupBelongsTutorialPredicate.tutorial);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("tutorial", tutorial).toString();
    }
}
