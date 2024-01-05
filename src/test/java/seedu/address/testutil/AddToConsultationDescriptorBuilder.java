package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddToConsultCommand.AddToConsultationDescriptor;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.person.Name;

/**
 * A utility class to help with building AddToConsultationDescriptor objects.
 */
public class AddToConsultationDescriptorBuilder {
    private AddToConsultationDescriptor descriptor;

    public AddToConsultationDescriptorBuilder() {
        descriptor = new AddToConsultationDescriptor();
    }

    public AddToConsultationDescriptorBuilder(AddToConsultationDescriptor descriptor) {
        this.descriptor = new AddToConsultationDescriptor(descriptor);
    }

    /**
     * Returns an {@code AddToConsultationDescriptor} with fields containing {@code consultation}'s details
     */
    public AddToConsultationDescriptorBuilder(Consultation consultation) {
        descriptor = new AddToConsultationDescriptor();
        descriptor.setNames(consultation.getStudentsNames());
    }

    /**
     * Parses the {@code names} into a {@code Set<Name>} and set it to the {@code AddToConsultationDescriptor}
     * that we are building.
     */
    public AddToConsultationDescriptorBuilder withNames(String... names) {
        Set<Name> nameSet = Stream.of(names).map(Name::new).collect(Collectors.toSet());
        descriptor.setNames(nameSet);
        return this;
    }

    public AddToConsultationDescriptor build() {
        return descriptor;
    }
}
