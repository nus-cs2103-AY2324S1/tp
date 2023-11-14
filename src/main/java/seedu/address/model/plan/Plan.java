package seedu.address.model.plan;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.person.Person;

/**
 * Represents a Plan in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Plan {
    /** A brief description of this plan. */
    private final PlanName planName;
    /** The date and time of this plan. */
    private final PlanDateTime dateTime;
    /** The friend this plan involves. */
    private Person friend;
    private Boolean isComplete = false;

    /**
     * Class constructor for Plan.
     * @param planName A brief description of the plan.
     * @param dateTime A LocalDateTIme object of this plan.
     * @param friend The friend this plan involves.
     */
    public Plan(PlanName planName, PlanDateTime dateTime, Person friend) {
        requireAllNonNull(planName, dateTime, friend);
        this.planName = planName;
        this.dateTime = dateTime;
        this.friend = friend;
    }

    /**
     * Class constructor for Plan.
     * @param planName A brief description of the plan.
     * @param dateTime A LocalDateTIme object of this plan.
     * @param friend The friend this plan involves.
     * @param isComplete The completion status of the plan.
     */
    public Plan(PlanName planName, PlanDateTime dateTime, Person friend, Boolean isComplete) {
        requireAllNonNull(planName, dateTime, friend, isComplete);
        this.planName = planName;
        this.dateTime = dateTime;
        this.friend = friend;
        this.isComplete = isComplete;
    }

    public PlanName getPlanName() {
        return planName;
    }

    public PlanDateTime getPlanDateTime() {
        return dateTime;
    }

    public Person getPlanFriend() {
        return friend;
    }
    public Boolean getPlanComplete() {
        return isComplete;
    }

    public void setFriend(Person friend) {
        this.friend = friend;
    }

    /**
     * Returns true if both plans have the same name.
     * This defines a weaker notion of equality between two plans.
     */
    public boolean isSamePlan(Plan otherPlan) {
        if (otherPlan == this) {
            return true;
        }

        return otherPlan != null
                && otherPlan.getPlanName().equals(getPlanName())
                && otherPlan.getPlanFriend().equals(getPlanFriend())
                && otherPlan.getPlanDateTime().equals(getPlanDateTime());
    }

    /**
     * Returns Yes if this Plan is completed, and No otherwise.
     */
    public String getCompletionString() {
        if (isComplete) {
            return "Yes";
        } else {
            return "No";
        }
    }

    /**
     * Returns true if both plans have the same name, dateTime and friend fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Plan)) {
            return false;
        }

        Plan otherPlan = (Plan) other;
        return planName.equals(otherPlan.planName)
                && dateTime.equals(otherPlan.dateTime)
                && friend.equals(otherPlan.friend);
    }
    /**
     * Returns string of format [PLAN_NAME with FRIEND_NAME at DATE_TIME]
     */
    @Override
    public String toString() {
        String friendName = friend.getName().toString();
        String dateTimeString = this.dateTime.toString();
        String planNameString = this.planName.toString();
        return '[' + planNameString + " with " + friendName + " at " + dateTimeString + ']';
    }

    /**
     * Marks this plan as completed.
     */
    public void setCompleted() {
        this.isComplete = true;
    }

    /**
     * Marks this plan as uncompleted.
     */
    public void setUncompleted() {
        this.isComplete = false;
    }
}
