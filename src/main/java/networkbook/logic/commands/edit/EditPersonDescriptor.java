package networkbook.logic.commands.edit;

import static networkbook.commons.util.CollectionUtil.requireAllNonNull;

import networkbook.commons.core.index.Index;
import networkbook.logic.Messages;
import networkbook.logic.commands.exceptions.CommandException;
import networkbook.model.person.Course;
import networkbook.model.person.Email;
import networkbook.model.person.Graduation;
import networkbook.model.person.Link;
import networkbook.model.person.Name;
import networkbook.model.person.Person;
import networkbook.model.person.Phone;
import networkbook.model.person.Priority;
import networkbook.model.person.Specialisation;
import networkbook.model.person.Tag;
import networkbook.model.util.UniqueList;

/**
 * Constructs a new person from an original person based on the edit actions.
 */
public class EditPersonDescriptor {
    public static final String MESSAGE_DUPLICATE_EXISTS = "The contact already has the new %s provided.";
    private Name name;
    private UniqueList<Phone> phones;
    private UniqueList<Email> emails;
    private UniqueList<Link> links;
    private Graduation graduation;
    private UniqueList<Course> courses;
    private UniqueList<Specialisation> specialisations;
    private UniqueList<Tag> tags;
    private Priority priority;

    /**
     * Constructs a new person editor from the original {@code person}.
     */
    public EditPersonDescriptor(Person person) {
        this.name = person.getName();
        this.phones = person.getPhones();
        this.emails = person.getEmails();
        this.links = person.getLinks();
        this.graduation = person.getGraduation().orElse(null);
        this.courses = person.getCourses();
        this.specialisations = person.getSpecialisations();
        this.tags = person.getTags();
        this.priority = person.getPriority().orElse(null);
    }

    public void setName(Name name) {
        assert name != null;
        this.name = name;
    }

    public void setPhone(Index index, Phone phone, Index indexOfPerson) throws CommandException {
        requireAllNonNull(index, phone);
        if (index.getZeroBased() >= this.phones.size()) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_MULTIVALUED_FIELD_ENTRY_INDEX,
                    indexOfPerson.getOneBased(), "a phone", index.getOneBased()));
        }

        if (this.phones.containsNotAtIndex(phone, index.getZeroBased())) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_EXISTS, "phone"));
        }

        this.phones.setItem(index.getZeroBased(), phone);
    }

    public void setEmail(Index index, Email email, Index indexOfPerson) throws CommandException {
        requireAllNonNull(index, email);
        if (index.getZeroBased() >= this.emails.size()) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_MULTIVALUED_FIELD_ENTRY_INDEX,
                    indexOfPerson.getOneBased(), "an email", index.getOneBased()));
        }

        if (this.emails.containsNotAtIndex(email, index.getZeroBased())) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_EXISTS, "email"));
        }

        this.emails.setItem(index.getZeroBased(), email);
    }

    public void setLink(Index index, Link link, Index indexOfPerson) throws CommandException {
        requireAllNonNull(index, link);
        if (index.getZeroBased() >= this.links.size()) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_MULTIVALUED_FIELD_ENTRY_INDEX,
                    indexOfPerson.getOneBased(), "a link", index.getOneBased()));
        }

        if (this.links.containsNotAtIndex(link, index.getZeroBased())) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_EXISTS, "link"));
        }

        this.links.setItem(index.getZeroBased(), link);
    }

    public void setGraduation(Graduation graduation) {
        assert graduation != null;
        this.graduation = graduation;
    }

    public void setCourse(Index index, Course course, Index indexOfPerson) throws CommandException {
        requireAllNonNull(index, course);
        if (index.getZeroBased() >= this.courses.size()) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_MULTIVALUED_FIELD_ENTRY_INDEX,
                    indexOfPerson.getOneBased(), "a course", index.getOneBased()));
        }

        if (this.courses.containsNotAtIndex(course, index.getZeroBased())) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_EXISTS, "course"));
        }

        this.courses.setItem(index.getZeroBased(), course);
    }

    public void setSpecialisation(Index index, Specialisation specialisation, Index indexOfPerson)
            throws CommandException {
        requireAllNonNull(index, specialisation);
        if (index.getZeroBased() >= this.specialisations.size()) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_MULTIVALUED_FIELD_ENTRY_INDEX,
                    indexOfPerson.getOneBased(), "a specialisation", index.getOneBased()));
        }

        if (this.specialisations.containsNotAtIndex(specialisation, index.getZeroBased())) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_EXISTS, "specialisation"));
        }

        this.specialisations.setItem(index.getZeroBased(), specialisation);
    }

    public void setTag(Index index, Tag tag, Index indexOfPerson) throws CommandException {
        requireAllNonNull(index, tag);
        if (index.getZeroBased() >= this.tags.size()) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_MULTIVALUED_FIELD_ENTRY_INDEX,
                    indexOfPerson.getOneBased(), "a tag", index.getOneBased()));
        }

        if (this.tags.containsNotAtIndex(tag, index.getZeroBased())) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_EXISTS, "tag"));
        }

        this.tags.setItem(index.getZeroBased(), tag);
    }

    public void setPriority(Priority priority) {
        assert priority != null;
        this.priority = priority;
    }

    public Person toPerson() {
        return new Person(name, phones, emails, links, graduation, courses, specialisations, tags, priority);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof EditPersonDescriptor)) {
            return false;
        }

        EditPersonDescriptor otherDescriptor = (EditPersonDescriptor) object;
        return this.name.equals(otherDescriptor.name)
                && this.phones.equals(otherDescriptor.phones)
                && this.emails.equals(otherDescriptor.emails)
                && this.links.equals(otherDescriptor.links)
                && this.graduation.equals(otherDescriptor.graduation)
                && this.courses.equals(otherDescriptor.courses)
                && this.specialisations.equals(otherDescriptor.specialisations)
                && this.tags.equals(otherDescriptor.tags)
                && this.priority.equals(otherDescriptor.priority);
    }
}
