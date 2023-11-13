package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.module.Module;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutorial.Tutorial;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {
    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();
    private final Set<Module> modules = new HashSet<>();
    private final Set<Tutorial> tutorials = new HashSet<>();
    private final StudentNumber studentNumber;

    private final Telegram telegram;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Set<Tag> tags,
                  Set<Module> modules, Set<Tutorial> tutorials, StudentNumber studentNumber,
                  Telegram telegram) {
        requireAllNonNull(name, phone, email, tags, studentNumber);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.tags.addAll(tags);
        this.modules.addAll(modules);
        this.tutorials.addAll(tutorials);
        this.studentNumber = studentNumber;
        this.telegram = telegram;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Module> getModules() {
        return Collections.unmodifiableSet(modules);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tutorial> getTutorials() {
        return Collections.unmodifiableSet(tutorials);
    }

    public Set<String> getUiOfModulesAndTutorials() {
        /* LinkedHashSet used over HashSet because it maintains insertion order, so user will see all tutorials first,
        then all modules without tutorials. */
        Set<String> uiList = new LinkedHashSet<>();
        Set<String> moduleCodesWithTutorials = new HashSet<>();
        for (Tutorial tutorial : this.getTutorials()) {
            moduleCodesWithTutorials.add(tutorial.getModuleCode());
            uiList.add(tutorial.toString());
        }
        for (Module module : this.getModules()) {
            if (!moduleCodesWithTutorials.contains(module.getModuleCode())) {
                uiList.add(module.toString());
            }
        }
        return uiList;
    }

    public StudentNumber getStudentNumber() {
        return studentNumber;
    }

    public Telegram getTelegram() {
        return telegram;
    }


    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
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
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && tags.equals(otherPerson.tags)
                && modules.equals(otherPerson.modules)
                && tutorials.equals(otherPerson.tutorials)
                && studentNumber.equals(otherPerson.studentNumber)
                && telegram.equals(otherPerson.telegram);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, tags, modules, tutorials, studentNumber, telegram);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("tags", tags)
                .add("modules", modules)
                .add("tutorials", tutorials)
                .add("studentNumber", studentNumber)
                .add("telegram", telegram)
                .toString();
    }
}
