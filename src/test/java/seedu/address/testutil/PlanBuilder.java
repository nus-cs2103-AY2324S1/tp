package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.ALICE;

import seedu.address.model.person.Person;
import seedu.address.model.plan.Plan;
import seedu.address.model.plan.PlanDateTime;
import seedu.address.model.plan.PlanName;

/**
 * A utility class to help with building Plan objects.
 */
public class PlanBuilder {

    public static final String DEFAULT_PLAN_NAME = "Meeting";
    public static final String DEFAULT_DATE_TIME = "2025-10-23-12:00";
    public static final Person DEFAULT_FRIEND = ALICE;

    private PlanName planName;
    private PlanDateTime planDateTime;
    private Person friend;
    private Boolean isComplete = false;

    /**
     * Creates a {@code PlanBuilder} with the default details.
     */
    public PlanBuilder() {
        planName = new PlanName(DEFAULT_PLAN_NAME);
        planDateTime = new PlanDateTime(DEFAULT_DATE_TIME);
        friend = DEFAULT_FRIEND;
    }

    /**
     * Initializes the PlanBuilder with the data of {@code planToCopy}.
     */
    public PlanBuilder(Plan plan) {
        planName = plan.getPlanName();
        planDateTime = plan.getPlanDateTime();
        friend = plan.getPlanFriend();
        isComplete = plan.getPlanComplete();
    }

    /**
     * Sets the {@code PlanName} of the {@code Plan} that we are building.
     */
    public PlanBuilder withPlanName(String planName) {
        this.planName = new PlanName(planName);
        return this;
    }

    /**
     * Sets the {@code PlanDateTime} of the {@code Plan} that we are building.
     */
    public PlanBuilder withPlanDateTime(String planDateTime) {
        this.planDateTime = new PlanDateTime(planDateTime);
        return this;
    }

    /**
     * Sets the {@code PlanFriend} of the {@code Plan} that we are building.
     */
    public PlanBuilder withPlanFriend(Person friend) {
        this.friend = friend;
        return this;
    }

    public Plan build() {
        return new Plan(planName, planDateTime, friend, isComplete);
    }

}
