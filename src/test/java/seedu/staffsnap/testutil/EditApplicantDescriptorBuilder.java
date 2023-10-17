package seedu.staffsnap.testutil;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.staffsnap.logic.commands.EditCommand.EditApplicantDescriptor;
import seedu.staffsnap.model.applicant.Applicant;
import seedu.staffsnap.model.applicant.Email;
import seedu.staffsnap.model.applicant.Name;
import seedu.staffsnap.model.applicant.Phone;
import seedu.staffsnap.model.applicant.Position;
import seedu.staffsnap.model.interview.Interview;

/**
 * A utility class to help with building EditApplicantDescriptor objects.
 */
public class EditApplicantDescriptorBuilder {

    private EditApplicantDescriptor descriptor;

    public EditApplicantDescriptorBuilder() {
        descriptor = new EditApplicantDescriptor();
    }

    public EditApplicantDescriptorBuilder(EditApplicantDescriptor descriptor) {
        this.descriptor = new EditApplicantDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditApplicantDescriptor} with fields containing {@code applicant}'s details
     */
    public EditApplicantDescriptorBuilder(Applicant applicant) {
        descriptor = new EditApplicantDescriptor();
        descriptor.setName(applicant.getName());
        descriptor.setPhone(applicant.getPhone());
        descriptor.setEmail(applicant.getEmail());
        descriptor.setPosition(applicant.getPosition());
        descriptor.setInterviews(applicant.getInterviews());
    }

    /**
     * Sets the {@code Name} of the {@code EditApplicantDescriptor} that we are building.
     */
    public EditApplicantDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditApplicantDescriptor} that we are building.
     */
    public EditApplicantDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditApplicantDescriptor} that we are building.
     */
    public EditApplicantDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Position} of the {@code EditApplicantDescriptor} that we are building.
     */
    public EditApplicantDescriptorBuilder withPosition(String position) {
        descriptor.setPosition(new Position(position));
        return this;
    }

    /**
     * Parses the {@code interviews} into a {@code List<Interview>} and set it to the {@code EditApplicantDescriptor}
     * that we are building.
     */
    public EditApplicantDescriptorBuilder withInterviews(String... interviews) {
        List<Interview> interviewList = Stream.of(interviews).map(Interview::new).collect(Collectors.toList());
        descriptor.setInterviews(interviewList);
        return this;
    }

    public EditApplicantDescriptor build() {
        return descriptor;
    }
}
