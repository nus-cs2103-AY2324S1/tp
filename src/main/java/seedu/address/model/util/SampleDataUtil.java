package seedu.address.model.util;

import seedu.address.model.AnimalCatalog;
import seedu.address.model.ReadOnlyAnimalCatalog;
import seedu.address.model.animal.AdmissionDate;
import seedu.address.model.animal.Animal;
import seedu.address.model.animal.Breed;
import seedu.address.model.animal.DateOfBirth;
import seedu.address.model.animal.Name;
import seedu.address.model.animal.PetId;
import seedu.address.model.animal.Sex;
import seedu.address.model.animal.Species;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    /**
     * A list of sample animals to show in the default app.
     * @return A list of Animals
     */

    public static Animal[] getSampleAnimals() {
        return new Animal[] {
            new Animal(new Name("Pookie"), new PetId("1234"), new Species("Dog"),
                new Breed("Golden retriever"), new Sex("Male"),
                new AdmissionDate("2022-09-09"), new DateOfBirth("2022-09-09")),
            new Animal(new Name("Bear"), new PetId("4321"), new Species("Bear"),
                new Breed("Brown Bear"), new Sex("Female"),
                new AdmissionDate("2022-09-09"), new DateOfBirth("2015-09-09"))
        };
    }


    /**
     * Generates a series of sample animals and puts it inside the animal catalog
     * @return AnimalCatalog
     */
    public static ReadOnlyAnimalCatalog getSampleAnimalCatalog() {
        AnimalCatalog sampleAc = new AnimalCatalog();
        for (Animal sampleAnimal : getSampleAnimals()) {
            sampleAc.addAnimal(sampleAnimal);
        }
        return sampleAc;
    }

}
