package seedu.address.testutil;

import java.time.LocalDateTime;

import seedu.address.commons.util.DateTimeParserUtil;
import seedu.address.logic.commands.editcommands.editdescriptors.EditInternshipDescriptor;
import seedu.address.model.company.internship.Internship;
import seedu.address.model.company.internship.InternshipDescription;
import seedu.address.model.company.internship.InternshipInterviewDateTime;
import seedu.address.model.company.internship.InternshipName;

/**
 * A utility class to help with building EditInternshipDescriptor objects.
 */
public class EditInternshipDescriptorBuilder {
    private EditInternshipDescriptor descriptor;

    public EditInternshipDescriptorBuilder() {
        descriptor = new EditInternshipDescriptor();
    }

    public EditInternshipDescriptorBuilder(EditInternshipDescriptor descriptor) {
        this.descriptor = new EditInternshipDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditInternshipDescriptor} with fields containing {@code internship}'s details
     */
    public EditInternshipDescriptorBuilder(Internship internship) {
        descriptor = new EditInternshipDescriptor();
        descriptor.setInternshipName(internship.getInternshipName());
        descriptor.setInternshipDescription(internship.getInternshipDescription());
        if (!internship.getInternshipDateTime().isEmpty()) {
            descriptor.setInternshipDateTime(internship.getInternshipDateTime().get());
        }
    }

    /**
     * Sets the {@code InternshipName} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withInternshipName(String internshipName) {
        descriptor.setInternshipName(new InternshipName(internshipName));
        return this;
    }

    /**
     * Sets the {@code InternshipDescription} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withInternshipDescription(String internshipDescription) {
        descriptor.setInternshipDescription(new InternshipDescription(internshipDescription));
        return this;
    }

    /**
     * Sets the {@code InternshipDateTime} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withInterviewDateTime(String interviewDateTime) {
        String trimmedInterviewDateTime = interviewDateTime.trim();
        LocalDateTime interviewDateTimeLdt = DateTimeParserUtil.parseStringToDateTime(trimmedInterviewDateTime);
        descriptor.setInternshipDateTime(new InternshipInterviewDateTime(interviewDateTimeLdt));
        return this;
    }

    public EditInternshipDescriptor build() {
        return descriptor;
    }
}
