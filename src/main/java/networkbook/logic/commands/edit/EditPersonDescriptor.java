package networkbook.logic.commands.edit;

import static java.util.Objects.requireNonNull;
import static networkbook.commons.util.CollectionUtil.requireAllNonNull;

import networkbook.commons.core.index.Index;
import networkbook.model.person.Course;
import networkbook.model.person.Email;
import networkbook.model.person.Graduation;
import networkbook.model.person.Link;
import networkbook.model.person.Name;
import networkbook.model.person.Person;
import networkbook.model.person.Phone;
import networkbook.model.person.Priority;
import networkbook.model.person.Specialisation;
import networkbook.model.tag.Tag;
import networkbook.model.util.UniqueList;

public class EditPersonDescriptor {
    private Name name;
    private UniqueList<Phone> phones;
    private UniqueList<Email> emails;
    private UniqueList<Link> links;
    private Graduation graduation;
    private UniqueList<Course> courses;
    private UniqueList<Specialisation> specialisations;
    private UniqueList<Tag> tags;
    private Priority priority;

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
        requireNonNull(name);
        this.name = name;
    }

    public void setPhone(Index index, Phone phone) {
        requireAllNonNull(index, phone);
        this.phones.setItem(index.getZeroBased(), phone);
    }

    public void setEmail(Index index, Email email) {
        requireAllNonNull(index, email);
        this.emails.setItem(index.getZeroBased(), email);
    }

    public void setLink(Index index, Link link) {
        requireAllNonNull(index, link);
        this.links.setItem(index.getZeroBased(), link);
    }

    public void setGraduation(Graduation graduation) {
        requireNonNull(graduation);
        this.graduation = graduation;
    }

    public void setCourse(Index index, Course course) {
        requireAllNonNull(index, course);
        this.courses.setItem(index.getZeroBased(), course);
    }

    public void setSpecialisation(Index index, Specialisation specialisation) {
        requireAllNonNull(index, specialisation);
        this.specialisations.setItem(index.getZeroBased(), specialisation);
    }

    public void setTag(Index index, Tag tag) {
        requireAllNonNull(index, tag);
        this.tags.setItem(index.getZeroBased(), tag);
    }

    public void setPriority(Priority priority) {
        requireNonNull(priority);
        this.priority = priority;
    }

    public Person toPerson() {
        return new Person(name, phones, emails, links, graduation, courses, specialisations, tags, priority);
    }
}
