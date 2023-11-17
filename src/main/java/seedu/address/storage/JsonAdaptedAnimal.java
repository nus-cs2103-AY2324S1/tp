package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.animal.AdmissionDate;
import seedu.address.model.animal.Animal;
import seedu.address.model.animal.Breed;
import seedu.address.model.animal.DateOfBirth;
import seedu.address.model.animal.Name;
import seedu.address.model.animal.PetId;
import seedu.address.model.animal.Sex;
import seedu.address.model.animal.Species;
import seedu.address.model.animal.TaskList;

/**
 * A Jackson-friendly adapted version of the {@link Animal} model class.
 * It holds the details of the {@code Animal} that are serializable to JSON format.
 *
 * <p>This class is part of the storage component and is necessary for the reading and writing
 * of {@code Animal} objects to and from JSON files. It is designed to provide a level of abstraction
 * which separates the internal representation of an {@code Animal} from how it is serialized to and from
 * JSON format. This allows for greater flexibility, as the internal representation of {@code Animal}
 * can be changed without affecting the serialization protocol.</p>
 *
 * <p>Note: Changes to the domain model (specifically, the {@code Animal} class) may require
 * corresponding updates to this class to ensure accurate data representation between the application's internal
 * data and its JSON-formatted persisted state.</p>
 *
 * <p>The {@code JsonAdaptedAnimal} includes the following operations:</p>
 * <ul>
 *   <li>Storing minimal data necessary for the accurate reconstruction of an {@code Animal}.</li>
 *   <li>Converting from a JSON-friendly adapted object version to the domain model version and vice versa.</li>
 *   <li>Validating and handling missing or corrupt data attributes that are necessary for the accurate
 *       reconstruction of an {@code Animal}.</li>
 * </ul>
 *
 * <p>Developers modifying this class should ensure consistency between the {@code Animal} model and
 * {@code JsonAdaptedAnimal} to maintain the system's integrity when persisting data.</p>
 */
public class JsonAdaptedAnimal {
    public static final String MISSING_FIELDS_MESSAGE_FORMAT = "The following Animal's field(s) is/are MISSING:\n%s";
    public static final String INVALID_FIELDS_MESSAGE_FORMAT = "The following Animal's field(s) is/are INVALID:\n%s";
    public static final String SEPARATOR = ", ";

    // Constant declaration of Keys used for the various attribute in the JSON representation of an Animal.
    public static final String NAME_KEY = "name";
    public static final String PET_ID_KEY = "petId";
    public static final String DATE_OF_BIRTH_KEY = "dateOfBirth";
    public static final String SEX_KEY = "sex";
    public static final String SPECIES_KEY = "species";
    public static final String BREED_KEY = "breed";
    public static final String ADMISSION_DATE_KEY = "admissionDate";
    public static final String TASK_LIST_KEY = "taskList";


    // Attributes of Animal that are implemented. Omission of HealthStatus and Notes is intentional.
    private final String name;
    private final String petId;
    private final String sex;
    private final String species;
    private final String breed;
    private final String dateOfBirth;
    private final String admissionDate;
    private final List<JsonAdaptedTask> tasks;

    /**
     * Constructs a {@code JsonAdaptedAnimal} with the given Animal details.
     * This constructor is used internally by Jackson to deserialize {@link Animal} JSON objects.
     */
    @JsonCreator
    public JsonAdaptedAnimal(@JsonProperty(NAME_KEY) String name, @JsonProperty(PET_ID_KEY) String petId,
                             @JsonProperty(SEX_KEY) String sex, @JsonProperty(SPECIES_KEY) String species,
                             @JsonProperty(BREED_KEY) String breed,
                             @JsonProperty(DATE_OF_BIRTH_KEY) String dateOfBirth,
                             @JsonProperty(ADMISSION_DATE_KEY) String admissionDate,
                             @JsonProperty(TASK_LIST_KEY) List<JsonAdaptedTask> tasks) {
        this.name = name;
        this.petId = petId;
        this.sex = sex;
        this.species = species;
        this.breed = breed;
        this.dateOfBirth = dateOfBirth;
        this.admissionDate = admissionDate;
        this.tasks = new ArrayList<>();
        if (tasks != null) {
            this.tasks.addAll(tasks);
        }
    }

    /**
     * Converts a given {@code Animal} into a {@code JsonAdaptedAnimal}, which can then be
     * serialized by Jackson into JSON objects.
     */
    public JsonAdaptedAnimal(Animal source) {
        this.name = source.getNameForSerialization();
        this.petId = source.getPetIdForSerialization();
        this.sex = source.getSexForSerialization();
        this.species = source.getSpeciesForSerialization();
        this.breed = source.getBreedForSerialization();
        this.dateOfBirth = source.getDateOfBirthForSerialization();
        this.admissionDate = source.getAdmissionDateForSerialization();
        this.tasks = source.getTaskListForSerialization();
    }

    /**
     * Converts this Jackson-friendly adapted animal object into the model's {@code Animal} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Animal toAnimalModel() throws IllegalValueException {
        checkForMissingFields();
        checkForInvalidFields();

        Name animalName = new Name(name);
        PetId animalPetId = new PetId(petId);
        Sex animalSex = new Sex(sex);
        Species animalSpecies = new Species(species);
        Breed animalBreed = new Breed(breed);
        DateOfBirth animalDateOfBirth = new DateOfBirth(dateOfBirth);
        AdmissionDate animalAdmissionDate = new AdmissionDate(admissionDate);
        TaskList animalTaskList = new TaskList();
        for (JsonAdaptedTask task : tasks) {
            animalTaskList.addTask(task.toTaskType());
        }
        return new Animal(animalName, animalPetId, animalSpecies, animalBreed, animalSex,
            animalAdmissionDate, animalDateOfBirth, animalTaskList);
    }

    /**
     * Checks the attributes for any null values.
     * Collates the list of null/missing attributes and throws a descriptive error message.
     *
     * @throws IllegalValueException if there were any missing fields.
     */
    private void checkForMissingFields() throws IllegalValueException {
        List<Class<?>> missingFields = new ArrayList<>();
        if (name == null) {
            missingFields.add(Name.class);
        }
        if (petId == null) {
            missingFields.add(PetId.class);
        }
        if (sex == null) {
            missingFields.add(Sex.class);
        }
        if (species == null) {
            missingFields.add(Species.class);
        }
        if (breed == null) {
            missingFields.add(Breed.class);
        }
        if (dateOfBirth == null) {
            missingFields.add(DateOfBirth.class);
        }
        if (admissionDate == null) {
            missingFields.add(AdmissionDate.class);
        }

        if (!missingFields.isEmpty()) {
            String missingFieldNames = missingFields.stream()
                .map(Class::getSimpleName)
                .collect(Collectors.joining(SEPARATOR));
            throw new IllegalValueException(String.format(MISSING_FIELDS_MESSAGE_FORMAT, missingFieldNames));
        }
    }

    /**
     * Validates the non-null fields against the model's constraints.
     * Accumulates all error messages for invalid fields and throws an exception if any are found.
     *
     * @throws IllegalValueException if there are invalid fields.
     */
    private void checkForInvalidFields() throws IllegalValueException {
        List<String> invalidFieldsMessages = new ArrayList<>();
        if (!Name.isValidName(name)) {
            invalidFieldsMessages.add(Name.MESSAGE_CONSTRAINTS);
        }
        if (!PetId.isValidPetId(petId)) {
            invalidFieldsMessages.add(PetId.MESSAGE_CONSTRAINTS);
        }
        if (!Sex.isValidSex(sex)) {
            invalidFieldsMessages.add(Sex.MESSAGE_CONSTRAINTS);
        }
        if (!Species.isValidSpecies(species)) {
            invalidFieldsMessages.add(Species.MESSAGE_CONSTRAINTS);
        }
        if (!Breed.isValidBreed(breed)) {
            invalidFieldsMessages.add(Breed.MESSAGE_CONSTRAINTS);
        }
        if (!DateOfBirth.isValidDate(dateOfBirth)) {
            invalidFieldsMessages.add(DateOfBirth.MESSAGE_CONSTRAINTS);
        }
        if (!AdmissionDate.isValidDate(admissionDate)) {
            invalidFieldsMessages.add(AdmissionDate.MESSAGE_CONSTRAINTS);
        }

        if (!invalidFieldsMessages.isEmpty()) {
            String invalidFields = String.join(SEPARATOR, invalidFieldsMessages);
            throw new IllegalValueException(String.format(INVALID_FIELDS_MESSAGE_FORMAT, invalidFields));
        }
    }
}
