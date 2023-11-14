package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

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
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_MAJOR = "Computer Science";
    public static final String DEFAULT_YEAR = "2";
    public static final String DEFAULT_EMAIL = "amy@u.nus.edu";
    public static final String DEFAULT_DESCRIPTION = "CS nerd";
    public static final String DEFAULT_NATIONALITY = "local";
    public static final String DEFAULT_GENDER = "F";

    private Name name;
    private Major major;
    private Year year;
    private Email email;
    private Description description;
    private Set<Tutorial> tutorials;
    private Set<SocialMediaLink> socialMediaLinks;
    private Nationality nationality;
    private Gender gender;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        major = new Major(DEFAULT_MAJOR);
        year = new Year(DEFAULT_YEAR);
        email = new Email(DEFAULT_EMAIL);
        description = new Description(DEFAULT_DESCRIPTION);
        tutorials = new HashSet<>();
        socialMediaLinks = new HashSet<>();
        nationality = new Nationality(DEFAULT_NATIONALITY);
        gender = new Gender(DEFAULT_GENDER);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        major = personToCopy.getMajor();
        year = personToCopy.getYear();
        email = personToCopy.getEmail();
        description = personToCopy.getDescription();
        tutorials = new HashSet<>(personToCopy.getTutorials());
        socialMediaLinks = new HashSet<>(personToCopy.getSocialMediaLinks());
        nationality = personToCopy.getNationality();
        gender = personToCopy.getGender();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code socialMediaLinks} into a {@code Set<SocialMediaLink>} and set it to the {@code Person} that
     * we are building.
     */
    public PersonBuilder withSocialMediaLinks(String... socialMediaLinks) {
        this.socialMediaLinks = SampleDataUtil.getSocialMediaLinkSet(socialMediaLinks);
        return this;
    }

    /**
     * Parses the {@code tutorials} into a {@code Set<Tutorial>}
     * and sets it to the {@code Person} that we are building.
     *
     * @param tutorials A varargs of tutorial strings. Tutorial strings should be a 2-digit number between 01 and 22.
     * @return The updated {@code PersonBuilder} with the tutorials set.
     */
    public PersonBuilder withTutorials(String... tutorials) {
        this.tutorials = SampleDataUtil.getTutorialSet(tutorials);
        return this;
    }


    /**
     * Sets the {@code Description} of the {@code Person} that we are building.
     */
    public PersonBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Major} of the {@code Person} that we are building.
     */
    public PersonBuilder withMajor(String major) {
        this.major = new Major(major);
        return this;
    }

    /**
     * Sets the {@code Year} of the {@code Person} that we are building.
     */
    public PersonBuilder withYear(String year) {
        this.year = new Year(year);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Nationality} of the {@code Person} that we are building.
     */
    public PersonBuilder withNationality(String nationality) {
        this.nationality = new Nationality(nationality);
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code Person} that we are building.
     */
    public PersonBuilder withGender(String gender) {
        this.gender = new Gender(gender);
        return this;
    }

    public Person build() {
        return new Person(name, major, year, email, description, tutorials, socialMediaLinks, nationality, gender);
    }

}
