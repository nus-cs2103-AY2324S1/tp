package seedu.address.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.AnimalType;
import seedu.address.model.person.Availability;
import seedu.address.model.person.Email;
import seedu.address.model.person.Housing;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * An interactive UI component that contains all the fields associated with a {@link Person}, and their values.
 * Can be instructed to start editing any field. Handles editing logic, including confirmation or cancellation actions
 * by the user. At any point, a new Person object can be retrieved.
 */
public class PersonProfile extends UiPart<Region> {
    private static final String FXML = "PersonProfile.fxml";

    @FXML private VBox vbox;

    enum Event {
        CONFIRM_SUCCESS, CONFIRM_FAIL, CANCEL
    }

    public enum Field {
        NAME("Name", Name::isValidName),
        PHONE("Phone", Phone::isValidPhone),
        EMAIL("Email", Email::isValidEmail),
        ADDRESS("Address", Address::isValidAddress),
        HOUSING("Housing", Housing::isValidHousing),
        AVAILABILITY("Availability", Availability::isValidAvailability),
        ANIMAL_NAME("Animal Name", Name::isValidName),
        ANIMAL_TYPE("Animal Type", AnimalType::isValidAnimalType);

        private final String name;
        private final Predicate<String> isValid;

        Field(String name, Predicate<String> isValid) {
            this.name = name;
            this.isValid = isValid;
        }

        public String getDisplayName() {
            return name;
        }
        public boolean isValid(String string) {
            return isValid.test(string);
        }
    }

    private final MainWindow mainWindow;

    private Person person;
    private final Map<Field, String> fields = new EnumMap<>(Field.class);
    private final Set<String> tags = new HashSet<>();
    private String note;

    private final Map<Field, PersonProfileField> uiElements = new EnumMap<>(Field.class);
    private final Map<Event, Runnable> handlers = new EnumMap<>(Event.class);

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonProfile(Person person, MainWindow mainWindow) {
        super(FXML);
        this.mainWindow = mainWindow;
        this.person = person;

        fields.put(Field.NAME, person.getName().toString());
        fields.put(Field.PHONE, person.getPhone().toString());
        fields.put(Field.EMAIL, person.getEmail().toString());
        fields.put(Field.ADDRESS, person.getAddress().toString());
        fields.put(Field.HOUSING, person.getHousing().toString());
        fields.put(Field.AVAILABILITY, person.getAvailability().toString());
        fields.put(Field.ANIMAL_NAME, person.getAnimalName().toString());
        fields.put(Field.ANIMAL_TYPE, person.getAnimalType().toString());
        person.getTags().stream().map(Tag::getTagName).forEach(tags::add);
        initialize();
    }

    public PersonProfile(MainWindow mainWindow) {
        super(FXML);
        this.mainWindow = mainWindow;
        this.person = null;

        Arrays.stream(Field.values()).forEach(field -> fields.put(field, null));
        initialize();
    }

    private void initialize() {
        //clear
        ObservableList<Node> vboxChildren = vbox.getChildren();
        vboxChildren.clear();

        //header
        PersonProfileHeader header = new PersonProfileHeader();
        vboxChildren.add(header.getRoot());

        //fields
        Arrays.stream(Field.values()).forEach(field ->
                uiElements.put(field, new PersonProfileField(this, field))
        );
        uiElements.values().stream()
                .map(UiPart::getRoot)
                .forEach(vboxChildren::add);
        vboxChildren.add(new PersonProfileHeader().getRoot());
        vboxChildren.add(new PersonProfileHeader().getRoot());
        vboxChildren.add(new PersonProfileHeader().getRoot());
        vboxChildren.add(new PersonProfileHeader().getRoot()); //todo remove extra elements
        //todo deal with tags
    }

    public void setFocus(Field field) {
        uiElements.get(field).setFocus();
    }

    public String getValueOfField(Field field) { //todo figure out if this is necessary
        return fields.get(field);
    }

    public boolean replaceFieldIfValid(Field field, String value) {
        fields.put(field, value);

        boolean didNotHavePerson = person == null;

        try {
            createPerson();
        } catch (IllegalArgumentException exception) {
            mainWindow.sendFeedback(exception.getMessage());
            return didNotHavePerson;
        }

        if (didNotHavePerson) {
            mainWindow.sendFeedback("Valid fosterer has been created!");
        } else {
            mainWindow.sendFeedback("Valid fosterer.");
        }
        confirmAll();
        return true;
    }

    private void confirmAll() {
        uiElements.values().forEach(PersonProfileField::confirmIfSubmitted);
    }

    private void createPerson() throws IllegalArgumentException {
        Field[] requiredFields = {Field.NAME, Field.PHONE, Field.EMAIL, Field.ADDRESS};
        Optional<Field> missingRequiredField = Arrays.stream(requiredFields)
                .filter(field -> fields.get(field) == null).findAny();
        if (missingRequiredField.isPresent()) {
            throw new IllegalArgumentException(missingRequiredField.get().name + " is required, but empty!");
        }

        Name name = new Name(fields.get(Field.NAME));
        Phone phone = new Phone(fields.get(Field.PHONE));
        Email email = new Email(fields.get(Field.EMAIL));
        Address address = new Address(fields.get(Field.ADDRESS));
        Housing housing = fields.get(Field.HOUSING) != null
                ? new Housing(fields.get(Field.HOUSING))
                : null;
        Availability availability = fields.get(Field.AVAILABILITY) != null
                ? new Availability(fields.get(Field.AVAILABILITY))
                : null;
        Name animalName = fields.get(Field.ANIMAL_NAME) != null
                ? new Name(fields.get(Field.ANIMAL_NAME))
                : null;
        AnimalType animalType = fields.get(Field.ANIMAL_TYPE) != null
                ? new AnimalType(fields.get(Field.ANIMAL_TYPE), fields.get(Field.AVAILABILITY))
                : null;

        this.person = new Person(name, phone, email, address, housing, availability, animalName, animalType, getTags());
    }

    private Set<Tag> getTags() {
        Set<Tag> set = new HashSet<>();
        tags.stream().map(Tag::new).forEach(set::add);
        return set;
    }

    /**
     * Gets the last valid Person object.
     *
     * @return valid Person object, or null if unavailable.
     */
    public Person getPerson() {
        return person;
    }

    void setEventHandler(Event event, Runnable handler) {
        handlers.put(event, handler);
    }

    void triggerEvent(Event event) {
        Runnable runnable = handlers.get(event);
        if (Objects.nonNull(runnable)) {
            runnable.run();
        }
    }

    void sendFeedback(String feedback) {
        mainWindow.sendFeedback(feedback);
    }
}
