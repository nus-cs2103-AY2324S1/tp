package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.affiliation.Affiliation;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.person.ShiftDays;
import seedu.address.model.person.Staff;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Person person) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setAffiliations(person.getAffiliations());
        descriptor.setAffiliationHistory(person.getAffiliationHistory());
        if (person instanceof Staff) {
            descriptor.setShiftDays(((Staff) person).getShiftDays());
        }
        if (person instanceof Doctor) {
            descriptor.setSpecialisations(((Doctor) person).getSpecialisations());
        }
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Role} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withRole(String role) {
        descriptor.setRole(new Role(role));
        return this;
    }

    /**
     * Parses the {@code affiliations} into a {@code Set<Affiliation>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     * @param affiliations The affiliations to set.
     */
    public EditPersonDescriptorBuilder withAffiliations(String... affiliations) {
        Set<Affiliation> affiliationSet = Stream.of(affiliations).map(Affiliation::new).collect(Collectors.toSet());
        descriptor.setAffiliations(affiliationSet);
        return this;
    }

    /**
     * Parses the {@code affiliationHistory} into a {@code Set<Affiliation>} and
     * set it to the {@code EditPersonDescriptor}
     * @param affiliationHistory The affiliation history to set.
     * @return EditPersonDescriptorBuilder
     */
    public EditPersonDescriptorBuilder withAffiliationHistory(String... affiliationHistory) {
        Set<Affiliation> affiliationSet = Stream.of(affiliationHistory)
            .map(Affiliation::new).collect(Collectors.toSet());
        descriptor.setAffiliationHistory(affiliationSet);
        return this;
    }

    /**
     * Sets the {@code ShiftDays} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withShiftDays(Set<Integer> shiftDays) {
        descriptor.setShiftDays(new ShiftDays(shiftDays));
        return this;
    }

    /**
     * Sets the {@code Specialisation} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withSpecialisations(Set<String> specialisations) {
        descriptor.setSpecialisations(SampleDataUtil.getSpecialisationSet(specialisations));
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
