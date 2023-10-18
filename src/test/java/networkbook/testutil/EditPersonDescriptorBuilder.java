package networkbook.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import networkbook.logic.commands.EditCommand;
import networkbook.model.person.Course;
import networkbook.model.person.Email;
import networkbook.model.person.GraduatingYear;
import networkbook.model.person.Link;
import networkbook.model.person.Name;
import networkbook.model.person.Person;
import networkbook.model.person.Phone;
import networkbook.model.person.Priority;
import networkbook.model.person.Specialisation;
import networkbook.model.tag.Tag;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditCommand.EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditCommand.EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditCommand.EditPersonDescriptor descriptor) {
        this.descriptor = new EditCommand.EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Person person) {
        descriptor = new EditCommand.EditPersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setPhones(person.getPhones());
        descriptor.setEmails(person.getEmails());
        descriptor.setLinks(person.getLinks());
        person.getGraduatingYear().ifPresent((GraduatingYear g) -> descriptor.setGraduatingYear(g));
        person.getCourse().ifPresent((Course c) -> descriptor.setCourse(c));
        person.getSpecialisation().ifPresent((Specialisation s) -> descriptor.setSpecialisation(s));
        descriptor.setTags(person.getTags());
        person.getPriority().ifPresent((Priority p) -> descriptor.setPriority(p));
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
        descriptor.addPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.addEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Link} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withLink(String link) {
        descriptor.addLink(new Link(link));
        return this;
    }

    /**
     * Sets the {@code GraduatingYear} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withGraduatingYear(String graduatingYear) {
        descriptor.setGraduatingYear(new GraduatingYear(graduatingYear));
        return this;
    }

    /**
     * Sets the {@code Course} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withCourse(String course) {
        descriptor.setCourse(new Course(course));
        return this;
    }

    /**
     * Sets the {@code Specialisation} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withSpecialisation(String specialisation) {
        descriptor.setSpecialisation(new Specialisation(specialisation));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPriority(String priority) {
        descriptor.setPriority(new Priority(priority));
        return this;
    }

    public EditCommand.EditPersonDescriptor build() {
        return descriptor;
    }
}
