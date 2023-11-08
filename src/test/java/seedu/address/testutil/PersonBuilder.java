package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Github;
import seedu.address.model.person.LinkedIn;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Score;
import seedu.address.model.person.ScoreList;
import seedu.address.model.person.Status;
import seedu.address.model.person.StatusTypes;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_REMARK = "";
    public static final String DEFAULT_LINKEDIN = "";
    public static final String DEFAULT_GITHUB = "";

    public static final int DEFAULT_SCORE_VALUE = 0;


    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Remark remark;

    private ScoreList scoreList;

    private Set<Tag> tags;
    private Status status;
    private LinkedIn linkedIn;
    private Github github;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        remark = new Remark(DEFAULT_REMARK);
        scoreList = new ScoreList();
        tags = new HashSet<>();
        status = new Status(); // default status is preliminary
        linkedIn = new LinkedIn(DEFAULT_LINKEDIN);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        remark = personToCopy.getRemark();
        scoreList = personToCopy.getScoreList();
        tags = new HashSet<>(personToCopy.getTags());
        linkedIn = personToCopy.getLinkedIn();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
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
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
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
     * Sets the {@code Remark} of the {@code Person} that we are building.
     */
    public PersonBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    /**
     * Sets the {@code ScoreList} of the {@code Person} that we are building.
     * @param scoreList the score list to be set
     * @return PersonBuilder
     */
    public PersonBuilder withScoreList(ScoreList scoreList) {
        this.scoreList = scoreList;
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code Person} that we are building.
     * @param score the score to be set
     * @return PersonBuilder
     */
    public PersonBuilder withInterviewScore(int score) {
        ScoreList newScoreList = new ScoreList();
        newScoreList.updateScoreList(new Tag("Interview", "assessment"), new Score(score));
        this.scoreList = newScoreList;
        return this;
    }

    /**
     * Sets the {@code LinkedIn} of the {@code Person} that we are building.
     * @param username
     * @return PersonBuilder
     */
    public PersonBuilder withLinkedIn(String username) {
        this.linkedIn = new LinkedIn(username);
        return this;
    }


    /**
     * Sets the {@code Status} of the {@code Person} that we are building.
     */
    public PersonBuilder withStatus(String status) {
        switch (status.toLowerCase()) {
        case "interviewed":
            this.status.setStatusType(StatusTypes.INTERVIEWED);
            break;
        case "rejected":
            this.status.setStatusType(StatusTypes.REJECTED);
            break;
        case "offered":
            this.status.setStatusType(StatusTypes.OFFERED);
            break;
        default:
            this.status.setStatusType(StatusTypes.PRELIMINARY);
        }
        return this;
    }

    /**
     * Builds a person with the given parameters.
     * @return Person
     */
    public Person build() {
        Person createdPerson = new Person(name, phone, email, address, remark, tags);
        createdPerson.setStatus(status);
        createdPerson.setScoreList(scoreList);
        return createdPerson;
    }

    /**
     * Sets the {@code Github} of the {@code Person} that we are building.
     * @param username
     * @return
     */
    public PersonBuilder withGithub(String username) {
        this.github = new Github(username);
        return this;
    }
}
