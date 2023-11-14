package seedu.address.model.plan;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;


/**
 * Tests that a {@code Plan} has the input Person object in it.
 */
public class PlanContainsFriendPredicate implements Predicate<Plan> {

    private final Person friend;

    public PlanContainsFriendPredicate(Person friend) {
        this.friend = friend;
    }

    @Override
    public boolean test(Plan plan) {
        return plan.getPlanFriend().equals(this.friend);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof seedu.address.model.plan.PlanContainsFriendPredicate)) {
            return false;
        }

        seedu.address.model.plan.PlanContainsFriendPredicate planContainsFriendPredicate =
                (seedu.address.model.plan.PlanContainsFriendPredicate) other;
        return friend.equals(planContainsFriendPredicate.friend);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("friend", friend).toString();
    }

}
