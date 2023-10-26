package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AnimalCatalog;
import seedu.address.model.animal.Animal;

/**
 * A utility class containing a list of {@code Animal} objects to be used in tests.
 */
public class TypicalAnimals {

    public static final Animal TOFU = new AnimalBuilder()
        .withName("Tofu")
        .withPetId("1234")
        .withDateOfBirth("2019-10-10")
        .withSex("FEMALE")
        .withSpecies("Cat")
        .withBreed("British Shorthair")
        .withAdmissionDate("2019-11-11")
        .build();

    public static final Animal MUFFIN = new AnimalBuilder()
        .withName("Muffin")
        .withPetId("3333")
        .withSex("MALE")
        .withSpecies("Dog")
        .withBreed("Jack Russell Terrier")
        .withDateOfBirth("2020-10-10")
        .withAdmissionDate("2020-12-12")
        .build();

    public static final Animal POOKIE = new AnimalBuilder()
        .withName("Pookie")
        .withPetId("9999")
        .withSex("MALE")
        .withSpecies("Wookie")
        .withBreed("Bear")
        .withDateOfBirth("2023-01-01")
        .withAdmissionDate("2023-04-04")
        .build();

    public static final Animal LOYBOY = new AnimalBuilder()
        .withName("Loyboy")
        .withPetId("2345")
        .withSex("MALE")
        .withSpecies("Human")
        .withBreed("Good Boy")
        .withDateOfBirth("2001-01-01")
        .withAdmissionDate("2023-02-02")
        .build();


    //TODO: Add more animals if needed

    public static AnimalCatalog getTypicalCatalog() {
        AnimalCatalog ac = new AnimalCatalog();
        for (Animal animal : getTypicalAnimals()) {
            ac.addAnimal(animal);
        }
        return ac;
    }

    public static List<Animal> getTypicalAnimals() {
        return new ArrayList<>(Arrays.asList(TOFU, MUFFIN, POOKIE, LOYBOY));
    }
}
