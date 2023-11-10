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
 * by the user. At any point, a Person object can be retrieved.
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
    private static final String FIELDS_ARE_INCOMPATIBLE = "Some fields are incompatible!";
    private static final String AVAILABLE_NIL_TYPE_MUST_NIL = "For 'nil' Availability, Animal Type must also be 'nil'.";
    private static final String AVAILABLE_NIL_NAME_MUST_NIL = "For 'nil' Availability, Animal Name must also be 'nil'.";
    private static final String AVAILABLE_NAME_MUST_NIL = "If fosterer is 'Available', Animal Name must be 'nil'.";
    private static final String AVAILABLE_TYPE_MUST_ABLE_OR_NIL =
            "If fosterer is 'Available', Animal Type must either be 'nil', or start with 'able'.";
    private static final String NOT_AVAILABLE_NAME_TYPE_BOTH_SAME =
            "If fosterer is 'NotAvailable', Animal Name and Type must either both be 'nil', or both not be 'nil'.";
    private static final String FIELD_IS_MISSING = "Field is required to be not 'nil': ";
    private static final String NOT_AVAILABLE_TYPE_NOT_CURRENT =
            "If fosterer is 'NotAvailable', Animal Type must either be 'nil' or begin with 'current'.";

    // endregion

    // region FXML
    @FXML private VBox vbox;

    // endregion

    // region Enums

    /**
     * Represents possible times in UI execution flow where handlers/listeners can be added.
     * @see #setEventHandler(Event, Runnable)
     */
    public enum Event {
        AFTER_CONFIRM, CANCEL, BEFORE_START_EDIT
    }

    /**
     * Represents a field of a Person as displayed in the UI.
     */
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
    private Person person;
    private final Map<Field, String> fields = new EnumMap<>(Field.class);
    private Set<Tag> tags;
    private String note;

    private final Map<Field, PersonProfileField> uiElements = new EnumMap<>(Field.class);
    private PersonProfileTags tagUI;
    private PersonProfileNote noteUI;
    private final Map<Event, List<Runnable>> eventHandlers = new EnumMap<>(Event.class);
    // endregion

    // endregion

    // region Constructor

    /**
     * Creates a UI showcasing details of the given {@link Person}.
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
        tags = new HashSet<>();
        tags.addAll(person.getTags());
        note = person.getNote();
        initialize();
    }

    /**
     * Creates a UI showcasing details of a new {@link Person}, starting from blanks.
     */
    public PersonProfile(MainWindow mainWindow) {
        super(FXML);
        this.mainWindow = mainWindow;
        this.person = null;

        Arrays.stream(Field.values()).forEach(field -> fields.put(field, null));
        tags = new HashSet<>();
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
        tagUI = new PersonProfileTags(this);
        vboxChildren.add(tagUI.getRoot());
        noteUI = new PersonProfileNote(this);
        vboxChildren.add(noteUI.getRoot());
    }

    // endregion

    // region Internal Event Handlers

    private void handleFieldLockIn() {
        if (handleIfRequiredFieldsNil()) {
            return;
        }

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

        Name name = new Name(getNonNullOrNil(Field.NAME));
        Phone phone = new Phone(getNonNullOrNil(Field.PHONE));
        Email email = new Email(getNonNullOrNil(Field.EMAIL));
        Address address = new Address(getNonNullOrNil(Field.ADDRESS));
        Housing housing = new Housing(getNonNullOrNil(Field.HOUSING));
        Availability availability = new Availability(getNonNullOrNil(Field.AVAILABILITY));
        Name animalName = new Name(getNonNullOrNil(Field.ANIMAL_NAME));
        AnimalType animalType = new AnimalType(getNonNullOrNil(Field.ANIMAL_TYPE), availability);

        this.person = new Person(name, phone, email, address, housing, availability, animalName, animalType, this.tags);
        person.setNote(note);
    }

    private boolean handleAvailabilityGroupInvalid() {
        Availability availability;
        Name animalName;

        try {
            availability = new Availability(getNonNullOrNil(Field.AVAILABILITY));
            animalName = new Name(getNonNullOrNil(Field.ANIMAL_NAME));
        } catch (Exception ignored) {
            sendUnexpectedError();
            return true;
        }

        if (!checkAvailabilityGroupValidElseFeedback(availability, animalName, getNonNullOrNil(Field.ANIMAL_TYPE))) {
            return true;
        }

        try {
            new AnimalType(getNonNullOrNil(Field.ANIMAL_TYPE), availability);
        } catch (Exception ignored) {
            sendUnexpectedError();
            return true;
        }

        return false;
    }

    private boolean editingInProgress() {
        return uiElements.values().stream().anyMatch(PersonProfileField::isEditing)
                || tagUI.isEditing() || noteUI.isEditing();
    }

    private void sendFeedback(String string) {
        mainWindow.sendFeedback(string);
    }

    /**
     * Checks if the availability, animal name, and animal type objects provided follow validity rules
     * used in the Person constructor.
     */
    private boolean checkAvailabilityGroupValidElseFeedback(
            Availability availability, Name animalName, String animalType
    ) throws IllegalArgumentException {
        String avail = availability.value;
        boolean isNameNil = Objects.equals(animalName.fullName, "nil");
        boolean isTypeNil = Objects.equals(animalType, "nil");
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
            boolean isTypeAbleOrNil = isTypeNil || animalType.startsWith("able.");
            if (!isNameNil) {
                sendConflict(AVAILABLE_NAME_MUST_NIL, Field.AVAILABILITY, Field.ANIMAL_NAME);
                return false;
            } else if (!isTypeAbleOrNil) {
                sendConflict(AVAILABLE_TYPE_MUST_ABLE_OR_NIL, Field.AVAILABILITY, Field.ANIMAL_TYPE);
                return false;
            } else {
                return true;
            }
        case "NotAvailable":
            boolean isTypeCurrent = animalType.startsWith("current.");
            if (isNameNil != isTypeNil) {
                sendConflict(
                        NOT_AVAILABLE_NAME_TYPE_BOTH_SAME, Field.AVAILABILITY, Field.ANIMAL_TYPE, Field.ANIMAL_NAME
                );
                return false;
            } else if (!isTypeNil && !isTypeCurrent) {
                sendConflict(
                        NOT_AVAILABLE_TYPE_NOT_CURRENT, Field.AVAILABILITY, Field.ANIMAL_TYPE
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

    private boolean handleIfRequiredFieldsNil() {
        Field[] requiredFields = {Field.NAME, Field.PHONE, Field.EMAIL, Field.ADDRESS};
        Optional<Field> nullOrNilField = Arrays.stream(requiredFields)
                .filter(field -> isNullOrNil(getNonNullOrNil(field)))
                .findFirst();
        if (nullOrNilField.isPresent()) {
            sendMissing(nullOrNilField.get());
            return true;
        }
        return false;
    }

    private boolean isNullOrNil(String string) {
        return string == null || string.equals("nil");
    }

    private String getNonNullOrNil(Field field) {
        String value = fields.get(field);
        if (value == null) {
            return "nil";
        }
        return value;
    }

    // endregion

    // region User Feedback

    private void sendEditingInProgress() {
        sendFeedback(EDITING_IN_PROGRESS);
    }

    private void sendPersonCreated() {
        sendFeedback(VALID_FOSTERER);
    }

    private void sendUnexpectedError() {
        sendFeedback(UNEXPECTED_ERROR);
    }

    private void sendConflict(String conflictMessage, Field... fields) {
        sendFeedback(FIELDS_ARE_INCOMPATIBLE + "\n" + conflictMessage);
        Objects.requireNonNull(fields);
        Arrays.stream(fields).map(uiElements::get).forEach(PersonProfileField::indicateIsError);
    }

    private void sendMissing(Field field) {
        sendFeedback(FIELD_IS_MISSING + field.getDisplayName());
        uiElements.get(field).indicateIsError();
    }

    // endregion

    // region Package

    void updateField(Field field, String value) {
        fields.put(field, value);
    }

    void updateTags(Set<Tag> tags) {
        this.tags = tags;
    }

    void updateNote(String note) {
        this.note = note;
    }

    void triggerEvent(Event event) {
        List<Runnable> runnables = eventHandlers.get(event);
        runnables.forEach(Runnable::run);
    }

    void sendHint(Field field) {
        sendFeedback(field.getHint());
    }

    void sendHint(String hint) {
        sendFeedback(hint);
    }

    String getValueOfField(Field field) {
        return getNonNullOrNil(field);
    }

    Set<Tag> getTags() {
        return tags;
    }

    String getNote() {
        return note;
    }

    void sendInvalidInput(Field field) {
        sendFeedback(INVALID_VALUE + field.getDisplayName() + "\n" + field.getHint());
    }

    void sendInvalidInput(String fieldName, String message) {
        sendFeedback(INVALID_VALUE + fieldName + "\n" + message);
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
     * Sets focus to the UI element responsible for editing tags.
     */
    public void setFocusTags() {
        tagUI.setFocus();
    }

    /**
     * Sets focus to the UI element responsible for editing notes.
     */
    public void setFocusNotes() {
        noteUI.setFocus();
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

    public boolean isStillEditing() {
        return editingInProgress() || person == null;
    }

    /**
     * Sets a Boolean that represents whether the profile page is in save confirmation dialog
     * by mapping through each PersonProfileField and setting their inConfirmationDialog boolean values.
     * @param isInConfirmationDialog is a boolean the tells whether the current window is is showing confirmation dialog
     */
    public void setIsInConfirmationDialog(boolean isInConfirmationDialog) {
        uiElements.values().stream()
                .forEach(field -> field.setIsInConfirmationDialog(isInConfirmationDialog));
        tagUI.setIsInConfirmationDialog(isInConfirmationDialog);
        noteUI.setIsInConfirmationDialog(isInConfirmationDialog);
    }

    /**
     * Resets the field values back to the most recently edited valid details
     * and change the red colored text, if there is any, back to black colored text.
     */
    public void resetValues() {
        if (person == null) {
            return;
        }

        fields.keySet().forEach(field -> {
            if (field == Field.NAME) {
                updateField(field, person.getName().toString());
            } else if (field == Field.PHONE) {
                updateField(field, person.getPhone().toString());
            } else if (field == Field.EMAIL) {
                updateField(field, person.getEmail().toString());
            } else if (field == Field.ADDRESS) {
                updateField(field, person.getAddress().toString());
            } else if (field == Field.HOUSING) {
                updateField(field, person.getHousing().toString());
            } else if (field == Field.AVAILABILITY) {
                updateField(field, person.getAvailability().toString());
            } else if (field == Field.ANIMAL_NAME) {
                updateField(field, person.getAnimalName().toString());
            } else if (field == Field.ANIMAL_TYPE) {
                updateField(field, person.getAnimalType().toString());
            }
        });

        uiElements.values().stream()
                .forEach(field -> {
                    field.refresh();
                });
    }

    // endregion
}
