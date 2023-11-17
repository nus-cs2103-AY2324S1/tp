package seedu.address.testutil;

import static seedu.address.testutil.TypicalTasks.FEED;
import static seedu.address.testutil.TypicalTasks.WALK;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.model.AnimalCatalog;
import seedu.address.model.animal.Animal;
import seedu.address.model.animal.Task;

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
        .withTaskList(List.of(FEED, WALK))
        .build();

    public static final Animal MUFFIN = new AnimalBuilder()
        .withName("Muffin")
        .withPetId("3333")
        .withSex("MALE")
        .withSpecies("Dog")
        .withBreed("Jack Russell Terrier")
        .withDateOfBirth("2020-10-10")
        .withAdmissionDate("2020-12-12")
        .withTaskList(Stream.of(new Task("Feed Muffin"),
                new Task("Wash Muffin")).collect(Collectors.toList()))
        .build();

    public static final Animal JAEBEE = new AnimalBuilder()
        .withName("Jaebee")
        .withPetId("9999")
        .withSex("MALE")
        .withSpecies("Pookie")
        .withBreed("Bear")
        .withDateOfBirth("2023-01-01")
        .withAdmissionDate("2023-04-04")
        .withTaskList(Stream.of(new Task("Feed Jaebee"),
                new Task("Wash Jaebee")).collect(Collectors.toList()))
        .build();

    public static final Animal LOYBOY = new AnimalBuilder()
        .withName("Loyboy")
        .withPetId("2345")
        .withSex("MALE")
        .withSpecies("Human")
        .withBreed("Good Boy")
        .withDateOfBirth("2001-01-01")
        .withAdmissionDate("2023-02-02")
        .withTaskList(Stream.of(new Task("Feed Loyboy"),
            new Task("Wash Loyboy")).collect(Collectors.toList()))
        .build();

    public static final Animal BEAR = new AnimalBuilder()
            .withName("Bear")
            .withPetId("1111")
            .withSex("Female")
            .withSpecies("Dog")
            .withBreed("Poodle")
            .withDateOfBirth("2019-01-01")
            .withAdmissionDate("2019-01-02")
            .withTaskList(Stream.of(new Task("Feed Bear"),
                new Task("Wash Bear")).collect(Collectors.toList()))
            .build();

    public static AnimalCatalog getTypicalCatalog() {
        AnimalCatalog ac = new AnimalCatalog();
        for (Animal animal : getTypicalAnimals()) {
            ac.addAnimal(animal);
        }
        return ac;
    }

    public static List<Animal> getTypicalAnimals() {
        return new ArrayList<>(Arrays.asList(TOFU, MUFFIN, JAEBEE, LOYBOY, BEAR));
    }
}
