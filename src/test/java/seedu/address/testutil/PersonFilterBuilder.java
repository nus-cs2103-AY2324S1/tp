package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building PersonFilter objects.
 */
public class PersonFilterBuilder {

    private FilterCommand.PersonFilter filter;

    public PersonFilterBuilder() {
        filter = new FilterCommand.PersonFilter();
    }

    public PersonFilterBuilder(FilterCommand.PersonFilter filter) {
        this.filter = new FilterCommand.PersonFilter(filter);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public PersonFilterBuilder(Person person) {
        filter = new FilterCommand.PersonFilter();
        filter.setName(person.getName().fullName);
        filter.setPhone(person.getPhone().value);
        filter.setEmail(person.getEmail().value);
        filter.setAddress(person.getAddress().value);
        filter.setTags(person.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public PersonFilterBuilder withName(String name) {
        filter.setName(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public PersonFilterBuilder withPhone(String phone) {
        filter.setPhone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public PersonFilterBuilder withEmail(String email) {
        filter.setEmail(email);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are building.
     */
    public PersonFilterBuilder withAddress(String address) {
        filter.setAddress(address);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public PersonFilterBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        filter.setTags(tagSet);
        return this;
    }

    public FilterCommand.PersonFilter build() {
        return filter;
    }
}
