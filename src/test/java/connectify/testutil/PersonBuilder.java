package connectify.testutil;

import java.util.HashSet;
import java.util.Set;

import connectify.model.company.Company;
import connectify.model.person.Person;
import connectify.model.person.PersonAddress;
import connectify.model.person.PersonEmail;
import connectify.model.person.PersonName;
import connectify.model.person.PersonNote;
import connectify.model.person.PersonPhone;
import connectify.model.person.PersonPriority;
import connectify.model.tag.Tag;
import connectify.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_NOTE = "";

    public static final String DEFAULT_PRIORITY = "1";

    private PersonName name;
    private PersonPhone personPhone;
    private PersonEmail personEmail;
    private PersonAddress personAddress;
    private Set<Tag> tags;
    private PersonNote note;
    private PersonPriority priority;

    private Company parentCompany;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new PersonName(DEFAULT_NAME);
        personPhone = new PersonPhone(DEFAULT_PHONE);
        personEmail = new PersonEmail(DEFAULT_EMAIL);
        personAddress = new PersonAddress(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        note = new PersonNote(DEFAULT_NOTE);
        priority = new PersonPriority(DEFAULT_PRIORITY);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        note = personToCopy.getNote();
        personPhone = personToCopy.getPhone();
        personEmail = personToCopy.getEmail();
        personAddress = personToCopy.getAddress();
        tags = new HashSet<>(personToCopy.getTags());
        priority = personToCopy.getPriority();
        parentCompany = personToCopy.getParentCompany();

    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new PersonName(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.personAddress = new PersonAddress(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.personPhone = new PersonPhone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.personEmail = new PersonEmail(email);
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code Person} that we are building.
     */
    public PersonBuilder withPriority(String priority) {
        this.priority = new PersonPriority(priority);
        return this;
    }

    /**
     * Sets the {@code Note} of the {@code Person} that we are building.
     * @param note
     * @return PersonBuilder
     */
    public PersonBuilder withNote(String note) {
        this.note = new PersonNote(note);
        return this;
    }

    /**
     * Sets the {@code ParentCompany} of the {@code Person} that we are building.
     * @param parentCompany
     * @return
     */
    public PersonBuilder withParentCompany(Company parentCompany) {
        this.parentCompany = parentCompany;
        return this;
    }

    /**
     * Builds a person object.
     * @return Person object
     */
    public Person build() {
        return new Person(name, personPhone, personEmail, personAddress, tags, note, priority, parentCompany);
    }

}
