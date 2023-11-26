package swe.context.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import swe.context.logic.commands.EditCommand.EditContactDescriptor;
import swe.context.model.alternate.AlternateContact;
import swe.context.model.contact.Contact;
import swe.context.model.contact.Email;
import swe.context.model.contact.Name;
import swe.context.model.contact.Note;
import swe.context.model.contact.Phone;
import swe.context.model.tag.Tag;

/**
 * A utility class to help with building EditContactDescriptor objects.
 */
public class EditContactDescriptorBuilder {

    private EditContactDescriptor descriptor;

    public EditContactDescriptorBuilder() {
        descriptor = new EditContactDescriptor();
    }

    public EditContactDescriptorBuilder(EditContactDescriptor descriptor) {
        this.descriptor = new EditContactDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditContactDescriptor} with fields containing {@code contact}'s details
     */
    public EditContactDescriptorBuilder(Contact contact) {
        descriptor = new EditContactDescriptor();
        descriptor.setName(contact.getName());
        descriptor.setPhone(contact.getPhone());
        descriptor.setEmail(contact.getEmail());
        descriptor.setNote(contact.getNote());
        descriptor.setTags(contact.getTags());
        descriptor.setAlternateContacts(contact.getAlternates());
    }

    /**
     * Sets the {@code Name} of the {@code EditContactDescriptor} that we are building.
     */
    public EditContactDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditContactDescriptor} that we are building.
     */
    public EditContactDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditContactDescriptor} that we are building.
     */
    public EditContactDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the Note of the EditContactDescriptor being built.
     */
    public EditContactDescriptorBuilder withNote(String _note) {
        descriptor.setNote(new Note(_note));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditContactDescriptor}
     * that we are building.
     */
    public EditContactDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Parses the {@code alternateContacts} into a {@code Set<AlternateContacts>}
     * and set it to the {@code EditContactDescriptor} that we are building.
     */
    public EditContactDescriptorBuilder withAlternateContacts(String... alternateContacts) {
        Set<AlternateContact> alternateContactSet = Stream.of(alternateContacts).map(AlternateContact::new)
                .collect(Collectors.toSet());
        descriptor.setAlternateContacts(alternateContactSet);
        return this;
    }

    public EditContactDescriptor build() {
        return descriptor;
    }
}
