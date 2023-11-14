package networkbook.logic.commands.delete;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

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
 * Class that contains temporary information about a person whose field is being deleted.
 */
public class DeletePersonDescriptor {
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
    public DeletePersonDescriptor(Person person) {
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

    /**
     * Deletes the phone entry at {@code index} in the descriptor's list of phones.
     * @param index is the index of the entry to delete.
     * @param indexOfPerson is the index of the person in the displayed list to delete phone.
     * @throws CommandException when the index is invalid in the list.
     */
    public void deletePhone(Index index, Index indexOfPerson) throws CommandException {
        requireNonNull(index);
        if (index.getZeroBased() >= this.phones.size()) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_MULTIVALUED_FIELD_ENTRY_INDEX,
                    indexOfPerson.getOneBased(), "a phone", index.getOneBased()));
        }
        this.phones.removeAtIndex(index.getZeroBased());
    }

    /**
     * Deletes the email entry at {@code index} in the descriptor's list of emails.
     * @param index is the index of the entry to delete.
     * @param indexOfPerson is the index of the person in the displayed list to delete email.
     * @throws CommandException when the index is invalid in the list.
     */
    public void deleteEmail(Index index, Index indexOfPerson) throws CommandException {
        requireNonNull(index);
        if (index.getZeroBased() >= this.emails.size()) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_MULTIVALUED_FIELD_ENTRY_INDEX,
                    indexOfPerson.getOneBased(), "an email", index.getOneBased()));
        }
        this.emails.removeAtIndex(index.getZeroBased());
    }

    /**
     * Deletes the link entry at {@code index} in the descriptor's list of links.
     * @param index is the index of the entry to delete.
     * @param indexOfPerson is the index of the person in the displayed list to delete link.
     * @throws CommandException when the index is invalid in the list.
     */
    public void deleteLink(Index index, Index indexOfPerson) throws CommandException {
        requireNonNull(index);
        if (index.getZeroBased() >= this.links.size()) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_MULTIVALUED_FIELD_ENTRY_INDEX,
                    indexOfPerson.getOneBased(), "a link", index.getOneBased()));
        }
        this.links.removeAtIndex(index.getZeroBased());
    }

    /**
     * Deletes the course entry at {@code index} in the descriptor's list of courses.
     * @param index is the index of the entry to delete.
     * @param indexOfPerson is the index of the person in the displayed list to delete course.
     * @throws CommandException when the index is invalid in the list.
     */
    public void deleteCourse(Index index, Index indexOfPerson) throws CommandException {
        requireNonNull(index);
        if (index.getZeroBased() >= this.courses.size()) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_MULTIVALUED_FIELD_ENTRY_INDEX,
                    indexOfPerson.getOneBased(), "a course", index.getOneBased()));
        }
        this.courses.removeAtIndex(index.getZeroBased());
    }

    /**
     * Deletes the specialisation entry at {@code index} in the descriptor's list of specialisations.
     * @param index is the index of the entry to delete.
     * @param indexOfPerson is the index of the person in the displayed list to delete specialisation.
     * @throws CommandException when the index is invalid in the list.
     */
    public void deleteSpecialisation(Index index, Index indexOfPerson) throws CommandException {
        requireNonNull(index);
        if (index.getZeroBased() >= this.specialisations.size()) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_MULTIVALUED_FIELD_ENTRY_INDEX,
                    indexOfPerson.getOneBased(), "a specialisation", index.getOneBased()));
        }
        this.specialisations.removeAtIndex(index.getZeroBased());
    }

    /**
     * Deletes the tag entry at {@code index} in the descriptor's list of tags.
     * @param index is the index of the entry to delete.
     * @param indexOfPerson is the index of the person in the displayed list to delete tag.
     * @throws CommandException when the index is invalid in the list.
     */
    public void deleteTag(Index index, Index indexOfPerson) throws CommandException {
        requireNonNull(index);
        if (index.getZeroBased() >= this.tags.size()) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_MULTIVALUED_FIELD_ENTRY_INDEX,
                    indexOfPerson.getOneBased(), "a tag", index.getOneBased()));
        }
        this.tags.removeAtIndex(index.getZeroBased());
    }

    /**
     * Deletes the graduation of the descriptor.
     * @param indexOfPerson is the index of the person in the displayed list to delete graduation.
     * @throws CommandException when the person has no graduation.
     */
    public void deleteGraduation(Index indexOfPerson) throws CommandException {
        if (this.graduation == null) {
            throw new CommandException(String.format(Messages.MESSAGE_DELETE_EMPTY_SINGLE_VALUED_FIELD,
                    indexOfPerson.getOneBased(), "a graduation semester"));
        }
        this.graduation = null;
    }

    /**
     * Deletes the graduation of the descriptor.
     * @param indexOfPerson is the index of the person in the displayed list to delete priority.
     * @throws CommandException when the person has no priority.
     */
    public void deletePriority(Index indexOfPerson) throws CommandException {
        if (this.priority == null) {
            throw new CommandException(String.format(Messages.MESSAGE_DELETE_EMPTY_SINGLE_VALUED_FIELD,
                    indexOfPerson.getOneBased(), "a priority"));
        }
        this.priority = null;
    }

    public Person toPerson() {
        return new Person(name, phones, emails, links, graduation, courses, specialisations, tags, priority);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof DeletePersonDescriptor)) {
            return false;
        }

        DeletePersonDescriptor otherDescriptor = (DeletePersonDescriptor) object;
        return this.name.equals(otherDescriptor.name)
                && this.phones.equals(otherDescriptor.phones)
                && this.emails.equals(otherDescriptor.emails)
                && this.links.equals(otherDescriptor.links)
                && Objects.equals(this.graduation, otherDescriptor.graduation)
                && this.courses.equals(otherDescriptor.courses)
                && this.specialisations.equals(otherDescriptor.specialisations)
                && this.tags.equals(otherDescriptor.tags)
                && Objects.equals(this.priority, otherDescriptor.priority);
    }
}
