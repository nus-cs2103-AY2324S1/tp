package seedu.address.testutil;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Client;
import seedu.address.model.person.Email;
import seedu.address.model.person.KeyMilestone;
import seedu.address.model.person.Lead;
import seedu.address.model.person.MeetingTime;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_KEY_MILESTONE = "01/12/2023";
    public static final String DEFAULT_MEETING_TIME = "10/10/2023 14:30";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<Tag> tags;
    private KeyMilestone keyMilestone;
    private Optional<MeetingTime> meetingTime;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        meetingTime = Optional.empty();
        keyMilestone = new KeyMilestone(DEFAULT_KEY_MILESTONE);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        meetingTime = personToCopy.getMeetingTime();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Initializes the PersonBuilder with the data of {@code leadToCopy}.
     */
    public PersonBuilder(Lead leadToCopy) {
        name = leadToCopy.getName();
        phone = leadToCopy.getPhone();
        email = leadToCopy.getEmail();
        address = leadToCopy.getAddress();
        keyMilestone = leadToCopy.getKeyMilestone();
        meetingTime = leadToCopy.getMeetingTime();
        tags = new HashSet<>(leadToCopy.getTags());
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
    public PersonBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
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

    /**
     * Sets the {@code KeyMilestone} of the {@code Person} that we are building.
     */
    //temporary fix: buildLead must call keyMilestone
    public PersonBuilder withKeyMilestone(String keyMilestone) {
        this.keyMilestone = new KeyMilestone(keyMilestone);
        return this;
    }
    /**
     * Sets the {@code MeetingTime} of the {@code Person} that we are building.
     */
    public PersonBuilder withMeetingTime(String meetingTime) {
        if (meetingTime == null) {
            this.meetingTime = Optional.empty();
            return this;
        }
        this.meetingTime = Optional.of(new MeetingTime(meetingTime));
        return this;
    }

    public Client buildClient() {
        return new Client(name, phone, email, address, meetingTime, tags);
    }

    public Lead buildLead() {
        return new Lead(name, phone, email, address, keyMilestone, meetingTime, tags);
    }
}
