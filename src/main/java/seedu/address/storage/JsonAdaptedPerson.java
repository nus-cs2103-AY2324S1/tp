package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Description;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Major;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nationality;
import seedu.address.model.person.Person;
import seedu.address.model.person.Year;
import seedu.address.model.socialmedialink.SocialMediaLink;
import seedu.address.model.tutorial.Tutorial;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String major;
    private final String year;
    private final String email;
    private final String description;
    private final List<JsonAdaptedTutorial> tutorials = new ArrayList<>();
    private final List<JsonAdaptedSocialMedia> socialMediaLinks = new ArrayList<>();
    private final String nationality;
    private final String gender;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name,
                             @JsonProperty("major") String major,
                             @JsonProperty("year") String year,
                             @JsonProperty("email") String email,
                             @JsonProperty("description") String description,
                             @JsonProperty("tutorials") List<JsonAdaptedTutorial> tutorials,
                             @JsonProperty("socialMediaLinks") List<JsonAdaptedSocialMedia> socialMediaLinks,
                             @JsonProperty("nationality") String nationality,
                             @JsonProperty("gender") String gender) {
        this.name = name;
        this.major = major;
        this.year = year;
        this.email = email;
        this.description = description;
        if (tutorials != null) {
            this.tutorials.addAll(tutorials);
        }
        if (socialMediaLinks != null) {
            this.socialMediaLinks.addAll(socialMediaLinks);
        }
        this.nationality = nationality;
        this.gender = gender;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        major = source.getMajor().value;
        year = source.getYear().value;
        email = source.getEmail().value;
        description = source.getDescription().value;
        tutorials.addAll(source.getTutorials().stream()
                .map(JsonAdaptedTutorial::new)
                .collect(Collectors.toList()));
        socialMediaLinks.addAll(source.getSocialMediaLinks().stream()
                .map(JsonAdaptedSocialMedia::new)
                .collect(Collectors.toList()));
        nationality = source.getNationality().value;
        gender = source.getGender().value;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tutorial> personTutorials = new ArrayList<>();
        final List<SocialMediaLink> personSocialMediaLinks = new ArrayList<>();

        for (JsonAdaptedTutorial tutorial : tutorials) {
            personTutorials.add(tutorial.toModelType());
        }

        for (JsonAdaptedSocialMedia socialMediaLink : socialMediaLinks) {
            personSocialMediaLinks.add(socialMediaLink.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (major == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Major.class.getSimpleName()));
        }
        if (!Major.isValidMajor(major)) {
            throw new ParseException(Major.MESSAGE_CONSTRAINTS);
        }
        final Major modelMajor = new Major(major);

        if (year == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Year.class.getSimpleName()));
        }
        if (!Year.isValidYear(year)) {
            throw new IllegalValueException(Year.MESSAGE_CONSTRAINTS);
        }
        final Year modelYear = new Year(year);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new ParseException(Description.MESSAGE_CONSTRAINTS);
        }
        if (nationality == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                                                                Nationality.class.getSimpleName()));
        }
        if (!Nationality.isValidNationality(nationality)) {
            throw new IllegalValueException(Nationality.MESSAGE_CONSTRAINTS);
        }

        if (!Gender.isValidGender(gender)) {
            throw new IllegalValueException(Gender.MESSAGE_CONSTRAINTS);
        }

        final Nationality modelNationality = new Nationality(nationality);
        final Gender modelGender = new Gender(gender);
        final Description modelDescription = new Description(description);
        final Set<Tutorial> modelTutorials = new HashSet<>(personTutorials);
        final Set<SocialMediaLink> modelSocialMediaLinks = new HashSet<>(personSocialMediaLinks);

        return new Person(modelName, modelMajor, modelYear, modelEmail, modelDescription,
            modelTutorials, modelSocialMediaLinks, modelNationality, modelGender);

    }
}
