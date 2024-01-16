package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    public static final String MESSAGE_DUPLICATE_TAG = "Tags list contains duplicate tag(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    private final List<Map<String, String>> tagList = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons and tags.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                       @JsonProperty("tagList") List<Map<String, String>> tags) {
        this.persons.addAll(persons);
        this.tagList.addAll(tags);
    }


    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        ObservableList<Tag> internalTagList = source.getTagList();
        for (Tag tag : internalTagList) {
            Map<String, String> map = new HashMap<>();
            map.put("tagCategory", tag.tagCategory);
            map.put("tagName", tag.tagName);
            this.tagList.add(map);
        }
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (addressBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPerson(person);
        }
        for (Map<String, String> tagData : tagList) {
            String tagCategory = tagData.get("tagCategory");
            String tagName = tagData.get("tagName");

            Tag tag = new Tag(tagName, tagCategory);

            if (!addressBook.hasTag(tag)) {
                addressBook.addTag(tag);
            }
        }
        return addressBook;
    }

}
