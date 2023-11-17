package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AnimalCatalog;
import seedu.address.model.ReadOnlyAnimalCatalog;
import seedu.address.model.animal.Animal;

/**
 * Represents an immutable AnimalCatalog that is serializable to JSON format, which can
 * then be written to/read from local storage. This class handles the list of {@link JsonAdaptedAnimal}.
 */
@JsonRootName(value = "animalcatalog")
public class JsonSerializableAnimalCatalog {
    public static final String MESSAGE_DUPLICATE_ANIMAL = "Animals list contain duplicate animal(s)!";
    public static final String ANIMALS_KEY = "animals";

    private final List<JsonAdaptedAnimal> animals = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAnimalCatalog} with the given {@code JsonAdaptedAnimals}.
     * This constructor is used internally by Jackson for serialization.
     *
     * @param animals the list of animals to be serialized.
     */
    @JsonCreator
    public JsonSerializableAnimalCatalog(@JsonProperty(ANIMALS_KEY) List<JsonAdaptedAnimal> animals) {
        this.animals.addAll(animals);
    }

    /**
     * Creates a {@code JsonSerializableAnimalCatalog} from the provided {@code ReadOnlyAnimalCatalog}.
     *
     * The created {@code JsonSerializableAnimalCatalog} is a separate instance with its own data,
     * ensuring that any future modifications to the {@code ReadOnlyAnimalCatalog} do not impact
     * the integrity or data of the {@code JsonSerializableAnimalCatalog}.
     *
     * This constructor is used internally by Jackson for serialization.
     *
     * @param source the original address book data to be converted into a {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAnimalCatalog(ReadOnlyAnimalCatalog source) {
        // Converts the Animal Models into a format suitable for JSON serialization.
        List<JsonAdaptedAnimal> jsonAdaptedAnimals = source.getAnimalList()
            .stream()
            .map(JsonAdaptedAnimal::new)
            .collect(Collectors.toList());

        animals.addAll(jsonAdaptedAnimals);
    }

    /**
     * Converts this JsonSerializableAnimalCatalog instance into the model's {@code AnimalCatalog} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AnimalCatalog toAnimalCatalogModel() throws IllegalValueException {
        AnimalCatalog animalCatalog = new AnimalCatalog();
        for (JsonAdaptedAnimal jsonAdaptedAnimal: animals) {
            Animal animalModel = jsonAdaptedAnimal.toAnimalModel();
            if (animalCatalog.hasAnimal(animalModel)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ANIMAL);
            }
            animalCatalog.addAnimal(animalModel);
        }
        return animalCatalog;
    }
}
