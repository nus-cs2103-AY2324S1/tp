package networkbook.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import networkbook.commons.exceptions.DuplicateEntryException;
import networkbook.commons.exceptions.IllegalValueException;
import networkbook.commons.exceptions.NullValueException;
import networkbook.commons.util.JsonObject;
import networkbook.logic.parser.ParserUtil;
import networkbook.logic.parser.exceptions.ParseException;
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
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson implements JsonObject {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";
    public static final String DUPLICATE_ENTRY_MESSAGE = "Duplicate entry for %s found: %s";

    private final String name;
    private final List<JsonAdaptedProperty<Phone>> phones = new ArrayList<>();
    private final List<JsonAdaptedProperty<Email>> emails = new ArrayList<>();
    private final List<JsonAdaptedProperty<Link>> links = new ArrayList<>();
    private final String graduation;
    private final List<JsonAdaptedProperty<Course>> courses = new ArrayList<>();
    private final List<JsonAdaptedProperty<Specialisation>> specialisations = new ArrayList<>();
    private final List<JsonAdaptedProperty<Tag>> tags = new ArrayList<>();
    private final String priority;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name,
                             @JsonProperty("phones") List<JsonAdaptedProperty<Phone>> phones,
                             @JsonProperty("emails") List<JsonAdaptedProperty<Email>> emails,
                             @JsonProperty("links") List<JsonAdaptedProperty<Link>> links,
                             @JsonProperty("graduation") String graduation,
                             @JsonProperty("courses") List<JsonAdaptedProperty<Course>> courses,
                             @JsonProperty("specialisations") List<JsonAdaptedProperty<Specialisation>> specialisations,
                             @JsonProperty("tags") List<JsonAdaptedProperty<Tag>> tags,
                             @JsonProperty("priority") String priority) {
        this.name = name;
        if (phones != null) {
            this.phones.addAll(phones);
        }
        if (emails != null) {
            this.emails.addAll(emails);
        }
        if (links != null) {
            this.links.addAll(links);
        }
        this.graduation = graduation;
        if (courses != null) {
            this.courses.addAll(courses);
        }
        if (specialisations != null) {
            this.specialisations.addAll(specialisations);
        }
        if (tags != null) {
            this.tags.addAll(tags);
        }
        this.priority = priority;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phones.addAll(source.getPhones().stream()
                .map(JsonAdaptedProperty::new)
                .collect(Collectors.toList()));
        emails.addAll(source.getEmails().stream()
                .map(JsonAdaptedProperty::new)
                .collect(Collectors.toList()));
        links.addAll(source.getLinks().stream()
                .map(JsonAdaptedProperty::new)
                .collect(Collectors.toList()));
        graduation = source.getGraduation().map(Graduation::toString).orElse(null);
        courses.addAll(source.getCourses().stream()
                        .map(Course::getCourse)
                .map(s -> new JsonAdaptedProperty<Course>(s))
                .collect(Collectors.toList()));
        specialisations.addAll(source.getSpecialisations().stream()
                .map(JsonAdaptedProperty::new)
                .collect(Collectors.toList()));
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedProperty::new)
                .collect(Collectors.toList()));
        priority = source.getPriority().map(Priority::toString).orElse(null);
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException, DuplicateEntryException {
        final Name modelName = getModelName();
        final UniqueList<Phone> modelPhones = getModelPhones();
        final UniqueList<Email> modelEmails = getModelEmails();
        final UniqueList<Link> modelLinks = getModelLinks();
        final Graduation modelGraduation = getModelGraduation();
        final UniqueList<Course> modelCourses = getModelCourses();
        final UniqueList<Specialisation> modelSpecs = getModelSpecialisations();
        final UniqueList<Tag> modelTags = getModelTags();
        final Priority modelPriority = getModelPriority();

        return new Person(modelName, modelPhones, modelEmails, modelLinks, modelGraduation, modelCourses,
                modelSpecs, modelTags, modelPriority);
    }

    private Name getModelName() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(name);
    }

    private UniqueList<Phone> getModelPhones() throws IllegalValueException, DuplicateEntryException {
        final UniqueList<Phone> modelPhones = new UniqueList<>();
        for (JsonAdaptedProperty<Phone> phone : phones) {
            Phone toAdd = phone.toModelType(Phone::isValidPhone, Phone.MESSAGE_CONSTRAINTS, Phone::new);
            if (modelPhones.contains(toAdd)) {
                throw new DuplicateEntryException(String.format(DUPLICATE_ENTRY_MESSAGE, "phone", phone.getName()));
            }
            modelPhones.add(toAdd);
        };
        return modelPhones;
    }

    private UniqueList<Email> getModelEmails() throws IllegalValueException, DuplicateEntryException {
        final UniqueList<Email> modelEmails = new UniqueList<>();
        for (JsonAdaptedProperty<Email> email : emails) {
            Email toAdd = email.toModelType(Email::isValidEmail, Email.MESSAGE_CONSTRAINTS, Email::new);
            if (modelEmails.contains(toAdd)) {
                throw new DuplicateEntryException(String.format(DUPLICATE_ENTRY_MESSAGE, "email", email.getName()));
            }
            modelEmails.add(toAdd);
        }
        return modelEmails;
    }

    private UniqueList<Link> getModelLinks() throws IllegalValueException, DuplicateEntryException {
        final UniqueList<Link> modelLinks = new UniqueList<>();
        for (JsonAdaptedProperty<Link> link : links) {
            Link toAdd = link.toModelType(Link::isValidLink, Link.MESSAGE_CONSTRAINTS, Link::new);
            if (modelLinks.contains(toAdd)) {
                throw new DuplicateEntryException(String.format(DUPLICATE_ENTRY_MESSAGE, "link", link.getName()));
            }
            modelLinks.add(toAdd);
        }
        return modelLinks;
    }

    private Graduation getModelGraduation() throws IllegalValueException {
        Graduation modelGraduation = null;
        if (graduation != null) {
            if (!Graduation.isValidGraduation(graduation)) {
                throw new IllegalValueException(Graduation.MESSAGE_CONSTRAINTS);
            }
            modelGraduation = new Graduation(graduation);
        }
        return modelGraduation;
    }

    private UniqueList<Course> getModelCourses() throws IllegalValueException, DuplicateEntryException {
        final UniqueList<Course> modelCourses = new UniqueList<>();
        for (JsonAdaptedProperty<Course> course : courses) {
            Course toAdd = getModelCourse(course);

            if (modelCourses.contains(toAdd)) {
                throw new DuplicateEntryException(String.format(DUPLICATE_ENTRY_MESSAGE, "course", course.getName()));
            }
            modelCourses.add(toAdd);
        }
        return modelCourses;
    }

    private Course getModelCourse(JsonAdaptedProperty<Course> jsonCourse) throws IllegalValueException {
        try {
            return ParserUtil.parseCourseWithPrefixes(jsonCourse.getName());
        } catch (ParseException e) {
            throw new IllegalValueException(Course.MESSAGE_CONSTRAINTS);
        }
    }

    private UniqueList<Specialisation> getModelSpecialisations()
            throws IllegalValueException, DuplicateEntryException {
        final UniqueList<Specialisation> modelSpecs = new UniqueList<>();
        for (JsonAdaptedProperty<Specialisation> specialisation : specialisations) {
            Specialisation toAdd = specialisation.toModelType(Specialisation::isValidSpecialisation,
                    Specialisation.MESSAGE_CONSTRAINTS, Specialisation::new);
            if (modelSpecs.contains(toAdd)) {
                throw new DuplicateEntryException(String.format(
                        DUPLICATE_ENTRY_MESSAGE, "specialisation", specialisation.getName()));
            }
            modelSpecs.add(toAdd);
        }
        return modelSpecs;
    }

    private UniqueList<Tag> getModelTags() throws IllegalValueException, DuplicateEntryException {
        final UniqueList<Tag> modelTags = new UniqueList<>();
        for (JsonAdaptedProperty<Tag> tag : tags) {
            Tag toAdd = tag.toModelType(Tag::isValidTagName, Tag.MESSAGE_CONSTRAINTS, Tag::new);
            if (modelTags.contains(toAdd)) {
                throw new DuplicateEntryException(String.format(DUPLICATE_ENTRY_MESSAGE, "tag", tag.getName()));
            }
            modelTags.add(toAdd);
        }
        return modelTags;
    }

    private Priority getModelPriority() throws IllegalValueException {
        Priority modelPriority = null;
        if (priority != null) {
            if (!Priority.isValidPriority(Priority.parsePriorityLevel(priority))) {
                throw new IllegalValueException(Priority.MESSAGE_CONSTRAINTS);
            }
            modelPriority = new Priority(priority);
        }
        return modelPriority;
    }

    @Override
    public void assertFieldsAreNotNull() throws NullValueException {
        assertNameIsNotNull();
        assertPhonesAreNotNull();
        assertEmailsAreNotNull();
        assertLinksAreNotNull();
        assertCoursesAreNotNull();
        assertSpecialisationsAreNotNull();
        assertTagsAreNotNull();
    }

    private void assertNameIsNotNull() throws NullValueException {
        if (name == null) {
            throw new NullValueException();
        }
    }

    private void assertPhonesAreNotNull() throws NullValueException {
        for (JsonAdaptedProperty<Phone> phone : phones) {
            if (phone == null) {
                throw new NullValueException();
            }
        }
    }

    private void assertEmailsAreNotNull() throws NullValueException {
        for (JsonAdaptedProperty<Email> email : emails) {
            if (email == null) {
                throw new NullValueException();
            }
        }
    }

    private void assertLinksAreNotNull() throws NullValueException {
        for (JsonAdaptedProperty<Link> link : links) {
            if (link == null) {
                throw new NullValueException();
            }
        }
    }

    private void assertCoursesAreNotNull() throws NullValueException {
        for (JsonAdaptedProperty<Course> course : courses) {
            if (course == null) {
                throw new NullValueException();
            }
        }
    }

    private void assertSpecialisationsAreNotNull() throws NullValueException {
        for (JsonAdaptedProperty<Specialisation> specialisation : specialisations) {
            if (specialisation == null) {
                throw new NullValueException();
            }
        }
    }

    private void assertTagsAreNotNull() throws NullValueException {
        for (JsonAdaptedProperty<Tag> tag : tags) {
            if (tag == null) {
                throw new NullValueException();
            }
        }
    }
}
