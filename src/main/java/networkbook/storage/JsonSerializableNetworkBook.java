package networkbook.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import networkbook.commons.exceptions.IllegalValueException;
import networkbook.model.NetworkBook;
import networkbook.model.ReadOnlyNetworkBook;
import networkbook.model.person.Person;

/**
 * An Immutable NetworkBook that is serializable to JSON format.
 */
@JsonRootName(value = "networkbook")
class JsonSerializableNetworkBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableNetworkBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableNetworkBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyNetworkBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableNetworkBook}.
     */
    public JsonSerializableNetworkBook(ReadOnlyNetworkBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this network book into the model's {@code NetworkBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public NetworkBook toModelType() throws IllegalValueException {
        NetworkBook networkBook = new NetworkBook();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (networkBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            networkBook.addPerson(person);
        }
        return networkBook;
    }

}
