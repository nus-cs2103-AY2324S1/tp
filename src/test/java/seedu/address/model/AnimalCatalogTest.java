package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAnimals.TOFU;
import static seedu.address.testutil.TypicalAnimals.getTypicalCatalog;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.animal.Animal;
import seedu.address.testutil.AnimalBuilder;

public class AnimalCatalogTest {

    private final AnimalCatalog animalCatalog = new AnimalCatalog();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), animalCatalog.getAnimalList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> animalCatalog.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAnimalCatalog_replacesData() {
        AnimalCatalog newData = getTypicalCatalog();
        animalCatalog.resetData(newData);
        assertEquals(newData, animalCatalog);
    }

    //TODO: Delete this test if exception is dealt with in this method
    //TODO: Uncomment if exception is dealt with elsewhere
    //@Test
    //public void resetData_withDuplicateAnimals_throwsDuplicateAnimalException() {
    //Two animals with the same identity fields
    //Animal editedTofu = new AnimalBuilder(TOFU).withAdmissionDate("2023-01-01").build();
    //List<Animal> newAnimals = Arrays.asList(TOFU, editedTofu);
    //AnimalCatalogStub newData = new AnimalCatalogStub(newAnimals);
    //assertThrows(DuplicateAnimalException.class, () -> animalCatalog.resetData(newData));
    //}

    @Test
    public void hasAnimal_nullAnimal_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> animalCatalog.hasAnimal(null));
    }

    @Test
    public void hasAnimal_animalNotInAnimalCatalog_returnsFalse() {
        assertFalse(animalCatalog.hasAnimal(TOFU));
    }

    @Test
    public void hasAnimal_animalInAnimalCatalog_returnsTrue() {
        animalCatalog.addAnimal(TOFU);
        assertTrue(animalCatalog.hasAnimal(TOFU));
    }

    @Test
    public void hasAnimal_animalWithSameIdentityFieldsInAnimalCatalog_returnsTrue() {
        animalCatalog.addAnimal(TOFU);
        Animal editedTofu = new AnimalBuilder(TOFU).withAdmissionDate("2023-01-01").build();
        assertTrue(animalCatalog.hasAnimal(editedTofu));
    }

    @Test
    public void getAnimalList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> animalCatalog.getAnimalList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = AnimalCatalog.class.getCanonicalName() + "{animals=" + animalCatalog.getAnimalList() + "}";
        assertEquals(expected, animalCatalog.toString());
    }

    @Test
    public void equals_sameAnimalCatalog_returnsTrue() {
        assertTrue(animalCatalog.equals(animalCatalog));
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        AnimalCatalog animalCatalogCopy = new AnimalCatalog();
        assertTrue(animalCatalog.equals(animalCatalogCopy));
    }

    @Test
    public void equals_nullOtherAnimalCatalog_returnsFalse() {
        assertFalse(animalCatalog.equals(null));
    }

    @Test
    public void equals_nullAnimalCatalog_returnsFalse() {
        assertThrows(NullPointerException.class, () -> ((AnimalCatalog) null).equals(animalCatalog));
    }

    @Test
    public void equals_differentType_returnsFalse() {
        assertFalse(animalCatalog.equals(5));
    }

    @Test
    public void equals_differentAnimalCatalog_returnsFalse() {
        AnimalCatalog animalCatalogCopy = new AnimalCatalog();
        animalCatalogCopy.addAnimal(TOFU);
        assertFalse(animalCatalog.equals(animalCatalogCopy));
    }

    //TODO: Uncomment class if exception in resetData is dealt with elsewhere.
    ///**
    // * A stub ReadOnlyAnimalCatalog whose animals list can violate interface constraints.
    // */
    //private static class AnimalCatalogStub implements ReadOnlyAnimalCatalog {
    //private final ObservableList<Animal> animals = FXCollections.observableArrayList();

    //AnimalCatalogStub(Collection<Animal> animals) {
    //this.animals.setAll(animals);
    //}

    //@Override
    //public ObservableList<Animal> getAnimalList() {
    //return animals;
    //}
    //}
}
