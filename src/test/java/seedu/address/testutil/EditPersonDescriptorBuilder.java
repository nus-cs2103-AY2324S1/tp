package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Description;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Major;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nationality;
import seedu.address.model.person.Person;
import seedu.address.model.person.Year;
import seedu.address.model.socialmedialink.SocialMediaLink;
import seedu.address.model.tutorial.Tutorial;

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
        descriptor.setMajor(person.getMajor());
        descriptor.setYear(person.getYear());
        descriptor.setEmail(person.getEmail());
        descriptor.setDescription(person.getDescription());
        descriptor.setTutorials(person.getTutorials());
        descriptor.setSocialMediaLinks(person.getSocialMediaLinks());
        descriptor.setNationality(person.getNationality());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Major} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withMajor(String major) {
        descriptor.setMajor(new Major(major));
        return this;
    }

    /**
     * Sets the {@code Year} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withYear(String year) {
        descriptor.setYear(new Year(year));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Parses the {@code socialMediaLinks} into a {@code Set<SocialMediaLink>} and set it to the
     * {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withSocialMediaLinks(String... socialMediaLinks) {
        Set<SocialMediaLink> socialmediaSet = Stream.of(socialMediaLinks).map(SocialMediaLink::new)
                .collect(Collectors.toSet());
        descriptor.setSocialMediaLinks(socialmediaSet);
        return this;
    }

    /**
     * Parses the {@code tutorials} into a {@code Set<Tutorial>} and sets it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTutorials(String... tutorials) {
        Set<Tutorial> tutorialSet = Stream.of(tutorials).map(Tutorial::new).collect(Collectors.toSet());
        descriptor.setTutorials(tutorialSet);
        return this;
    }

    /**
     * Sets the {@code Nationality} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withNationality(String nationality) {
        descriptor.setNationality(new Nationality(nationality));
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withGender(String gender) {
        descriptor.setGender(new Gender(gender));
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
