package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Github;
import seedu.address.model.person.LinkedIn;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.ScoreList;
import seedu.address.model.person.Status;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final List<Map<String, String>> tags = new ArrayList<>();
    private final JsonAdaptedScoreList scoreList;
    private final String linkedIn;
    private final String github;

    private final String remark;
    private final String status;



    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("tags") List<Map<String, String>> tags,
            @JsonProperty("scoreList") JsonAdaptedScoreList scoreList,
            @JsonProperty("linkedIn") String linkedIn,
            @JsonProperty("github") String github,
            @JsonProperty("remark") String remark, @JsonProperty("status") String status) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        if (tags != null) {
            this.tags.addAll(tags);
        }
        this.linkedIn = linkedIn;
        this.github = github;
        this.remark = remark;
        this.status = status;
        this.scoreList = scoreList;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        Set<Tag> personTags = source.getTags();
        for (Tag tag : personTags) {
            Map<String, String> map = new HashMap<>();
            map.put("tagCategory", tag.tagCategory);
            map.put("tagName", tag.tagName);
            this.tags.add(map);
        }
        scoreList = new JsonAdaptedScoreList(source.getScoreList());
        linkedIn = source.getLinkedIn().value;
        github = source.getGithub().value;
        remark = source.getRemark().value;
        status = source.getStatus().getValue();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (Map<String, String> tagData : tags) {
            String tagCategory = tagData.get("tagCategory");
            String tagName = tagData.get("tagName");
            if (!Tag.isValidTagName(tagName)) {
                throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
            }

            personTags.add(new Tag(tagName, tagCategory));
        }
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);
        if (remark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName()));
        }
        final Remark modelRemark = new Remark(remark);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        Person p = new Person(modelName, modelPhone, modelEmail, modelAddress, modelRemark, modelTags);
        if (linkedIn != null) {
            p.setLinkedIn(new LinkedIn(linkedIn));
        }
        if (github != null) {
            p.setGithub(new Github(github));
        }
        if (status != null) {
            p.setStatus(new Status(status));
        }
        ScoreList modelScoreList = (scoreList != null) ? scoreList.toModelType() : new ScoreList();
        p.setScoreList(modelScoreList);
        return p;
    }
}
