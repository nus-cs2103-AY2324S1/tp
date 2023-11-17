package seedu.address.model.animal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAnimals.MUFFIN;
import static seedu.address.testutil.TypicalAnimals.TOFU;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.animal.exceptions.AnimalNotFoundException;
import seedu.address.model.animal.exceptions.DuplicateAnimalException;
import seedu.address.testutil.AnimalBuilder;

public class UniqueAnimalListTest {

    private final UniqueAnimalList uniqueAnimalList = new UniqueAnimalList();

    @Test
    public void contains_nullAnimal_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAnimalList.contains(null));
    }

    @Test
    public void contains_animalNotInList_returnsFalse() {
        assertFalse(uniqueAnimalList.contains(MUFFIN));
    }

    @Test
    public void contains_animalInList_returnsTrue() {
        try {
            uniqueAnimalList.add(MUFFIN);
            assertTrue(uniqueAnimalList.contains(MUFFIN));
        } catch (DuplicateAnimalException e) {
            fail("Exception not supposed to be thrown");
        }
    }

    @Test
    public void contains_animalWithSameIdentityFieldsInList_returnsTrue() {
        try {
            uniqueAnimalList.add(MUFFIN);
            Animal editedMuffin = new AnimalBuilder(MUFFIN).build();
            assertTrue(uniqueAnimalList.contains(editedMuffin));
        } catch (DuplicateAnimalException e) {
            fail("Exception not supposed to be thrown");
        }
    }

    @Test
    public void add_nullAnimal_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAnimalList.add(null));
    }

    @Test
    public void add_duplicateAnimal_throwsDuplicateAnimalException() {
        try {
            uniqueAnimalList.add(MUFFIN);
            assertThrows(DuplicateAnimalException.class, () -> uniqueAnimalList.add(MUFFIN));
        } catch (DuplicateAnimalException e) {
            fail("Exception not supposed to be thrown");
        }
    }

    @Test
    public void setAnimal_nullTargetAnimal_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAnimalList.setAnimal(null, MUFFIN));
    }

    @Test
    public void setAnimal_nullEditedAnimal_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAnimalList.setAnimal(MUFFIN, null));
    }

    @Test
    public void setAnimal_targetAnimalNotInList_throwsAnimalNotFoundException() {
        assertThrows(AnimalNotFoundException.class, () -> uniqueAnimalList.setAnimal(MUFFIN, MUFFIN));
    }

    @Test
    public void setAnimal_editedAnimalIsSameAnimal_success() {
        try {
            uniqueAnimalList.add(MUFFIN);
            uniqueAnimalList.setAnimal(MUFFIN, MUFFIN);
            UniqueAnimalList expectedUniqueAnimalList = new UniqueAnimalList();
            expectedUniqueAnimalList.add(MUFFIN);
            assertEquals(expectedUniqueAnimalList, uniqueAnimalList);
        } catch (DuplicateAnimalException | AnimalNotFoundException e) {
            fail("Exception not supposed to be thrown");
        }
    }

    @Test
    public void setAnimal_editedAnimalHasSameIdentity_success() {
        try {
            uniqueAnimalList.add(MUFFIN);
            Animal editedMuffin = new AnimalBuilder(MUFFIN).withAdmissionDate("2020-03-03").build();
            uniqueAnimalList.setAnimal(MUFFIN, editedMuffin);
            UniqueAnimalList expectedUniqueAnimalList = new UniqueAnimalList();
            expectedUniqueAnimalList.add(editedMuffin);
            assertEquals(expectedUniqueAnimalList, uniqueAnimalList);
        } catch (DuplicateAnimalException | AnimalNotFoundException e) {
            fail("Exception not supposed to be thrown");
        }
    }

    @Test
    public void setAnimal_editedAnimalHasDifferentIdentity_success() {
        try {
            uniqueAnimalList.add(MUFFIN);
            uniqueAnimalList.setAnimal(MUFFIN, TOFU);
            UniqueAnimalList expectedUniqueAnimalList = new UniqueAnimalList();
            expectedUniqueAnimalList.add(TOFU);
            assertEquals(expectedUniqueAnimalList, uniqueAnimalList);
        } catch (DuplicateAnimalException | AnimalNotFoundException e) {
            fail("Exception not supposed to be thrown");
        }
    }

    @Test
    public void setAnimal_editedAnimalHasNonUniqueIdentity_throwsDuplicateAnimalException() {
        try {
            uniqueAnimalList.add(MUFFIN);
            uniqueAnimalList.add(TOFU);
            assertThrows(DuplicateAnimalException.class, () -> uniqueAnimalList.setAnimal(MUFFIN, TOFU));
        } catch (DuplicateAnimalException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void remove_nullAnimal_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAnimalList.remove(null));
    }

    @Test
    public void remove_animalDoesNotExist_throwsAnimalNotFoundException() {
        assertThrows(AnimalNotFoundException.class, () -> uniqueAnimalList.remove(MUFFIN));
    }

    @Test
    public void remove_existingAnimal_removesAnimal() {
        try {
            uniqueAnimalList.add(MUFFIN);
            uniqueAnimalList.remove(MUFFIN);
            UniqueAnimalList expectedUniqueAnimalList = new UniqueAnimalList();
            assertEquals(expectedUniqueAnimalList, uniqueAnimalList);
        } catch (DuplicateAnimalException | AnimalNotFoundException e) {
            fail("Exception not supposed to be thrown");
        }
    }

    @Test
    public void setAnimals_nullUniqueAnimalList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAnimalList.setAnimals((UniqueAnimalList) null));
    }

    @Test
    public void setAnimals_uniqueAnimalList_replacesOwnListWithProvidedUniqueAnimalList() {
        try {
            uniqueAnimalList.add(MUFFIN);
            UniqueAnimalList expectedUniqueAnimalList = new UniqueAnimalList();
            expectedUniqueAnimalList.add(TOFU);
            uniqueAnimalList.setAnimals(expectedUniqueAnimalList);
            assertEquals(expectedUniqueAnimalList, uniqueAnimalList);
        } catch (DuplicateAnimalException e) {
            fail("Exception not supposed to be thrown");
        }
    }

    @Test
    public void setAnimals_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueAnimalList.setAnimals((List<Animal>) null));
    }

    @Test
    public void setAnimals_list_replacesOwnListWithProvidedList() {
        try {
            uniqueAnimalList.add(MUFFIN);
            List<Animal> animalList = Collections.singletonList(TOFU);
            uniqueAnimalList.setAnimals(animalList);
            UniqueAnimalList expectedUniqueAnimalList = new UniqueAnimalList();
            expectedUniqueAnimalList.add(TOFU);
            assertEquals(expectedUniqueAnimalList, uniqueAnimalList);
        } catch (DuplicateAnimalException e) {
            fail("Exception not supposed to be thrown");
        }
    }

    @Test
    public void setAnimals_listWithDuplicateAnimals_throwsDuplicateAnimalException() {
        List<Animal> listWithDuplicateAnimals = Arrays.asList(MUFFIN, MUFFIN);
        assertThrows(DuplicateAnimalException.class, () -> uniqueAnimalList.setAnimals(listWithDuplicateAnimals));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueAnimalList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueAnimalList.asUnmodifiableObservableList().toString(),
                uniqueAnimalList.toString());
    }
}
