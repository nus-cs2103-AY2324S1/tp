package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.animal.Animal;
import seedu.address.model.animal.Name;
import seedu.address.model.animal.Task;

/**
 * Represents the in-memory model of the AnimalCatalog Data
 */
public class AnimalModelManager implements AnimalModel {
    private static final Logger logger = LogsCenter.getLogger(AnimalModelManager.class);

    private final AnimalCatalog animalCatalog;

    private final AnimalUserPrefs userPrefs;

    private final FilteredList<Animal> filteredAnimals;

    /**
     * Initializes an animalCatalog with the given animalCatalog and userPrefs
     *
     * @param animalCatalog Generated animalCatalog
     * @param userPrefs AnimalUserPreferences
     */
    public AnimalModelManager(ReadOnlyAnimalCatalog animalCatalog, AnimalReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(animalCatalog, userPrefs);

        logger.fine("Initializing with animal catalog: " + animalCatalog + " and user prefs " + userPrefs);

        this.animalCatalog = new AnimalCatalog(animalCatalog);
        this.userPrefs = new AnimalUserPrefs(userPrefs);
        filteredAnimals = new FilteredList<>(this.animalCatalog.getAnimalList());
    }

    public AnimalModelManager() {
        this(new AnimalCatalog(), new AnimalUserPrefs());
    }

    @Override
    public void setUserPrefs(AnimalReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public AnimalReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAnimalCatalogFilePath() {
        return userPrefs.getAnimalCatalogFilePath();
    }

    @Override
    public void setAnimalCatalogFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAnimalCatalogFilePath(addressBookFilePath);
    }

    //=========== Animal Catalog ================================================================================

    @Override
    public void setAnimalCatalog(ReadOnlyAnimalCatalog animalCatalog) {
        this.animalCatalog.resetData(animalCatalog);
    }

    @Override
    public ReadOnlyAnimalCatalog getAnimalCatalog() {
        return animalCatalog;
    }

    @Override
    public boolean hasAnimal(Animal animal) {
        requireNonNull(animal);
        return animalCatalog.hasAnimal(animal);
    }

    @Override
    public void deleteAnimal(Animal target) {
        animalCatalog.removeAnimal(target);
    }

    @Override
    public void addAnimal(Animal animal) {
        animalCatalog.addAnimal(animal);
        updateFilteredAnimalList(PREDICATE_SHOW_ALL_ANIMALS);
    }

    @Override
    public void setAnimal(Animal target, Animal editedAnimal) {
        requireAllNonNull(target, editedAnimal);

        animalCatalog.setAnimal(target, editedAnimal);
    }

    @Override
    public Animal addTask(Task newTask, Animal animal) {
        requireAllNonNull(newTask, animal);

        Animal updatedAnimal = animal.addTask(newTask);
        setAnimal(animal, updatedAnimal);
        updateFilteredAnimalList(PREDICATE_SHOW_ALL_ANIMALS);

        return updatedAnimal;
    }

    @Override
    public Animal deleteTask(Animal animal, Index taskIndex) {
        requireAllNonNull(taskIndex, animal);

        Animal updatedAnimal = animal.deleteTaskByIndex(taskIndex);
        setAnimal(animal, updatedAnimal);
        updateFilteredAnimalList(PREDICATE_SHOW_ALL_ANIMALS);

        return updatedAnimal;
    }

    @Override
    public int getSizeOfTaskList(Animal animal) {
        requireNonNull(animal);

        return animal.getNumberOfTasks();
    }

    @Override
    public Name getName(Animal animal) {
        requireNonNull(animal);

        return animal.getName();
    }

    @Override
    public Task getTaskByIndex(Animal animal, Index index) {
        requireAllNonNull(animal, index);

        return animal.getTaskList().getTaskByIndex(index);
    }

    @Override
    public Animal updateTask(Animal animal, int[] taskIndexes, boolean isCompleted) {
        requireAllNonNull(animal, taskIndexes, isCompleted);

        Animal updatedAnimal = animal.updateTaskList(taskIndexes, isCompleted);
        animalCatalog.setAnimal(animal, updatedAnimal);

        updateFilteredAnimalList(PREDICATE_SHOW_ALL_ANIMALS);
        return updatedAnimal;
    }

    @Override
    public void resetTasks() {
        animalCatalog.resetTasks();
        updateFilteredAnimalList(PREDICATE_SHOW_ALL_ANIMALS);
    }

    //=========== Filtered Animal List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Animal} backed by the internal list of
     * {@code animalCatalog}
     */
    @Override
    public ObservableList<Animal> getFilteredAnimalList() {
        return filteredAnimals;
    }

    @Override
    public void updateFilteredAnimalList(Predicate<Animal> predicate) {
        requireNonNull(predicate);
        filteredAnimals.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AnimalModelManager)) {
            return false;
        }

        AnimalModelManager otherModelManager = (AnimalModelManager) other;
        return animalCatalog.equals(otherModelManager.animalCatalog)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredAnimals.equals(otherModelManager.filteredAnimals);
    }
}
