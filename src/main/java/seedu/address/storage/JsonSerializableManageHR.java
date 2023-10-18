package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ManageHr;
import seedu.address.model.ReadOnlyManageHr;
import seedu.address.model.person.Person;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "managehr")
class JsonSerializableManageHr {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableManageHR} with the given persons.
     */
    @JsonCreator
    public JsonSerializableManageHr(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyManageHR} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableManageHR}.
     */
    public JsonSerializableManageHr(ReadOnlyManageHr source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code ManageHR} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ManageHr toModelType() throws IllegalValueException {
        ManageHr manageHR = new ManageHr();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (manageHR.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            manageHR.addPerson(person);
        }
        return manageHR;
    }

}
