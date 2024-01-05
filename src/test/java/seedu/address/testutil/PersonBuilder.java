package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.gradedtest.GradedTest;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TelegramHandle;
import seedu.address.model.person.assignment.AssignmentMap;
import seedu.address.model.session.Session;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@u.nus.edu";
    public static final String DEFAULT_TELEGRAM_HANDLE = "l_dinghan";

    private Name name;
    private Phone phone;
    private Email email;
    private TelegramHandle telegramHandle;
    private Set<Tag> tags;
    private Set<GradedTest> gradedTests;
    private Set<Session> sessions;
    private AssignmentMap assignments;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        telegramHandle = new TelegramHandle(DEFAULT_TELEGRAM_HANDLE);
        tags = new HashSet<>();
        gradedTests = new HashSet<>();
        sessions = new HashSet<>();
        assignments = new AssignmentMap();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        telegramHandle = personToCopy.getTelegramHandle();
        tags = new HashSet<>(personToCopy.getTags());
        assignments = personToCopy.getAllAssignments();
        gradedTests = new HashSet<>(personToCopy.getGradedTest());
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy} but with a new AssignmentMap.
     */
    public PersonBuilder(Person personToCopy, AssignmentMap newAssignments) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        telegramHandle = personToCopy.getTelegramHandle();
        tags = new HashSet<>(personToCopy.getTags());
        assignments = newAssignments;
        gradedTests = new HashSet<>(personToCopy.getGradedTest());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code gradedTest} into a {@code Set<GradedTest>} and set it to
     * the {@code GradedTest} that we are building.
     */
    public PersonBuilder withGradedTest(String ... gradedTests) {
        this.gradedTests = SampleDataUtil.getGradedTestSet(gradedTests);
        return this;
    }


    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withTelegramHandle(String telegramHandle) {
        this.telegramHandle = new TelegramHandle(telegramHandle);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, telegramHandle, tags, assignments, gradedTests);
    }

}
