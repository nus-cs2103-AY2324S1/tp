package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Error Messages
    public static final String AVAILABLE_WHILE_ANIMAL_NAMED_MESSAGE =
            "When an animal name is provided, availability should not be 'Available' or 'nil'.";
    public static final String ANIMAL_NAME_TYPE_MISMATCH_WHEN_UNAVAILABLE_MESSAGE =
            "When availability is 'NotAvailable', animal name and type have to either be both 'nil' or both not 'nil'.";
    public static final String NIL_WORD = "nil";
    private static final String NAME_CANNOT_BE_NIL_MESSAGE = "Name of fosterer cannot be 'nil'!";

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    private final Name animalName;
    private final Availability availability;
    private final Housing housing;
    private final AnimalType animalType;
    private String note;

    /**
     * Constructor for Person object. Ensures that required fields are not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Housing housing,
                  Availability availability, Name animalName, AnimalType animalType,
                  Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, housing, availability, animalName, animalType, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.housing = housing;
        this.availability = availability;
        this.animalName = animalName;
        this.animalType = animalType;
        this.tags.addAll(tags);

        if (Objects.equals(name.fullName, Person.NIL_WORD)) {
            throw new IllegalArgumentException(NAME_CANNOT_BE_NIL_MESSAGE);
        }

        if (!isAvailabilityValidWhenAnimalNameNotNil()) {
            throw new IllegalArgumentException(AVAILABLE_WHILE_ANIMAL_NAMED_MESSAGE);
        }
        if (!isAnimalNameTypeValidWhenNotAvailable()) {
            throw new IllegalArgumentException(ANIMAL_NAME_TYPE_MISMATCH_WHEN_UNAVAILABLE_MESSAGE);
        }
    }

    /**
     * Minimal constructor that fills non-required fields with placeholder values (nil).
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        this(name, phone, email, address, new Housing(NIL_WORD), new Availability(NIL_WORD),
                new Name(NIL_WORD), new AnimalType(NIL_WORD, new Availability(NIL_WORD)), tags);
    }

    /**
     * Returns boolean value to check if animal name is valid based on availability status.
     *
     * @return a boolean value which represents if animal name is valid.
     */
    boolean isAvailabilityValidWhenAnimalNameNotNil() {
        String avail = availability.value;
        if (!animalName.fullName.equals(Person.NIL_WORD)) {
            return !(avail.equals("Available") || avail.equals(Person.NIL_WORD));
        }
        return true;
    }

    /**
     * Returns boolean value to check if animal name and type are valid when NotAvailable.
     *
     * @return a boolean value which represents if animal name and type are valid.
     */
    boolean isAnimalNameTypeValidWhenNotAvailable() {
        String avail = availability.value;
        if (avail.equals("NotAvailable")) {
            String type = animalType.value;
            String name = animalName.fullName;
            return (name.equals("nil") && type.equals("nil"))
                    || (!name.equals("nil") && !type.equals("nil"));
        }
        return true;
    }

    public Name getAnimalName() {
        return animalName;
    }

    public Availability getAvailability() {
        return availability;
    }

    public Housing getHousing() {
        return housing;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public String getNote() {
        return Objects.requireNonNullElse(note, "");
    }

    public void setNote(String note) {
        this.note = note;
    }

    /**
     * Returns a map of fields and their existing attributes as strings.
     * Intended for use with predicates generated through the find command.
     *
     * @return a Map; keys include all publicly gettable fields as well as all tags,
     * and values are values of the respective fields, or {@code null} for tags.
     */
    public Map<String, String> getFieldsAndAttributes() {
        HashMap<String, String> map = new HashMap<>();
        tryPut(map, "name", getName());
        tryPut(map, "phone", getPhone());
        tryPut(map, "email", getEmail());
        tryPut(map, "address", getAddress());
        tryPut(map, "housing", getHousing());
        tryPut(map, "availability", getAvailability());
        tryPut(map, "animal name", getAnimalName());
        tryPut(map, "animal type", getAnimalType());
        getTags().forEach(tag -> map.put(tag.tagName, null));
        tryPut(map, "note", getNote());
        return map;
    }

    private void tryPut(Map<String, String> map, String key, Object value) {
        if (Objects.isNull(value)) {
            return;
        }
        map.put(key, value.toString());
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if person is an available fosterer.
     */
    public boolean isAvailableFosterer() {
        return availability.equals(Availability.AVAILABLE);
    }

    /**
     * Returns true if person is a current fosterer.
     * Person is a current fosterer if and only if they are not available, and is
     * currently fostering an animal.
     */
    public boolean isCurrentFosterer() {
        boolean isNotAvailable = this.availability.equals(Availability.NOT_AVAILABLE);
        boolean isAnimalNameNil = this.animalName.fullName.equals(Person.NIL_WORD);
        boolean isAnimalCurrentCatOrDog = this.animalType.equals(AnimalType.CURRENT_DOG)
                || this.animalType.equals(AnimalType.CURRENT_CAT);

        return isNotAvailable && !isAnimalNameNil && isAnimalCurrentCatOrDog;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && tags.equals(otherPerson.tags)
                && animalName.equals(otherPerson.animalName)
                && availability.equals(otherPerson.availability)
                && animalType.equals(otherPerson.animalType)
                && housing.equals(otherPerson.housing)
                && this.getNote().equals(otherPerson.getNote());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, housing, availability, animalName, animalType, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("tags", tags)
                .add("animalName", animalName)
                .add("availability", availability)
                .add("animalType", animalType)
                .add("housing", housing)
                .toString();
    }
}
