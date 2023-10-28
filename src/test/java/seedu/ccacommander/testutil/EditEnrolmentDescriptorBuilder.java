package seedu.ccacommander.testutil;

import seedu.ccacommander.logic.commands.EditEnrolmentCommand.EditEnrolmentDescriptor;
import seedu.ccacommander.model.attendance.Attendance;
import seedu.ccacommander.model.attendance.Hours;
import seedu.ccacommander.model.attendance.Remark;
import seedu.ccacommander.model.shared.Name;

/**
 * A utility class to help with building EditEnrolmentDescriptor objects.
 */
public class EditEnrolmentDescriptorBuilder {

    private EditEnrolmentDescriptor descriptor;

    public EditEnrolmentDescriptorBuilder() {
        descriptor = new EditEnrolmentDescriptor();
    }

    public EditEnrolmentDescriptorBuilder(EditEnrolmentDescriptor descriptor) {
        this.descriptor = new EditEnrolmentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditEnrolmentDescriptor} with fields containing {@code enrolment}'s details
     */
    public EditEnrolmentDescriptorBuilder(Attendance enrolment) {
        descriptor = new EditEnrolmentDescriptor();
        descriptor.setMemberName(enrolment.getMemberName());
        descriptor.setEventName(enrolment.getEventName());
        descriptor.setHours(enrolment.getHours());
        descriptor.setRemark(enrolment.getRemark());
    }

    /**
     * Sets the {@code memberName} of the {@code EditEnrolmentDescriptor} that we are building.
     */
    public EditEnrolmentDescriptorBuilder withMemberName(String memberName) {
        descriptor.setMemberName(new Name(memberName));
        return this;
    }

    /**
     * Sets the {@code eventName} of the {@code EditEnrolmentDescriptor} that we are building.
     */
    public EditEnrolmentDescriptorBuilder withEventName(String eventName) {
        descriptor.setEventName(new Name(eventName));
        return this;
    }

    /**
     * Sets the {@code Hours} of the {@code EditEnrolmentDescriptor} that we are building.
     */
    public EditEnrolmentDescriptorBuilder withHours(String hours) {
        descriptor.setHours(new Hours(hours));
        return this;
    }

    /**
     * Sets the {@code Renark} of the {@code EditEnrolmentDescriptor} that we are building.
     */
    public EditEnrolmentDescriptorBuilder withRemark(String remark) {
        descriptor.setRemark(new Remark(remark));
        return this;
    }

    public EditEnrolmentDescriptor build() {
        return descriptor;
    }
}
