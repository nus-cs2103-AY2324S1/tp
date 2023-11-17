package seedu.address.model.animal;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.storage.JsonAdaptedTask;

/**
 * Represents an Animal in the catalog.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Animal {
    private final Name name;
    private final PetId petId;
    private final DateOfBirth dateOfBirth;
    private final Sex sex;
    private final Species species;
    private final Breed breed;
    private final AdmissionDate admissionDate;
    private final TaskList taskList;

    /**
     * Constructs an {@link Animal} object.
     * @param name the name of the Animal.
     * @param petId the petId assigned to the Animal.
     * @param species the species of the Animal.
     * @param breed the breed of the Animal.
     * @param sex the sex of the Animal.
     * @param admissionDate the date the Animal was admitted.
     * @param dateOfBirth the date of birth of the Animal.
     */
    public Animal(Name name, PetId petId, Species species, Breed breed, Sex sex,
                  AdmissionDate admissionDate, DateOfBirth dateOfBirth) {
        this.name = name;
        this.petId = petId;
        this.sex = sex;
        this.species = species;
        this.breed = breed;
        this.admissionDate = admissionDate;
        this.dateOfBirth = dateOfBirth;
        this.taskList = new TaskList();
    }

    /**
     * Constructs an {@link Animal} object.
     * @param name the name of the Animal.
     * @param petId the petId assigned to the Animal.
     * @param species the species of the Animal.
     * @param breed the breed of the Animal.
     * @param sex the sex of the Animal.
     * @param admissionDate the date the Animal was admitted.
     * @param dateOfBirth the date of birth of the Animal.
     */
    public Animal(Name name, PetId petId, Species species, Breed breed, Sex sex,
                  AdmissionDate admissionDate, DateOfBirth dateOfBirth, TaskList taskList) {
        this.name = name;
        this.petId = petId;
        this.sex = sex;
        this.species = species;
        this.breed = breed;
        this.admissionDate = admissionDate;
        this.dateOfBirth = dateOfBirth;
        this.taskList = taskList;
    }

    public Name getName() {
        return name;
    }

    public PetId getPetId() {
        return petId;
    }

    public DateOfBirth getDateOfBirth() {
        return dateOfBirth;
    }

    public AdmissionDate getAdmissionDate() {
        return admissionDate;
    }

    public Sex getSex() {
        return sex;
    }

    public Species getSpecies() {
        return species;
    }

    public Breed getBreed() {
        return breed;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public List<Task> getTasks() {
        return taskList.getTasks();
    }

    // ----------------- These getter methods are used for JSON serialization by JsonAdaptedAnimal--------------
    // NOTE: For Animal attributes that simply stores a string as its underlying implementation, with its toString()
    // method returning the string as-is, toString() is called to use it for deserialization.
    // This must be taken into account when modifying the existing toString() methods.
    public String getNameForSerialization() {
        return name.toString();
    }

    public String getPetIdForSerialization() {
        return petId.toString();
    }

    public String getDateOfBirthForSerialization() {
        return dateOfBirth.getDateOfBirth().toString();
    }

    public String getAdmissionDateForSerialization() {
        return admissionDate.getAdmissionDate().toString();
    }

    public String getSexForSerialization() {
        return sex.getSerializableForm();
    }

    public String getSpeciesForSerialization() {
        return species.toString();
    }

    public String getBreedForSerialization() {
        return breed.toString();
    }

    /**
     * Clones the current Animal object and adds the task to the clone
     *
     * @param task the task to be added.
     * @return a new Animal object with the added task.
     */
    public Animal addTask(Task task) {
        requireNonNull(task);

        Animal updatedAnimal = new Animal(name, petId, species, breed, sex, admissionDate, dateOfBirth);
        copyTasksFromAnimalToAnimal(this, updatedAnimal);
        updatedAnimal.getTaskList().addTask(task);

        return updatedAnimal;
    }

    /**
     * Clones the current Animal object and deletes the task of the specified task index of the clone
     *
     * @param targetTaskIndex the index of the task to be deleted in the Animal taskList
     * @return a new Animal object with a taskList less the specified task
     */
    public Animal deleteTaskByIndex(Index targetTaskIndex) {
        requireNonNull(targetTaskIndex);

        Animal updatedAnimal = new Animal(name, petId, species, breed, sex, admissionDate, dateOfBirth);
        copyTasksFromAnimalToAnimal(this, updatedAnimal);
        updatedAnimal.getTaskList().deleteTaskByIndex(targetTaskIndex);

        return updatedAnimal;
    }

    private static void copyTasksFromAnimalToAnimal(Animal source, Animal target) {
        TaskList targetTaskList = target.getTaskList();
        targetTaskList.addAllTasks(source.getTasks());
    }

    public List<JsonAdaptedTask> getTaskListForSerialization() {
        return taskList.getSerializedTaskList();
    }

    /**
     * Returns a new Animal object with the updated task list.
     *
     * @param taskIndex the indexes of the task to be updated.
     * @param isDone the status of the task to be updated.
     * @return a new Animal object with the updated task list.
     * @throws IndexOutOfBoundsException if the taskIndex is invalid.
     */
    public Animal updateTaskList(int[] taskIndex, boolean isDone) throws IndexOutOfBoundsException {
        TaskList updatedList = taskList.updateTaskList(taskIndex, isDone);

        return new Animal(name, petId, species, breed, sex, admissionDate, dateOfBirth, updatedList);
    }

    public void resetTasks() {
        taskList.resetTasks();
    }

    public int getNumberOfTasks() {
        return taskList.getNumberOfTasks();
    }

    /**
     * Returns the string representation of this Animal.
     *
     * @return the string representation of this Animal.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("id", petId)
                .add("sex", sex)
                .add("species", species)
                .add("breed", breed)
                .add("admissionDate", admissionDate)
                .add("dateOfBirth", dateOfBirth)
                .toString();
    }

    /**
     * Returns a list of the attributes of this Animal.
     *
     * @return a list of the attributes of this Animal.
     */
    public List<String> attributes() {
        return List.of(
                name.toString(),
                petId.toString(),
                dateOfBirth.toString(),
                admissionDate.toString(),
                species.toString(),
                sex.toString(),
                breed.toString()
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, petId, sex, species, breed, admissionDate, dateOfBirth);
    }

    /**
     * Returns true if the {@link PetId} of the {@code otherAnimal} is equal to this Animal's petId.
     * Does not use {@link Animal#equals(Object)} in the event of an Edit.
     *
     * @param otherAnimal the other Animal to be compared.
     * @return true if the PetIds being compared are equal, false otherwise.
     */
    public boolean isSameAnimal(Animal otherAnimal) {
        if (otherAnimal == this) {
            return true;
        }

        return otherAnimal != null
                && otherAnimal.getPetId().equals(getPetId());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Animal)) {
            return false;
        }

        Animal otherAnimal = (Animal) other;
        return name.equals(otherAnimal.name)
                && petId.equals(otherAnimal.petId)
                && dateOfBirth.equals(otherAnimal.dateOfBirth)
                && sex.equals(otherAnimal.sex)
                && species.equals(otherAnimal.species)
                && breed.equals(otherAnimal.breed)
                && admissionDate.equals(otherAnimal.admissionDate)
                && taskList.equals(otherAnimal.taskList);
    }
}
