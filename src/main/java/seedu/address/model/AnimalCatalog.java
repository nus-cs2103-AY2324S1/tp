package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.animal.Animal;
import seedu.address.model.animal.UniqueAnimalList;
import seedu.address.model.animal.exceptions.AnimalNotFoundException;
import seedu.address.model.animal.exceptions.DuplicateAnimalException;

/**
 * Wraps all data at the AnimalCatalog Level.
 */
public class AnimalCatalog implements ReadOnlyAnimalCatalog {

    private final UniqueAnimalList animals;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        animals = new UniqueAnimalList();
    }

    public AnimalCatalog() {}

    /**
     * Creates an AnimalCatalog using the Animals in toBeCopied
     *
     * @param toBeCopied Animals to be copied into the AnimalCatalog
     */
    public AnimalCatalog(ReadOnlyAnimalCatalog toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the Animal List animals. Must not contain duplicate animals.
     *
     * @param animals
     */
    public void setAnimals(List<Animal> animals) {
        try {
            this.animals.setAnimals(animals);
        } catch (DuplicateAnimalException e) {
            System.out.println("Duplicate Animal!"); //Replace this with Command Output
        }
    }

    /**
     * Resets the existing data of the AnimalCatalog with the newData
     *
     * @param newData Data to be written into AnimalCatalog
     */
    public void resetData(ReadOnlyAnimalCatalog newData) {
        requireNonNull(newData);

        setAnimals(newData.getAnimalList());
    }

    /**
     * Returns true if an animal in the AnimalCatalog has same identity as animal
     *
     * @param animal Animal to be checked in the AnimalCatalog
     * @return A boolean value
     */
    public boolean hasAnimal(Animal animal) {
        requireNonNull(animal);
        return animals.contains(animal);
    }

    /**
     * Adds an animal into the AnimalCatalog. The animal must not already exist in the catalog.
     *
     * @param a Animal to be added in.
     */
    public void addAnimal(Animal a) {
        try {
            animals.add(a);
        } catch (DuplicateAnimalException e) {
            System.out.println("Duplicate Animal!"); //Replace this with Command Output
        }
    }

    /**
     * Replaces the given animal in the AnimalCatalog with the editedAnimal
     *
     * @param target Target animal to be replaced
     * @param editedAnimal editedAnimal to replace target
     */
    public void setAnimal(Animal target, Animal editedAnimal) {
        try {
            requireNonNull(editedAnimal);
            animals.setAnimal(target, editedAnimal);
        } catch (DuplicateAnimalException e) {
            System.out.println("Duplicate Animal!"); //Replace this with Command Output
        } catch (AnimalNotFoundException e) {
            System.out.println("Animal not found!");
        }
    }

    /**
     * Removes Animal from the AnimalCatalog. Animal must exists in the AnimalCatalog.
     *
     * @param key Animal to be removed
     */
    public void removeAnimal(Animal key) {
        try {
            animals.remove(key);
        } catch (AnimalNotFoundException e) {
            System.out.println("Animal Not Found!"); //Replace this with Command Output
        }
    }

    public void resetTasks() {
        animals.resetTasks();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("animals", animals)
                .toString();
    }

    @Override
    public ObservableList<Animal> getAnimalList() {
        return animals.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AnimalCatalog)) {
            return false;
        }

        AnimalCatalog otherAnimalCatalog = (AnimalCatalog) other;
        return animals.equals(otherAnimalCatalog.animals);
    }

    @Override
    public int hashCode() {
        return animals.hashCode();
    }

}
