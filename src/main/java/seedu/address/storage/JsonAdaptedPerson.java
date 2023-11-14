package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.TimeIntervalList;
import seedu.address.model.group.GroupList;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final List<JsonAdaptedGroup> groupList = new ArrayList<>();
    private final List<JsonAdaptedTime> freeTimeList = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email,
            @JsonProperty("groupList") List<JsonAdaptedGroup> groupList,
            @JsonProperty("freeTimeList") List<JsonAdaptedTime> freeTimeList
    ) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        if (groupList != null) {
            this.groupList.addAll(groupList);
        }
        if (freeTimeList != null) {
            this.freeTimeList.addAll(freeTimeList);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        groupList.addAll(source.getGroups().toStream()
                .map(JsonAdaptedGroup::new)
                .collect(Collectors.toList()));
        freeTimeList.addAll(source.getTime().toStream()
                .map(JsonAdaptedTime::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
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

        if (groupList == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    GroupList.class.getSimpleName()));
        }

        GroupList modelGroupList = new GroupList();
        for (JsonAdaptedGroup group : groupList) {
            modelGroupList.add(group.toModelType());
        }

        if (freeTimeList == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TimeIntervalList.class.getSimpleName()));
        }

        TimeIntervalList modelTimeIntervalListList = new TimeIntervalList();
        for (JsonAdaptedTime freeTime : freeTimeList) {
            modelTimeIntervalListList.addTime(freeTime.toModelType());
        }

        return new Person(modelName, modelPhone, modelEmail, modelGroupList, modelTimeIntervalListList);
    }

}
