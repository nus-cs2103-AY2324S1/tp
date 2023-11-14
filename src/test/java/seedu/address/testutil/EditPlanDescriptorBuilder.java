package seedu.address.testutil;

import seedu.address.logic.commands.EditPlanCommand.EditPlanDescriptor;
import seedu.address.model.person.Name;
import seedu.address.model.plan.Plan;
import seedu.address.model.plan.PlanDateTime;
import seedu.address.model.plan.PlanName;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPlanDescriptorBuilder {

    private EditPlanDescriptor descriptor;

    public EditPlanDescriptorBuilder() {
        descriptor = new EditPlanDescriptor();
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPlanDescriptorBuilder(Plan plan) {
        descriptor = new EditPlanDescriptor();
        descriptor.setPlanName(plan.getPlanName());
        descriptor.setPlanDateTime(plan.getPlanDateTime());
        descriptor.setPlanFriendName(plan.getPlanFriend().getName());
    }

    /**
     * Sets the {@code PlanName} of the {@code EditPlanDescriptor} that we are building.
     */
    public EditPlanDescriptorBuilder withPlanName(String name) {
        descriptor.setPlanName(new PlanName(name));
        return this;
    }

    /**
     * Sets the {@code PlanDateTime} of the {@code EditPlanDescriptor} that we are building.
     */
    public EditPlanDescriptorBuilder withPlanDateTime(String dateTime) {
        descriptor.setPlanDateTime(new PlanDateTime(dateTime));
        return this;
    }

    /**
     * Sets the {@code PlanFriendName} of the {@code EditPlanDescriptor} that we are building.
     */
    public EditPlanDescriptorBuilder withPlanFriendName(String friendName) {
        descriptor.setPlanFriendName(new Name(friendName));
        return this;
    }

    public EditPlanDescriptor build() {
        return descriptor;
    }
}
