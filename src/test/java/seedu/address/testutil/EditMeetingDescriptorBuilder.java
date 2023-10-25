package seedu.address.testutil;

import static seedu.address.logic.parser.ParserUtil.FORMAT;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditMeetingCommand.EditMeetingDescriptor;
import seedu.address.model.meeting.Location;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.Title;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditMeetingDescriptor objects.
 */
public class EditMeetingDescriptorBuilder {

    private EditMeetingDescriptor descriptor;

    public EditMeetingDescriptorBuilder() {
        descriptor = new EditMeetingDescriptor();
    }

    public EditMeetingDescriptorBuilder(EditMeetingDescriptor descriptor) {
        this.descriptor = new EditMeetingDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditMeetingDescriptor} with fields containing {@code meeting}'s details
     */
    public EditMeetingDescriptorBuilder(Meeting meeting) {
        descriptor = new EditMeetingDescriptor();
        descriptor.setTitle(meeting.getTitle());
        descriptor.setLocation(meeting.getLocation());
        descriptor.setStart(meeting.getStart());
        descriptor.setEnd(meeting.getEnd());
        descriptor.setTags(meeting.getTags());
    }

    /**
     * Sets the {@code Title} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withTitle(String title) {
        descriptor.setTitle(new Title(title));
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withLocation(String location) {
        descriptor.setLocation(new Location(location));
        return this;
    }

    /**
     * Parses the {@code Start} into a {@code LocalDateTime} and set it to the
     * {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withStart(String start) {
        descriptor.setStart(LocalDateTime.parse(start, FORMAT));
        return this;
    }

    /**
     * Parses the {@code End} into a {@code LocalDateTime} and set it to the
     * {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withEnd(String end) {
        descriptor.setEnd(LocalDateTime.parse(end, FORMAT));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditMeetingDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::of).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditMeetingDescriptor build() {
        return descriptor;
    }
}
