package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.gradedtest.Finals;
import seedu.address.model.gradedtest.GradedTest;
import seedu.address.model.gradedtest.MidTerms;
import seedu.address.model.gradedtest.PracticalExam;
import seedu.address.model.gradedtest.ReadingAssessment1;
import seedu.address.model.gradedtest.ReadingAssessment2;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TelegramHandle;
import seedu.address.model.tag.Tag;

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
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setTelegramHandle(person.getTelegramHandle());
        descriptor.setTags(person.getTags());
        descriptor.setGradedTest(person.getGradedTest());
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
        descriptor.setPhone(new Phone(phone));
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
     * Sets the {@code TelegramHandle} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withTelegramHandle(String telegramHandle) {
        descriptor.setTelegramHandle(new TelegramHandle(telegramHandle));
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
     * Parses the {@code gradedTest} into a {@code Set<GradedTest>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withGradedTest(String... gradedTests) {
        Set<GradedTest> gradedTestSet = Stream.of(gradedTests).map(GradedTest::new).collect(Collectors.toSet());
        descriptor.setGradedTest(gradedTestSet);
        return this;
    }

    /**
     * Sets the Reading Assessment for the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withRA1(String ra1) {
        descriptor.setReadingAssessment1(new ReadingAssessment1(ra1));
        return this;
    }

    /**
     * Sets the Reading Assessment for the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withRA2(String ra2) {
        descriptor.setReadingAssessment2(new ReadingAssessment2(ra2));
        return this;
    }

    /**
     * Sets the MidTerms for the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withMidTerm(String midterms) {
        descriptor.setMidTerms(new MidTerms(midterms));
        return this;
    }

    /**
     * Sets the Finals for the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withFinals(String finals) {
        descriptor.setFinals(new Finals(finals));
        return this;
    }

    /**
     * Sets the Practical Exam for the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPracticalExam(String pe) {
        descriptor.setPracticalExam(new PracticalExam(pe));
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
