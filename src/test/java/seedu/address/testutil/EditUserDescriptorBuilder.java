package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.edit.EditUserDescriptor;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.timetable.DatedEvent;
import seedu.address.model.person.timetable.Schedule;
import seedu.address.model.tag.Tag;
import seedu.address.model.user.User;

/**
 * A utility class to help with building EditUserDescriptorBuilder objects.
 */
public class EditUserDescriptorBuilder {

    private final EditUserDescriptor descriptor;

    public EditUserDescriptorBuilder() {
        descriptor = new EditUserDescriptor();
    }

    public EditUserDescriptorBuilder(EditUserDescriptor descriptor) {
        this.descriptor = new EditUserDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditUserDescriptorBuilder} with fields containing {@code user}'s details
     */
    public EditUserDescriptorBuilder(User user) {
        descriptor = new EditUserDescriptor();
        descriptor.setName(user.getName());
        descriptor.setPhone(user.getPhone());
        descriptor.setEmail(user.getEmail());
        descriptor.setAddress(user.getAddress());
        descriptor.setBirthday(user.getBirthday());
        descriptor.setSchedule(user.getSchedule());
        descriptor.setTags(user.getTags());
        descriptor.setDatedEvents(user.getDatedEvents());
    }

    /**
     * Sets the {@code Name} of the {@code EditUserDescriptorBuilder} that we are building.
     */
    public EditUserDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditUserDescriptorBuilder} that we are building.
     */
    public EditUserDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditUserDescriptorBuilder} that we are building.
     */
    public EditUserDescriptorBuilder withPhone(Phone phone) {
        descriptor.setPhone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditUserDescriptorBuilder} that we are building.
     */
    public EditUserDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditUserDescriptorBuilder} that we are building.
     */
    public EditUserDescriptorBuilder withEmail(Email email) {
        descriptor.setEmail(email);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditUserDescriptorBuilder} that we are building.
     */
    public EditUserDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code Birthday} of the {@code EditUserDescriptorBuilder} that we are building.
     */
    public EditUserDescriptorBuilder withBirthday(String birthday) {
        descriptor.setBirthday(new Birthday(birthday));
        return this;
    }

    /**
     * Sets the {@code Schedule} of the {@code EditUserDescriptorBuilder} that we are building.
     */
    public EditUserDescriptorBuilder withSchedule() {
        descriptor.setSchedule(new Schedule());
        return this;
    }

    /**
     * Sets the {@code Schedule} of the {@code EditUserDescriptorBuilder} that we are building.
     */
    public EditUserDescriptorBuilder withSchedule(Schedule schedule) {
        descriptor.setSchedule(schedule);
        return this;
    }

    /**
     * Sets the {@code Schedule} of the {@code EditUserDescriptorBuilder} that we are building.
     */
    public EditUserDescriptorBuilder withDatedEvents() {
        descriptor.setDatedEvents(new ArrayList<DatedEvent>());
        return this;
    }

    /**
     * Sets the {@code Schedule} of the {@code EditUserDescriptorBuilder} that we are building.
     */
    public EditUserDescriptorBuilder withDatedEvents(ArrayList<DatedEvent> datedEvents) {
        descriptor.setDatedEvents(datedEvents);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditUserDescriptorBuilder}
     * that we are building.
     */
    public EditUserDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditUserDescriptor build() {
        return descriptor;
    }
}

