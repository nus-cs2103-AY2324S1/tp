package networkbook.storage;

import static networkbook.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import networkbook.commons.exceptions.DuplicateEntryException;
import networkbook.commons.exceptions.IllegalValueException;
import networkbook.commons.exceptions.NullValueException;
import networkbook.model.person.Course;
import networkbook.model.person.Email;
import networkbook.model.person.Graduation;
import networkbook.model.person.Link;
import networkbook.model.person.Name;
import networkbook.model.person.Person;
import networkbook.model.person.Phone;
import networkbook.model.person.Specialisation;
import networkbook.model.person.Tag;
import networkbook.testutil.PersonBuilder;
import networkbook.testutil.TypicalPersons;

// TODO: add/amend tests based on individual attribute specification
public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String NULL_NAME = null;
    private static final List<JsonAdaptedProperty<Phone>> INVALID_PHONES = List.of(
            new JsonAdaptedProperty<>("+ 651234"));
    private static final List<JsonAdaptedProperty<Phone>> CONTAINING_NULL_PHONES =
            Stream.<JsonAdaptedProperty<Phone>>generate(() -> null)
                    .limit(1).collect(Collectors.toCollection(ArrayList::new));
    private static final List<JsonAdaptedProperty<Phone>> CONTAINING_DUPLICATE_PHONES = List.of(
            new JsonAdaptedProperty<>("12345"), new JsonAdaptedProperty<>("12345"));
    private static final List<JsonAdaptedProperty<Email>> INVALID_EMAILS =
            List.of(new JsonAdaptedProperty<>("example.com"));
    private static final List<JsonAdaptedProperty<Email>> CONTAINING_NULL_EMAILS =
            Stream.<JsonAdaptedProperty<Email>>generate(() -> null)
                    .limit(1).collect(Collectors.toCollection(ArrayList::new));
    private static final List<JsonAdaptedProperty<Email>> CONTAINING_DUPLICATE_EMAILS = List.of(
            new JsonAdaptedProperty<>("nkn@example.com"), new JsonAdaptedProperty<>("nkn@example.com"));
    private static final List<JsonAdaptedProperty<Link>> INVALID_LINKS = List.of(
            new JsonAdaptedProperty<>("facebookcom"));
    private static final List<JsonAdaptedProperty<Link>> CONTAINING_NULL_LINKS =
            Stream.<JsonAdaptedProperty<Link>>generate(() -> null)
                    .limit(1).collect(Collectors.toCollection(ArrayList::new));
    private static final List<JsonAdaptedProperty<Link>> CONTAINING_DUPLICATE_LINKS = List.of(
            new JsonAdaptedProperty<>("example.com"), new JsonAdaptedProperty<>("example.com"));
    private static final String INVALID_GRADUATION = "2024";
    private static final String NULL_GRADUATION = null;
    private static final List<JsonAdaptedProperty<Course>> INVALID_COURSES = List.of(
            new JsonAdaptedProperty<>(""));
    private static final List<JsonAdaptedProperty<Course>> CONTAINING_NULL_COURSES =
            Stream.<JsonAdaptedProperty<Course>>generate(() -> null)
                    .limit(1).collect(Collectors.toCollection(ArrayList::new));
    private static final List<JsonAdaptedProperty<Course>> CONTAINING_DUPLICATE_COURSES = List.of(
            new JsonAdaptedProperty<>("CS2103T"), new JsonAdaptedProperty<>("CS2103T"));
    private static final List<JsonAdaptedProperty<Specialisation>> INVALID_SPECIALISATIONS = List.of(
            new JsonAdaptedProperty<>(""));
    private static final List<JsonAdaptedProperty<Specialisation>> CONTAINING_NULL_SPECIALISATIONS =
            Stream.<JsonAdaptedProperty<Specialisation>>generate(() -> null)
                    .limit(1).collect(Collectors.toCollection(ArrayList::new));
    private static final List<JsonAdaptedProperty<Specialisation>> CONTAINING_DUPLICATE_SPECIALISATIONS = List.of(
            new JsonAdaptedProperty<>("swe"), new JsonAdaptedProperty<>("swe"));
    private static final List<JsonAdaptedProperty<Tag>> INVALID_TAGS =
            List.of(new JsonAdaptedProperty<Tag>("#friend"));
    private static final List<JsonAdaptedProperty<Tag>> CONTAINING_NULL_TAGS =
            Stream.<JsonAdaptedProperty<Tag>>generate(() -> null)
                    .limit(1).collect(Collectors.toCollection(ArrayList::new));
    private static final List<JsonAdaptedProperty<Tag>> CONTAINING_DUPLICATE_TAGS = List.of(
            new JsonAdaptedProperty<>("friend"), new JsonAdaptedProperty<>("friend"));
    private static final String INVALID_PRIORITY = "hi";
    private static final String NULL_PRIORITY = null;

    private static final String VALID_NAME = TypicalPersons.BENSON.getName().toString();
    private static final List<JsonAdaptedProperty<Phone>> VALID_PHONES = TypicalPersons.BENSON.getPhones().stream()
            .map(JsonAdaptedProperty::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedProperty<Email>> VALID_EMAILS = TypicalPersons.BENSON.getEmails().stream()
            .map(JsonAdaptedProperty::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedProperty<Link>> VALID_LINKS = TypicalPersons.BENSON.getLinks().stream()
            .map(JsonAdaptedProperty::new)
            .collect(Collectors.toList());
    private static final String VALID_GRADUATION = TypicalPersons.BENSON.getGraduation()
            .map(Graduation::toString).orElse(null);
    private static final List<JsonAdaptedProperty<Course>> VALID_COURSES = TypicalPersons.BENSON.getCourses().stream()
            .map(JsonAdaptedProperty::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedProperty<Specialisation>> VALID_SPECIALISATIONS = List.of(
            new JsonAdaptedProperty<>("Game Development")
    );
    private static final List<JsonAdaptedProperty<Tag>> VALID_TAGS = TypicalPersons.BENSON.getTags().stream()
            .map(JsonAdaptedProperty::new)
            .collect(Collectors.toList());
    private static final String VALID_PRIORITY = "mEDiUM";

    @Test
    public void assertFieldsAreNotNull_nullName_throwsNullValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(NULL_NAME, VALID_PHONES, VALID_EMAILS, VALID_LINKS,
                VALID_GRADUATION, VALID_COURSES, VALID_SPECIALISATIONS, VALID_TAGS, VALID_PRIORITY);
        assertThrows(NullValueException.class, person::assertFieldsAreNotNull);
    }

    @Test
    public void assertFieldsAreNotNull_phonesContainNull_throwsNullValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, CONTAINING_NULL_PHONES, VALID_EMAILS,
                VALID_LINKS, VALID_GRADUATION, VALID_COURSES, VALID_SPECIALISATIONS, VALID_TAGS, VALID_PRIORITY);
        assertThrows(NullValueException.class, person::assertFieldsAreNotNull);
    }

    @Test
    public void assertFieldsAreNotNull_emailsContainNull_throwsNullValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONES, CONTAINING_NULL_EMAILS,
                VALID_LINKS, VALID_GRADUATION, VALID_COURSES, VALID_SPECIALISATIONS, VALID_TAGS, VALID_PRIORITY);
        assertThrows(NullValueException.class, person::assertFieldsAreNotNull);
    }

    @Test
    public void assertFieldsAreNotNull_linksContainNull_throwsNullValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONES, VALID_EMAILS,CONTAINING_NULL_LINKS,
                VALID_GRADUATION, VALID_COURSES, VALID_SPECIALISATIONS, VALID_TAGS, VALID_PRIORITY);
        assertThrows(NullValueException.class, person::assertFieldsAreNotNull);
    }

    @Test
    public void assertFieldsAreNotNull_nullGraduation_throwsNothing() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONES, VALID_EMAILS, VALID_LINKS,
                NULL_GRADUATION, VALID_COURSES, VALID_SPECIALISATIONS, VALID_TAGS, VALID_PRIORITY);
        person.assertFieldsAreNotNull();
    }

    @Test
    public void assertFieldsAreNotNull_coursesContainNull_throwsNullValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONES, VALID_EMAILS, VALID_LINKS,
                VALID_GRADUATION, CONTAINING_NULL_COURSES, VALID_SPECIALISATIONS, VALID_TAGS, VALID_PRIORITY);
        assertThrows(NullValueException.class, person::assertFieldsAreNotNull);
    }

    @Test
    public void assertFieldsAreNotNull_specialisationsContainNull_throwsNullValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONES, VALID_EMAILS, VALID_LINKS,
                VALID_GRADUATION, VALID_COURSES, CONTAINING_NULL_SPECIALISATIONS, VALID_TAGS, VALID_PRIORITY);
        assertThrows(NullValueException.class, person::assertFieldsAreNotNull);
    }

    @Test
    public void assertFieldsAreNotNull_tagsContainNull_throwsNullValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONES, VALID_EMAILS, VALID_LINKS,
                VALID_GRADUATION, VALID_COURSES, VALID_SPECIALISATIONS, CONTAINING_NULL_TAGS, VALID_PRIORITY);
        assertThrows(NullValueException.class, person::assertFieldsAreNotNull);
    }

    @Test
    public void assertFieldsAreNotNull_nullPriority_throwsNothing() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONES, VALID_EMAILS, VALID_LINKS,
                VALID_GRADUATION, VALID_COURSES, VALID_SPECIALISATIONS, VALID_TAGS, NULL_PRIORITY);
        person.assertFieldsAreNotNull();
    }

    @Test
    public void assertFieldsAreNotNull_validFields_throwsNothing() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONES, VALID_EMAILS, VALID_LINKS,
                VALID_GRADUATION, VALID_COURSES, VALID_SPECIALISATIONS, VALID_TAGS, VALID_PRIORITY);
        person.assertFieldsAreNotNull();
    }

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(TypicalPersons.BENSON);
        assertEquals(TypicalPersons.BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_PHONES, VALID_EMAILS,
                        VALID_LINKS, VALID_GRADUATION, VALID_COURSES,
                        VALID_SPECIALISATIONS, VALID_TAGS, VALID_PRIORITY);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(NULL_NAME, VALID_PHONES, VALID_EMAILS,
                VALID_LINKS, VALID_GRADUATION, VALID_COURSES,
                VALID_SPECIALISATIONS, VALID_TAGS, VALID_PRIORITY);
        String expectedMessage = String.format(
                JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT,
                Name.class.getSimpleName()
        );
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhones_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_PHONES, VALID_EMAILS,
                        VALID_LINKS, VALID_GRADUATION, VALID_COURSES,
                        VALID_SPECIALISATIONS, VALID_TAGS, VALID_PRIORITY);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_duplicatePhones_throwsDuplicateEntryException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, CONTAINING_DUPLICATE_PHONES, VALID_EMAILS,
                VALID_LINKS, VALID_GRADUATION, VALID_COURSES, VALID_SPECIALISATIONS, VALID_TAGS, VALID_PRIORITY);
        String expectedMessage = String.format(JsonAdaptedPerson.DUPLICATE_ENTRY_MESSAGE, "phone", "12345");
        assertThrows(DuplicateEntryException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmails_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONES, INVALID_EMAILS,
                        VALID_LINKS, VALID_GRADUATION, VALID_COURSES,
                        VALID_SPECIALISATIONS, VALID_TAGS, VALID_PRIORITY);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_duplicateEmails_throwsDuplicateEntryException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONES, CONTAINING_DUPLICATE_EMAILS,
                VALID_LINKS, VALID_GRADUATION, VALID_COURSES, VALID_SPECIALISATIONS, VALID_TAGS, VALID_PRIORITY);
        String expectedMessage = String.format(JsonAdaptedPerson.DUPLICATE_ENTRY_MESSAGE, "email", "nkn@example.com");
        assertThrows(DuplicateEntryException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidLink_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONES, VALID_EMAILS,
                        INVALID_LINKS, VALID_GRADUATION, VALID_COURSES,
                        VALID_SPECIALISATIONS, VALID_TAGS, VALID_PRIORITY);
        String expectedMessage = Link.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_duplicateLinks_throwsDuplicateEntryException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONES, VALID_EMAILS,
                CONTAINING_DUPLICATE_LINKS, VALID_GRADUATION, VALID_COURSES, VALID_SPECIALISATIONS,
                VALID_TAGS, VALID_PRIORITY);
        String expectedMessage = String.format(JsonAdaptedPerson.DUPLICATE_ENTRY_MESSAGE, "link", "example.com");
        assertThrows(DuplicateEntryException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidGraduation_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONES, VALID_EMAILS,
                        VALID_LINKS, INVALID_GRADUATION, VALID_COURSES,
                        VALID_SPECIALISATIONS, VALID_TAGS, VALID_PRIORITY);
        String expectedMessage = Graduation.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullGraduation_success() throws Exception {
        JsonAdaptedPerson jsonAdaptedPerson = new JsonAdaptedPerson(VALID_NAME, VALID_PHONES, VALID_EMAILS, VALID_LINKS,
                NULL_GRADUATION, VALID_COURSES, VALID_SPECIALISATIONS, VALID_TAGS, VALID_PRIORITY);
        PersonBuilder builder = new PersonBuilder(TypicalPersons.BENSON);
        Person person = builder.withGraduation(null).withPriority("Medium").build();
        assertEquals(person, jsonAdaptedPerson.toModelType());
    }

    @Test
    public void toModelType_invalidCourse_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONES, VALID_EMAILS,
                        VALID_LINKS, VALID_GRADUATION, INVALID_COURSES,
                        VALID_SPECIALISATIONS, VALID_TAGS, VALID_PRIORITY);
        String expectedMessage = Course.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_duplicateCourses_throwsDuplicateEntryException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONES, VALID_EMAILS, VALID_LINKS,
                VALID_GRADUATION, CONTAINING_DUPLICATE_COURSES, VALID_SPECIALISATIONS, VALID_TAGS, VALID_PRIORITY);
        String expectedMessage = String.format(JsonAdaptedPerson.DUPLICATE_ENTRY_MESSAGE, "course", "CS2103T");
        assertThrows(DuplicateEntryException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidSpecialisation_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONES, VALID_EMAILS,
                        VALID_LINKS, VALID_GRADUATION, VALID_COURSES,
                        INVALID_SPECIALISATIONS, VALID_TAGS, VALID_PRIORITY);
        String expectedMessage = Specialisation.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_duplicateSpecialisations_throwsDuplicateEntryException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONES, VALID_EMAILS, VALID_LINKS,
                VALID_GRADUATION, VALID_COURSES, CONTAINING_DUPLICATE_SPECIALISATIONS, VALID_TAGS, VALID_PRIORITY);
        String expectedMessage = String.format(JsonAdaptedPerson.DUPLICATE_ENTRY_MESSAGE, "specialisation", "swe");
        assertThrows(DuplicateEntryException.class, expectedMessage, person::toModelType);
    }


    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONES, VALID_EMAILS,
                        VALID_LINKS, VALID_GRADUATION, VALID_COURSES,
                        VALID_SPECIALISATIONS, INVALID_TAGS, VALID_PRIORITY);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_duplicateTags_throwsDuplicateEntryException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONES, VALID_EMAILS, VALID_LINKS,
                VALID_GRADUATION, VALID_COURSES, VALID_SPECIALISATIONS, CONTAINING_DUPLICATE_TAGS, VALID_PRIORITY);
        String expectedMessage = String.format(JsonAdaptedPerson.DUPLICATE_ENTRY_MESSAGE, "tag", "friend");
        assertThrows(DuplicateEntryException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPriority_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONES, VALID_EMAILS,
                VALID_LINKS, VALID_GRADUATION, VALID_COURSES,
                VALID_SPECIALISATIONS, VALID_TAGS, INVALID_PRIORITY);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_nullPriority_success() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONES, VALID_EMAILS,
                VALID_LINKS, VALID_GRADUATION, VALID_COURSES,
                VALID_SPECIALISATIONS, VALID_TAGS, NULL_PRIORITY);
        assertEquals(TypicalPersons.BENSON, person.toModelType());
    }

}
