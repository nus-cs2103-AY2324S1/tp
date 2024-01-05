package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.RemoveFromConsultCommand.RemoveFromConsultationDescriptor;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.person.Name;

/**
 * A utility class to help with building RemoveFromConsultationDescriptor objects.
 */
public class RemoveFromConsultationDescriptorBuilder {
    private RemoveFromConsultationDescriptor descriptor;

    public RemoveFromConsultationDescriptorBuilder() {
        descriptor = new RemoveFromConsultationDescriptor();
    }

    /**
     * Returns an {@code RemoveFromConsultationDescriptor} with fields containing {@code consultation}'s details
     */
    public RemoveFromConsultationDescriptorBuilder(Consultation consultation) {
        descriptor = new RemoveFromConsultationDescriptor();
        descriptor.setNames(consultation.getStudentsNames());
    }

    /**
     * Parses the {@code names} into a {@code Set<Name>} and set it to the
     * {@code RemoveFromConsultationDescriptorBuilder} that we are building.
     */
    public RemoveFromConsultationDescriptorBuilder withNames(String... names) {
        Set<Name> nameSet = Stream.of(names).map(Name::new).collect(Collectors.toSet());
        descriptor.setNames(nameSet);
        return this;
    }

    public RemoveFromConsultationDescriptor build() {
        return descriptor;
    }
}
