package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.editcommands.editdescriptors.EditCompanyDescriptor;
import seedu.address.model.company.Company;
import seedu.address.model.company.CompanyDescription;
import seedu.address.model.company.CompanyEmail;
import seedu.address.model.company.CompanyName;
import seedu.address.model.company.CompanyPhone;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditCompanyDescriptor objects.
 */
public class EditCompanyDescriptorBuilder {
    private EditCompanyDescriptor descriptor;

    public EditCompanyDescriptorBuilder() {
        descriptor = new EditCompanyDescriptor();
    }

    public EditCompanyDescriptorBuilder(EditCompanyDescriptor descriptor) {
        this.descriptor = new EditCompanyDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditCompanyDescriptor} with fields containing {@code company}'s details
     */
    public EditCompanyDescriptorBuilder(Company company) {
        descriptor = new EditCompanyDescriptor();
        descriptor.setCompanyName(company.getCompanyName());
        descriptor.setCompanyPhone(company.getCompanyPhone());
        descriptor.setCompanyEmail(company.getCompanyEmail());
        descriptor.setDescription(company.getCompanyDescription());
        descriptor.setTags(company.getTags());
    }

    /**
     * Sets the {@code CompanyName} of the {@code EditCompanyDescriptor} that we are building.
     */
    public EditCompanyDescriptorBuilder withCompanyName(String companyName) {
        descriptor.setCompanyName(new CompanyName(companyName));
        return this;
    }

    /**
     * Sets the {@code CompanyPhone} of the {@code EditCompanyDescriptor} that we are building.
     */
    public EditCompanyDescriptorBuilder withCompanyPhone(String companyPhone) {
        descriptor.setCompanyPhone(new CompanyPhone(companyPhone));
        return this;
    }

    /**
     * Sets the {@code CompanyEmail} of the {@code EditCompanyDescriptor} that we are building.
     */
    public EditCompanyDescriptorBuilder withCompanyEmail(String companyEmail) {
        descriptor.setCompanyEmail(new CompanyEmail(companyEmail));
        return this;
    }

    /**
     * Sets the {@code CompanyDescription} of the {@code EditCompanyDescriptor} that we are building.
     */
    public EditCompanyDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new CompanyDescription(description));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditCompanyDescriptor}
     * that we are building.
     */
    public EditCompanyDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditCompanyDescriptor build() {
        return descriptor;
    }
}
