package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.fields.Email;
import seedu.address.model.person.fields.Name;
import seedu.address.model.person.fields.Phone;
import seedu.address.model.person.fields.Telegram;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

/**
 * Represents a Member in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Member extends Person {

    // Identity Fields
    private final Phone phone;
    private final Email email;
    private final Telegram telegram;
    private final Set<Tag> tags = new HashSet<>();
    private final List<Task> tasks = new ArrayList<>();

    /**
     * Every field must be present and not null.
     *
     * @param name     The name of the member.
     * @param phone    The phone number of the member.
     * @param email    The email of the member.
     * @param telegram The telegram handle of the member.
     * @param tags     The tags of the member.
     */
    public Member(Name name, Phone phone, Email email, Telegram telegram, Set<Tag> tags) {
        super(name);
        requireAllNonNull(telegram);
        this.phone = phone;
        this.email = email;
        this.telegram = telegram;
        this.tags.addAll(tags);
    }

    /**
     * Every field must be present and not null.
     *
     * @param name     The name of the member.
     * @param phone    The phone number of the member.
     * @param email    The email of the member.
     * @param telegram The telegram handle of the member.
     * @param tags     The tags of the member.
     * @param tasks    The tasks of the member.
     */
    public Member(Name name, Phone phone, Email email, Telegram telegram, Set<Tag> tags, List<Task> tasks) {
        super(name);
        requireAllNonNull(telegram);
        this.phone = phone;
        this.email = email;
        this.telegram = telegram;
        this.tags.addAll(tags);
        this.tasks.addAll(tasks);
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Telegram getTelegram() {
        return telegram;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public List<Task> getTasks() {
        return Collections.unmodifiableList(tasks);
    }

    /**
     * Returns true if both members have the same name, phone, email and telegram handle.
     * This defines a weaker notion of equality between two members.
     */
    @Override
    public boolean isSamePerson(Person other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Member)) {
            return false;
        }

        Member otherMember = (Member) other;

        // phone required to be unique across members
        return this.phone.equals(otherMember.phone);
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Member)) {
            return false;
        }

        Member otherMember = (Member) other;
        return getName().equals(otherMember.getName())
            && getPhone().equals(otherMember.getPhone())
            && getEmail().equals(otherMember.getEmail())
            && getTags().equals(otherMember.getTags())
            && getTelegram().equals(otherMember.getTelegram());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getName(), getPhone(), getEmail(), getTelegram(), getTags());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("name", getName())
            .add("phone", getPhone())
            .add("email", getEmail())
            .add("telegram", getTelegram())
            .add("tags", getTags())
            .add("tasks", getTasks())
            .toString();
    }

    @Override
    public String detailsToCopy() {
        return "Name: " + getName() + "\n"
            + "Phone: " + getPhone() + "\n"
            + "Email: " + getEmail() + "\n"
            + "Telegram: " + getTelegram() + "\n"
            + "Tags: " + getTags().stream().map(Tag::toString).collect(Collectors.joining(", ")) + "\n"
            + "Tasks: " + getTasks().stream().map(Task::toString).collect(Collectors.joining(", "));
    }
}
