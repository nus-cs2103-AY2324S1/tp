package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditPatientCommand.EditPatientDescriptor;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthdate;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.patients.Patient;
import seedu.address.model.tag.Tag;
import seedu.address.model.treatment.TreatmentName;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPatientDescriptorBuilder {

    private EditPatientDescriptor descriptor;

    public EditPatientDescriptorBuilder() {
        descriptor = new EditPatientDescriptor();
    }

    public EditPatientDescriptorBuilder(EditPatientDescriptor descriptor) {
        this.descriptor = new EditPatientDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPatientDescriptor} with fields containing {@code patient}'s details
     */
    public EditPatientDescriptorBuilder(Patient patient) {
        descriptor = new EditPatientDescriptor();
        descriptor.setName(patient.getName());
        descriptor.setPhone(patient.getPhone());
        descriptor.setBirthdate(patient.getBirthdate());
        descriptor.setGender(patient.getGender());
        descriptor.setRemark(patient.getRemark());
        descriptor.setTreatmentName(patient.getTreatmentName());
        descriptor.setAddress(patient.getAddress());
        descriptor.setEmail(patient.getEmail());
        descriptor.setTags(patient.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code birthdate} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withBirthdate(String birthdate) {
        descriptor.setBirthdate(new Birthdate(birthdate));
        return this;
    }

    /**
     * Sets the {@code gender} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withGender(String gender) {
        descriptor.setGender(new Gender(gender));
        return this;
    }

    /**
     * Sets the {@code remark} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withRemark(String remark) {
        descriptor.setRemark(new Remark(remark));
        return this;
    }

    /**
     * Sets the {@code birthdate} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withTreatmentName(String treatmentName) {
        descriptor.setTreatmentName(new TreatmentName(treatmentName));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPatientDescriptor} that we are building.
     */
    public EditPatientDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPatientDescriptor}
     * that we are building.
     */
    public EditPatientDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditPatientDescriptor build() {
        return descriptor;
    }
}
