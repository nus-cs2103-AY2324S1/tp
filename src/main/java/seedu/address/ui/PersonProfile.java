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

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.Address;
import seedu.address.model.person.AnimalType;
import seedu.address.model.person.Availability;
import seedu.address.model.person.Email;
import seedu.address.model.person.Housing;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * An interactive UI component that contains all the fields associated with a {@link Person}, and their values.
 * Can be instructed to start editing any field. Handles editing logic, including confirmation or cancellation actions
 * by the user. At any point, a new Person object can be retrieved.
 */
public class PersonProfile extends UiPart<Region> {

    // region Super Constants
    private static final String FXML = "PersonProfile.fxml";
    // endregion

    // region String Constants
    private static final String VALID_FOSTERER = "Valid fosterer.";
    private static final String UNEXPECTED_ERROR = "Internal Error: fosterer was unexpectedly unable to be created.";
    private static final String EDITING_IN_PROGRESS = "Some fields are still under edit. \n"
            + "Please confirm or cancel them with enter or escape keys before proceeding.";

    private static final String INVALID_VALUE = "Invalid value for field: ";
    private static final String INVALID_AVAILABILITY_GROUP =
            "Availability, Animal Name and Animal Type are incompatible!";
    private static final String INVALID_ANIMAL_TYPE = "Animal Type and Availability are incompatible!";
    private static final String AVAILABLE_NIL_TYPE_MUST_NIL = "For 'nil' Availability, Animal Type must also be 'nil'!";
    private static final String AVAILABLE_NIL_NAME_MUST_NIL = "For 'nil' Availability, Animal Name must also be 'nil'!";
    private static final String AVAILABLE_NAME_MUST_NIL = "If fosterer is 'Available', Animal Name must be 'nil'!";
    private static final String AVAILABLE_TYPE_MUST_ABLE_OR_NIL =
            "If fosterer is 'Available', Animal Type must either be 'nil', or start with 'able'.";
    private static final String NOT_AVAILABLE_NAME_TYPE_BOTH_SAME =
            "If fosterer is 'NotAvailable', Animal Name and Type must either both be 'nil', or both not be 'nil'.";

    // endregion

    // region FXML
    @FXML private VBox vbox;
    // endregion

    // region Enums

    public enum Event {
        AFTER_CONFIRM, CANCEL, BEFORE_START_EDIT
    }

    public enum Field {
        NAME("Name", Name::isValidName, Name.MESSAGE_CONSTRAINTS),
        PHONE("Phone", Phone::isValidPhone, Phone.MESSAGE_CONSTRAINTS),
        EMAIL("Email", Email::isValidEmail, Email.MESSAGE_CONSTRAINTS),
        ADDRESS("Address", Address::isValidAddress, Address.MESSAGE_CONSTRAINTS),
        HOUSING("Housing", Housing::isValidHousing, Housing.MESSAGE_CONSTRAINTS),
        AVAILABILITY("Availability", Availability::isValidAvailability, Availability.MESSAGE_CONSTRAINTS),
        ANIMAL_NAME("Animal Name", Name::isValidName, Name.MESSAGE_CONSTRAINTS),
        ANIMAL_TYPE("Animal Type", AnimalType::isValidAnimalType, AnimalType.MESSAGE_CONSTRAINTS);

        private final String name;
        private final Predicate<String> isValid;
        private final String hint;

        Field(String name, Predicate<String> isValid, String hint) {
            this.name = name;
            this.isValid = isValid;
            this.hint = hint;
        }

        public String getDisplayName() {
            return name;
        }
        public boolean isValid(String string) {
            return isValid.test(string);
        }
        public String getHint() {
            return hint;
        }
    }

    // endregion

    // region Fields

    // region Final
    private final MainWindow mainWindow;
    private final Map<Field, String> fields = new EnumMap<>(Field.class);
    private final Set<String> tags = new HashSet<>();

    private final Map<Field, PersonProfileField> uiElements = new EnumMap<>(Field.class);
    private final Map<Event, List<Runnable>> eventHandlers = new EnumMap<>(Event.class);
    // endregion

    private Person person;
    // endregion

    // region Constructor

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

        //event handlers
        Arrays.stream(Event.values()).forEach(event -> eventHandlers.put(event, new ArrayList<>()));
        setEventHandler(Event.AFTER_CONFIRM, this::handleFieldLockIn);
        setEventHandler(Event.CANCEL, this::handleFieldLockIn);

        //ui
        Arrays.stream(Field.values()).forEach(field ->
                uiElements.put(field, new PersonProfileField(this, field))
        );
        uiElements.values().stream()
                .map(UiPart::getRoot)
                .forEach(vboxChildren::add);
        //todo deal with tags
    }

    // endregion

    // region Internal Event Handlers

    private void handleFieldLockIn() {
        if (handleAvailabilityGroupInvalid()) {
            return;
        }

        if (editingInProgress()) {
            sendEditingInProgress();
            return;
        }

        try {
            createAndUpdatePerson();
            sendPersonCreated();
        } catch (IllegalArgumentException ignored) {
            sendUnexpectedError();
        }
    }

    // endregion

    // region Internal Helpers

    private void createAndUpdatePerson() {
        this.person = null;

        Name name = new Name(fields.get(Field.NAME));
        Phone phone = new Phone(fields.get(Field.PHONE));
        Email email = new Email(fields.get(Field.EMAIL));
        Address address = new Address(fields.get(Field.ADDRESS));
        Housing housing = new Housing(fields.get(Field.HOUSING));
        Availability availability = new Availability(fields.get(Field.AVAILABILITY));
        Name animalName = new Name(fields.get(Field.ANIMAL_NAME));
        AnimalType animalType = new AnimalType(fields.get(Field.ANIMAL_TYPE), availability);

        this.person = new Person(name, phone, email, address, housing, availability, animalName, animalType, getTags());
    }

    private Set<Tag> getTags() {
        Set<Tag> set = new HashSet<>();
        tags.stream().map(Tag::new).forEach(set::add);
        return set;
    }

    private boolean handleAvailabilityGroupInvalid() {
        Availability availability;
        Name animalName;
        AnimalType animalType;

        try {
            availability = new Availability(fields.get(Field.AVAILABILITY));
            animalName = new Name(fields.get(Field.ANIMAL_NAME));
        } catch (IllegalArgumentException ignored) {
            sendUnexpectedError();
            return true;
        }

        try {
            animalType = new AnimalType(fields.get(Field.ANIMAL_TYPE), availability);
        } catch (IllegalArgumentException ignored) {
            sendConflict(INVALID_ANIMAL_TYPE, Field.ANIMAL_TYPE, Field.AVAILABILITY);
            return true;
        }

        //noinspection RedundantIfStatement
        if(!checkAvailabilityGroupValidElseFeedback(availability, animalName, animalType)) {
            return true;
        }

        return false;
    }

    private boolean editingInProgress() {
        return uiElements.values().stream().anyMatch(PersonProfileField::isEditing);
    }

    private void sendFeedback(String string) {
        mainWindow.sendFeedback(string);
    }

    /**
     * Checks if the availability, animal name, and animal type objects provided follow validity rules
     * used in the Person constructor.
     */
    private boolean checkAvailabilityGroupValidElseFeedback (
            Availability availability, Name animalName, AnimalType animalType
    ) throws IllegalArgumentException {
        String avail = availability.value;
        boolean isNameNil = Objects.equals(animalName.fullName, "nil");
        boolean isTypeNil = Objects.equals(animalType.value, "nil");
        switch (avail) {
        case "nil":
            if (!isTypeNil) {
                sendConflict(AVAILABLE_NIL_TYPE_MUST_NIL, Field.AVAILABILITY, Field.ANIMAL_TYPE);
                return false;
            } else if (!isNameNil) {
                sendConflict(AVAILABLE_NIL_NAME_MUST_NIL, Field.AVAILABILITY, Field.ANIMAL_NAME);
                return false;
            } else {
                return true;
            }
        case "Available":
            boolean isTypeAbleOrNil = isTypeNil || animalType.value.startsWith("able.");
            if (!isNameNil) {
                sendConflict(AVAILABLE_NAME_MUST_NIL, Field.AVAILABILITY, Field.ANIMAL_NAME);
                return false;
            } else if (!isTypeAbleOrNil) {
                sendConflict(AVAILABLE_TYPE_MUST_ABLE_OR_NIL, Field.AVAILABILITY, Field.ANIMAL_TYPE);
            } else {
                return true;
            }
        case "NotAvailable":
            if (isNameNil != isTypeNil) {
                sendConflict(
                        NOT_AVAILABLE_NAME_TYPE_BOTH_SAME, Field.AVAILABILITY, Field.ANIMAL_TYPE, Field.ANIMAL_NAME
                );
                return false;
            } else {
                return true;
            }
        default:
            sendUnexpectedError();
            return false;
        }
    }

    // endregion

    // region User Feedback

    private void sendEditingInProgress() {
        sendFeedback(EDITING_IN_PROGRESS);
    }

    private void sendPersonCreated() {
        mainWindow.sendFeedback(VALID_FOSTERER);
    }

    private void sendUnexpectedError() {
        sendFeedback(UNEXPECTED_ERROR);
    }

    private void sendConflict(String conflictMessage, Field... fields) {
        mainWindow.sendFeedback(INVALID_AVAILABILITY_GROUP + "\n" + conflictMessage);
        Objects.requireNonNull(fields);
        Arrays.stream(fields).map(uiElements::get).forEach(PersonProfileField::indicateIsError);
    }

    // endregion

    // region Package

    void updateField(Field field, String value) {
        fields.put(field, value);
    }

    void triggerEvent(Event event) {
        List<Runnable> runnables = eventHandlers.get(event);
        runnables.forEach(Runnable::run);
    }

    void sendHint(Field field) {
        mainWindow.sendFeedback(field.getHint());
    }

    String getValueOfField(Field field) {
        return fields.get(field);
    }

    void sendInvalidInput(Field field) {
        sendFeedback(INVALID_VALUE + field.getDisplayName() + "\n" + field.getHint());
    }

    // endregion

    // region External

    /**
     * Sets focus to the UI element responsible for editing the provided field.
     *
     * @param field the field of Person that should currently be under edit.
     */
    public void setFocus(Field field) {
        uiElements.get(field).setFocus();
    }

    /**
     * Gets the last valid Person object.
     *
     * @return valid Person object, or null if unavailable.
     */
    public Person getPerson() {
        return person;
    }

    /**
     * Sets a Runnable to run when a specific {@link Event} occurs.
     *
     * @param event trigger to run the handler.
     * @param handler Runnable to run when the event occurs.
     */
    public void setEventHandler(Event event, Runnable handler) {
        eventHandlers.get(event).add(handler);
    }

    // endregion
}
