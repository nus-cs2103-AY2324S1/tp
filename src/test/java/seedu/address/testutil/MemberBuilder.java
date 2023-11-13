package seedu.address.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.person.Member;
import seedu.address.model.person.fields.Email;
import seedu.address.model.person.fields.Name;
import seedu.address.model.person.fields.Phone;
import seedu.address.model.person.fields.Telegram;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Member objects.
 */
public class MemberBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_TELEGRAM = "@amybee";

    private Name name;
    private Phone phone;
    private Email email;
    private Telegram telegram;
    private Set<Tag> tags;
    private final List<Task> tasks;

    /**
     * Creates a {@code MemberBuilder} with the default details.
     */
    public MemberBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        telegram = new Telegram(DEFAULT_TELEGRAM);
        tags = new HashSet<>();
        tasks = new ArrayList<>();
    }

    /**
     * Initializes the MemberBuilder with the data of {@code memberToCopy}.
     */
    public MemberBuilder(Member memberToCopy) {
        name = memberToCopy.getName();
        phone = memberToCopy.getPhone();
        email = memberToCopy.getEmail();
        telegram = memberToCopy.getTelegram();
        tags = new HashSet<>(memberToCopy.getTags());
        tasks = new ArrayList<>(memberToCopy.getTasks());
    }

    /**
     * Sets the {@code Name} of the {@code Member} that we are building.
     */
    public MemberBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Member} that we are building.
     */
    public MemberBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Telegram} of the {@code Member} that we are building.
     */
    public MemberBuilder withTelegram(String telegram) {
        this.telegram = new Telegram(telegram);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Member} that we are building.
     */
    public MemberBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Member} that we are building.
     */
    public MemberBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Parses the {@code tasks} into a {@code List<Task>} and set it to the {@code Member} that we are building.
     */
    public MemberBuilder withTask(String... taskName) {
        for (String i : taskName) {
            this.tasks.add(new Task(i));
        }
        return this;
    }

    public Member build() {
        return new Member(name, phone, email, telegram, tags, tasks);
    }

}
