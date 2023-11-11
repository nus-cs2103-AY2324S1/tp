package seedu.application.testutil;

import seedu.application.logic.commands.InterviewEditCommand;
import seedu.application.model.job.interview.Interview;
import seedu.application.model.job.interview.InterviewAddress;
import seedu.application.model.job.interview.InterviewDateTime;
import seedu.application.model.job.interview.InterviewType;

/**
 * A utility class to help with building EditInterviewDescriptor objects.
 */
public class EditInterviewDescriptorBuilder {
    private InterviewEditCommand.EditInterviewDescriptor descriptor;

    public EditInterviewDescriptorBuilder() {
        descriptor = new InterviewEditCommand.EditInterviewDescriptor();
    }

    public EditInterviewDescriptorBuilder(InterviewEditCommand.EditInterviewDescriptor descriptor) {
        this.descriptor = new InterviewEditCommand.EditInterviewDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditInterviewDescriptor} with fields containing {@code interview}'s details
     */
    public EditInterviewDescriptorBuilder(Interview interview) {
        descriptor = new InterviewEditCommand.EditInterviewDescriptor();
        descriptor.setType(interview.getInterviewType());
        descriptor.setDateTime(interview.getInterviewDateTime());
        descriptor.setAddress(interview.getInterviewAddress());
    }

    /**
     * Sets the {@code Type} of the {@code EditInterviewDescriptor} that we are building.
     */
    public EditInterviewDescriptorBuilder withType(String type) {
        descriptor.setType(new InterviewType(type));
        return this;
    }
    /**
     * Sets the {@code DateTime} of the {@code EditInterviewDescriptor} that we are building.
     */
    public EditInterviewDescriptorBuilder withDateTime(String dateTime) {
        descriptor.setDateTime(new InterviewDateTime(dateTime));
        return this;
    }
    /**
     * Sets the {@code Address} of the {@code EditInterviewDescriptor} that we are building.
     */
    public EditInterviewDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new InterviewAddress(address));
        return this;
    }

    public InterviewEditCommand.EditInterviewDescriptor build() {
        return descriptor;
    }
}
