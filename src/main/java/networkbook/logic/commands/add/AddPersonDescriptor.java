package networkbook.logic.commands.add;

import java.util.Objects;
import java.util.Optional;

import networkbook.commons.util.CollectionUtil;
import networkbook.commons.util.ToStringBuilder;
import networkbook.model.person.Course;
import networkbook.model.person.Email;
import networkbook.model.person.Graduation;
import networkbook.model.person.Link;
import networkbook.model.person.Phone;
import networkbook.model.person.Priority;
import networkbook.model.person.Specialisation;
import networkbook.model.person.Tag;
import networkbook.model.util.UniqueList;

/**
 * Stores the details to edit the person with. Each non-empty field value will replace the
 * corresponding field value of the person.
 */
public class AddPersonDescriptor {
    private UniqueList<Phone> phones;
    private UniqueList<Email> emails;
    private UniqueList<Link> links;
    private Graduation graduation;
    private UniqueList<Course> courses;
    private UniqueList<Specialisation> specialisations;
    private UniqueList<Tag> tags;
    private Priority priority;

    public AddPersonDescriptor() {
    }

    /**
     * Copy constructor.
     */
    public AddPersonDescriptor(AddPersonDescriptor toCopy) {
        setPhones(toCopy.getPhones().map(UniqueList::copy).orElse(null));
        setEmails(toCopy.getEmails().map(UniqueList::copy).orElse(null));
        setLinks(toCopy.getLinks().map(UniqueList::copy).orElse(null));
        setGraduation(toCopy.graduation);
        setCourses(toCopy.getCourses().map(UniqueList::copy).orElse(null));
        setSpecialisations(toCopy.getSpecialisations().map(UniqueList::copy).orElse(null));
        setTags(toCopy.getTags().map(UniqueList::copy).orElse(null));
        setPriority(toCopy.priority);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldEdited() {
        return CollectionUtil.isAnyNonNull(graduation, priority)
                || (phones != null && !phones.isEmpty())
                || (emails != null && !emails.isEmpty())
                || (links != null && !links.isEmpty())
                || (courses != null && !courses.isEmpty())
                || (specialisations != null && !specialisations.isEmpty())
                || (tags != null && !tags.isEmpty());
    }

    public void setPhones(UniqueList<Phone> phones) {
        this.phones = phones;
    }

    /**
     * Adds {@code phone} to the list of {@code Phone}s.
     */
    public void addPhone(Phone phone) {
        this.phones = Optional.ofNullable(this.phones).map(phones -> {
            phones.add(phone);
            return phones;
        }).or(() -> {
            UniqueList<Phone> uniqueList = new UniqueList<>();
            uniqueList.add(phone);
            return Optional.of(uniqueList);
        }).get();
    }

    public Optional<UniqueList<Phone>> getPhones() {
        return Optional.ofNullable(phones);
    }

    public void setEmails(UniqueList<Email> emails) {
        this.emails = emails;
    }

    /**
     * Adds {@code email} to the list of {@code emails}
     */
    public void addEmail(Email email) {
        this.emails = Optional.ofNullable(this.emails).map(emails -> {
            emails.add(email);
            return emails;
        }).or(() -> {
            UniqueList<Email> uniqueList = new UniqueList<>();
            uniqueList.add(email);
            return Optional.of(uniqueList);
        }).get();
    }

    public Optional<UniqueList<Email>> getEmails() {
        return Optional.ofNullable(emails);
    }

    public void setLinks(UniqueList<Link> links) {
        this.links = links;
    }

    /**
     * Adds {@code link} to the list of {@code links}.
     */
    public void addLink(Link link) {
        this.links = Optional.ofNullable(this.links).map(links -> {
            links.add(link);
            return links;
        }).or(() -> {
            UniqueList<Link> uniqueList = new UniqueList<>();
            uniqueList.add(link);
            return Optional.of(uniqueList);
        }).get();
    }

    public Optional<UniqueList<Link>> getLinks() {
        return Optional.ofNullable(links);
    }

    public void setGraduation(Graduation graduation) {
        this.graduation = graduation;
    }

    public Optional<Graduation> getGraduation() {
        return Optional.ofNullable(graduation);
    }

    public void setCourses(UniqueList<Course> courses) {
        this.courses = courses;
    }

    /**
     * Adds {@code specialisations} to the list of {@code specialisations}.
     */
    public void addCourse(Course course) {
        this.courses = Optional.ofNullable(this.courses).map(courses -> {
            courses.add(course);
            return courses;
        }).or(() -> {
            UniqueList<Course> uniqueList = new UniqueList<>();
            uniqueList.add(course);
            return Optional.of(uniqueList);
        }).get();
    }

    public Optional<UniqueList<Course>> getCourses() {
        return Optional.ofNullable(courses);
    }

    public void setSpecialisations(UniqueList<Specialisation> specialisations) {
        this.specialisations = specialisations;
    }

    /**
     * Adds {@code specialisations} to the list of {@code specialisations}.
     */
    public void addSpecialisation(Specialisation specialisation) {
        this.specialisations = Optional.ofNullable(this.specialisations).map(specialisations -> {
            specialisations.add(specialisation);
            return specialisations;
        }).or(() -> {
            UniqueList<Specialisation> uniqueList = new UniqueList<>();
            uniqueList.add(specialisation);
            return Optional.of(uniqueList);
        }).get();
    }

    public Optional<UniqueList<Specialisation>> getSpecialisations() {
        return Optional.ofNullable(specialisations);
    }

    /**
     * Sets {@code tags} to this object's {@code tags}.
     * A defensive copy of {@code tags} is used internally.
     */
    public void setTags(UniqueList<Tag> tags) {
        this.tags = tags;
    }

    /**
     * Adds {@code tag} to the {@code UniqueList<tag>} of the descriptor.
     * If the tags field is null, initialize it with an empty {@code UniqueList}.
     *
     * @param tag is the new tag to be added.
     */
    public void addTag(Tag tag) {
        if (this.tags == null) {
            this.tags = new UniqueList<>();
        }
        this.tags.add(tag);
    }

    public Optional<UniqueList<Tag>> getTags() {
        return Optional.ofNullable(tags);
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Optional<Priority> getPriority() {
        return Optional.ofNullable(this.priority);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddPersonDescriptor)) {
            return false;
        }

        AddPersonDescriptor otherAddPersonDescriptor = (AddPersonDescriptor) other;
        return Objects.equals(phones, otherAddPersonDescriptor.phones)
                && Objects.equals(emails, otherAddPersonDescriptor.emails)
                && Objects.equals(links, otherAddPersonDescriptor.links)
                && Objects.equals(graduation, otherAddPersonDescriptor.graduation)
                && Objects.equals(courses, otherAddPersonDescriptor.courses)
                && Objects.equals(specialisations, otherAddPersonDescriptor.specialisations)
                && Objects.equals(tags, otherAddPersonDescriptor.tags)
                && Objects.equals(priority, otherAddPersonDescriptor.priority);
    }

    @Override
    public String toString() {
        ToStringBuilder tsb = new ToStringBuilder(this)
                .add("phones", phones)
                .add("emails", emails)
                .add("links", links)
                .add("graduation", graduation)
                .add("courses", courses)
                .add("specialisations", specialisations)
                .add("priority", priority)
                .add("tags", tags);
        return tsb.toString();
    }
}
