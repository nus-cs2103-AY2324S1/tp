package seedu.address.testutil;

import static seedu.address.testutil.TypicalDatedEvents.NORMAL_DATEDEVENTS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.timetable.DatedEvent;
import seedu.address.model.person.timetable.Schedule;
import seedu.address.model.tag.Tag;
import seedu.address.model.user.User;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building User objects.
 */
public class UserBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_BIRTHDAY = "2000-01-01";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Birthday birthday;
    private Set<Tag> tags;
    private Schedule schedule;
    private ArrayList<DatedEvent> datedEvents;

    /**
     * Sets the {@code datedEvents} of the {@code User} that we are building.
     */
    public UserBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        birthday = new Birthday(DEFAULT_BIRTHDAY);
        schedule = new Schedule();
        tags = new HashSet<>();
        datedEvents = NORMAL_DATEDEVENTS;
    }

    /**
     * Initializes the UserBuilder with the data of {@code userToCopy}.
     */
    public UserBuilder(User userToCopy) {
        name = userToCopy.getName();
        phone = userToCopy.getPhone();
        email = userToCopy.getEmail();
        address = userToCopy.getAddress();
        birthday = userToCopy.getBirthday();
        schedule = userToCopy.getSchedule();
        tags = new HashSet<>(userToCopy.getTags());
        datedEvents = new ArrayList<>(userToCopy.getDatedEvents());
    }

    /**
     * Sets the {@code Name} of the {@code User} that we are building.
     */
    public UserBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code User} that we are building.
     */
    public UserBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Schedule} of the {@code User} that we are building.
     */
    public UserBuilder withSchedule() {
        this.schedule = new Schedule();
        return this;
    }

    /**
     * Sets the {@code Schedule} of the {@code User} that we are building.
     */
    public UserBuilder withSchedule(Schedule schedule) {
        this.schedule = schedule;
        return this;
    }

    /**
     * Sets the {@code datedEvents} of the {@code User} that we are building.
     */
    public UserBuilder withDatedEvents() {
        this.datedEvents = new ArrayList<DatedEvent>();
        return this;
    }

    /**
     * Sets the {@code datedEvents} of the {@code User} that we are building.
     */
    public UserBuilder withDatedEvents(ArrayList<DatedEvent> datedEvents) {
        this.datedEvents = datedEvents;
        return this;
    }


    /**
     * Sets the {@code Address} of the {@code User} that we are building.
     */
    public UserBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code User} that we are building.
     */
    public UserBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code User} that we are building.
     */
    public UserBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Birthday} of the {@code User} that we are building.
     */
    public UserBuilder withBirthday(String birthday) {
        this.birthday = new Birthday(birthday);
        return this;
    }

    public User build() {
        return new User(name, phone, email, address, birthday, schedule, tags, datedEvents);
    }

}
