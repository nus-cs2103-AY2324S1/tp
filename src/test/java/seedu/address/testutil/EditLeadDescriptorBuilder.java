package seedu.address.testutil;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditLeadDescriptor;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.KeyMilestone;
import seedu.address.model.person.Lead;
import seedu.address.model.person.MeetingTime;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditLeadDescriptor objects.
 *
 */
public class EditLeadDescriptorBuilder extends EditPersonDescriptorBuilder {
    // not extending from EditPersonDescriptorBuilder as what we to change is leadDescriptor instance instead of
    // personDescriptor

    private EditLeadDescriptor leadDescriptor;

    public EditLeadDescriptorBuilder() {
        this.leadDescriptor = new EditLeadDescriptor();
    }


    public EditLeadDescriptorBuilder(EditLeadDescriptor descriptor) {
        this.leadDescriptor = new EditLeadDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditLeadDescriptor} with fields containing {@code lead}'s details
     */
    public EditLeadDescriptorBuilder(Lead lead) {
        leadDescriptor = new EditLeadDescriptor();
        leadDescriptor.setName(lead.getName());
        leadDescriptor.setPhone(lead.getPhone());
        leadDescriptor.setEmail(lead.getEmail());
        leadDescriptor.setAddress(lead.getAddress());
        leadDescriptor.setKeyMilestone(lead.getKeyMilestone());
        leadDescriptor.setMeetingTime(lead.getMeetingTime());
        leadDescriptor.setTags(lead.getTags());
    }


    /**
     * Sets the {@code Name} of the {@code EditLeadDescriptor} that we are building.
     */
    public EditLeadDescriptorBuilder withName(String name) {
        leadDescriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditLeadDescriptor} that we are building.
     */
    public EditLeadDescriptorBuilder withPhone(String phone) {
        leadDescriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditLeadDescriptor} that we are building.
     */
    public EditLeadDescriptorBuilder withEmail(String email) {
        leadDescriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditLeadDescriptor} that we are building.
     */
    public EditLeadDescriptorBuilder withAddress(String address) {
        leadDescriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code KeyMilestone} of the {@code EditLeadDescriptor} that we are building.
     */
    public EditLeadDescriptorBuilder withKeyMilestone(String keyMilestone) {
        leadDescriptor.setKeyMilestone(new KeyMilestone(keyMilestone));
        return this;
    }


    /**
     * Sets the {@code MeetingTime} of the {@code EditLeadDescriptor} that we are building.
     */
    public EditLeadDescriptorBuilder withMeetingTime(String meetingTime) {
        leadDescriptor.setMeetingTime(Optional.of(new MeetingTime(meetingTime)));
        return this;
    }


    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditLeadDescriptor}
     * that we are building.
     */
    public EditLeadDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        leadDescriptor.setTags(tagSet);
        return this;
    }

    public EditLeadDescriptor build() {
        return leadDescriptor;
    }
}
