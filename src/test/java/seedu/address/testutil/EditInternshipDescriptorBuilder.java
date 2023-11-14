package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.ModifyCommand.EditInternshipDescriptor;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.Deadline;
import seedu.address.model.internship.Duration;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.Role;
import seedu.address.model.internship.StartDate;
import seedu.address.model.requirement.Requirement;

/**
 * A utility class to help with building EditPersonDescriptor objects.
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
     * Returns an {@code EditInternshipDescriptor} with fields containing {@code internship} details
     */
    public EditInternshipDescriptorBuilder(Internship internship) {
        descriptor = new EditInternshipDescriptor();
        descriptor.setCompanyName(internship.getCompanyName());
        descriptor.setRole(internship.getRole());
        descriptor.setApplicationStatus(internship.getApplicationStatus());
        descriptor.setDeadline(internship.getDeadline());
        descriptor.setStartDate(internship.getStartDate());
        descriptor.setDuration(internship.getDuration());
        descriptor.setRequirements(internship.getRequirements());
    }

    /**
     * Sets the {@code CompanyName} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withCompanyName(String name) {
        descriptor.setCompanyName(new CompanyName(name));
        return this;
    }

    /**
     * Sets the {@code Role} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withRole(String role) {
        descriptor.setRole(new Role(role));
        return this;
    }
    /**
     * Sets the {@code ApplicationStatus} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withApplicationStatus(String applicationStatus) {
        descriptor.setApplicationStatus(new ApplicationStatus(applicationStatus));
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withDeadline(String deadline, String startDate) {
        descriptor.setDeadline(new Deadline(deadline, startDate));
        return this;
    }

    /**
     * Sets the {@code StartDate} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withStartDate(String startDate) {
        descriptor.setStartDate(new StartDate(startDate));
        return this;
    }

    /**
     * Sets the {@code Duration} of the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withDuration(String duration) {
        descriptor.setDuration(new Duration(duration));
        return this;
    }

    /**
     * Parses the {@code requirements} into a {@code Set<Requirement>} and set it to
     * the {@code EditInternshipDescriptor} that we are building.
     */
    public EditInternshipDescriptorBuilder withRequirements(String... requirements) {
        Set<Requirement> requirementSet = Stream.of(requirements).map(Requirement::new).collect(Collectors.toSet());
        descriptor.setRequirements(requirementSet);
        return this;
    }

    public EditInternshipDescriptor build() {
        return descriptor;
    }
}
