package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.edit.EditClientCommand;
import seedu.address.model.client.Client;
import seedu.address.model.client.ClientRoles;
import seedu.address.model.client.Document;
import seedu.address.model.commons.Name;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Phone;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditClientDescriptorBuilder {

    private final EditClientCommand.EditClientDescriptor descriptor;

    public EditClientDescriptorBuilder() {
        descriptor = new EditClientCommand.EditClientDescriptor();
    }

    public EditClientDescriptorBuilder(EditClientCommand.EditClientDescriptor descriptor) {
        this.descriptor = new EditClientCommand.EditClientDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditClientDescriptor} with fields containing {@code client}'s details.
     */
    public EditClientDescriptorBuilder(Client client) {
        descriptor = new EditClientCommand.EditClientDescriptor();
        descriptor.setName(client.getName());
        descriptor.setPhone(client.getPhone());
        descriptor.setEmail(client.getEmail());
        descriptor.setAddress(client.getAddress());
        descriptor.setRole(client.getRole());
        descriptor.setProjects(client.getProjects());
        descriptor.setDocument(client.getDocument());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }


    /*
    public EditDeveloperDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }
    */
    /**
     * Sets the {@code Projects} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditClientDescriptorBuilder withProjects(String... projects) {
        Set<String> projectNames = Stream.of(projects).collect(Collectors.toSet());
        descriptor.setProjects(projectNames);
        return this;
    }


    /**
     * Sets the {@code Role} of the {@code EditPersonDescriptor} that we are building.
     * @param role
     * @return
     */
    public EditClientDescriptorBuilder withRole(String role) {
        descriptor.setRole(new ClientRoles(role));
        return this;
    }

    /**
     * Sets the {@code Document} of the {@code EditPersonDescriptor} that we are building.
     * @param document
     * @return
     */
    public EditClientDescriptorBuilder withDocument(String document) {
        descriptor.setDocument(new Document(document));
        return this;
    }

    public EditClientCommand.EditClientDescriptor build() {
        return descriptor;
    }
}
