package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Contact;
import seedu.address.model.person.Course;
import seedu.address.model.person.Favourite;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Role;
// import seedu.address.model.person.Tutorial;

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
        descriptor.setRoles(person.getRoles());
        descriptor.setContacts(person.getContacts());
        descriptor.setCourses(person.getCourses());
        descriptor.setTutorials(person.getTutorials());
        descriptor.setFavourite(person.getFavourite());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Parses the {@code roles} into a {@code Set<Role>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withRoles(String... roles) {
        Set<Role> roleSet = Stream.of(roles).map(Role::new).collect(Collectors.toSet());
        descriptor.setRoles(roleSet);
        return this;
    }

    /**
     * Parses the {@code contacts} into a {@code Set<Contact>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withContacts(String... contacts) {
        Set<Contact> contactSet = Stream.of(contacts).map(Contact::new).collect(Collectors.toSet());
        descriptor.setContacts(contactSet);
        return this;
    }

    /**
     * Parses the {@code courses} into a {@code Set<Course>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withCourses(String... courses) {
        Set<Course> courseSet = Stream.of(courses).map(Course::new).collect(Collectors.toSet());
        descriptor.setCourses(courseSet);
        return this;
    }

    /**
     * Sets the {@code Favourite} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withFavourite(boolean isFavourite) {
        Favourite favourite = new Favourite(isFavourite);
        descriptor.setFavourite(favourite);
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
