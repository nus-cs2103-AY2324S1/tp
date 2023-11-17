package seedu.address.testutil;

import java.util.List;

import seedu.address.model.animal.AdmissionDate;
import seedu.address.model.animal.Animal;
import seedu.address.model.animal.Breed;
import seedu.address.model.animal.DateOfBirth;
import seedu.address.model.animal.Name;
import seedu.address.model.animal.PetId;
import seedu.address.model.animal.Sex;
import seedu.address.model.animal.Species;
import seedu.address.model.animal.Task;
import seedu.address.model.animal.TaskList;



/**
 * A utility class to help with building Animal objects.
 */
public class AnimalBuilder {

    public static final String DEFAULT_NAME = "Pookie";
    public static final String DEFAULT_PETID = "1111";
    public static final String DEFAULT_DATE_OF_BIRTH = "2020-12-12";
    public static final String DEFAULT_SEX = "MALE";
    public static final String DEFAULT_SPECIES = "Dog";
    public static final String DEFAULT_BREED = "Poodle";
    public static final String DEFAULT_ADMISSION_DATE = "2023-05-05";


    private Name name;
    private PetId petId;
    private DateOfBirth dateOfBirth;
    private Sex sex;
    private Species species;
    private Breed breed;
    private AdmissionDate admissionDate;
    private TaskList taskList;

    /**
     * Creates a {@code AnimalBuilder} with the default details.
     */
    public AnimalBuilder() {
        name = new Name(DEFAULT_NAME);
        petId = new PetId(DEFAULT_PETID);
        dateOfBirth = new DateOfBirth(DEFAULT_DATE_OF_BIRTH);
        sex = new Sex(DEFAULT_SEX);
        species = new Species(DEFAULT_SPECIES);
        breed = new Breed(DEFAULT_BREED);
        admissionDate = new AdmissionDate(DEFAULT_ADMISSION_DATE);
        taskList = new TaskList();
    }

    /**
     * Initializes the AnimalBuilder with the data of {@code animalToCopy}.
     */
    public AnimalBuilder(Animal animalToCopy) {
        name = animalToCopy.getName();
        petId = animalToCopy.getPetId();
        dateOfBirth = animalToCopy.getDateOfBirth();
        sex = animalToCopy.getSex();
        species = animalToCopy.getSpecies();
        breed = animalToCopy.getBreed();
        admissionDate = animalToCopy.getAdmissionDate();
        taskList = animalToCopy.getTaskList();
    }

    /**
     * Sets the {@code Name} of the {@code Animal} that we are building.
     */
    public AnimalBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code PetId} of the {@code Animal} that we are building.
     */
    public AnimalBuilder withPetId(String petId) {
        this.petId = new PetId(petId);
        return this;
    }

    /**
     * Sets the {@code DateOfBirth} of the {@code Animal} that we are building.
     */
    public AnimalBuilder withDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = new DateOfBirth(dateOfBirth);
        return this;
    }

    /**
     * Sets the {@code Sex} of the {@code Animal} that we are building.
     */
    public AnimalBuilder withSex(String sex) {
        this.sex = new Sex(sex);
        return this;
    }

    /**
     * Sets the {@code Species} of the {@code Animal} that we are building.
     */
    public AnimalBuilder withSpecies(String species) {
        this.species = new Species(species);
        return this;
    }

    /**
     * Sets the {@code Breed} of the {@code Animal} that we are building.
     */
    public AnimalBuilder withBreed(String breed) {
        this.breed = new Breed(breed);
        return this;
    }

    /**
     * Sets the {@code AdmissionDate} of the {@code Animal} that we are building.
     */
    public AnimalBuilder withAdmissionDate(String admissionDate) {
        this.admissionDate = new AdmissionDate(admissionDate);
        return this;
    }

    /**
     * Sets the {@code taskList} of the {@code Animal} that we are building.
     *
     */
    public AnimalBuilder withTaskList(List<Task> taskList) {
        this.taskList = new TaskList();
        this.taskList.addAllTasks(taskList);
        return this;
    }

    public Animal build() {
        return new Animal(name, petId, species, breed, sex, admissionDate, dateOfBirth, taskList);
    }
}
