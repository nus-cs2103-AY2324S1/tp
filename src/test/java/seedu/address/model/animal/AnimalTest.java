package seedu.address.model.animal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAnimals.MUFFIN;
import static seedu.address.testutil.TypicalAnimals.TOFU;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.AnimalBuilder;

public class AnimalTest {

    @Test
    public void isSameAnimal_sameAnimal_returnsTrue() {
        assertTrue(TOFU.isSameAnimal(TOFU));
    }

    @Test
    public void isSameAnimal_nullAnimal_returnsFalse() {
        assertThrows(NullPointerException.class, () -> ((Animal) null).isSameAnimal(TOFU));
    }

    @Test
    public void isSameAnimal_nullOtherAnimal_returnsFalse() {
        assertFalse(TOFU.isSameAnimal(null));
    }

    @Test
    public void isSameAnimal_samePetId_returnsTrue() {
        Animal editedTofu = new AnimalBuilder(MUFFIN).withPetId("1234").build();
        assertTrue(TOFU.isSameAnimal(editedTofu));
    }

    @Test
    public void isSameAnimal_sameNameDifferentPetId_returnsFalse() {
        Animal editedTofu = new AnimalBuilder(TOFU).withPetId("2345").build();
        assertFalse(TOFU.isSameAnimal(editedTofu));
    }

    @Test
    public void isSameAnimal_samePetIdDifferentName_returnsTrue() {
        Animal editedTofu = new AnimalBuilder(TOFU).withName("Muffin").build();
        assertTrue(TOFU.isSameAnimal(editedTofu));
    }

    @Test
    public void equals_sameAnimal_returnsTrue() {
        assertTrue(TOFU.equals(TOFU));
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        Animal tofuCopy = new AnimalBuilder(TOFU).build();
        assertTrue(TOFU.equals(tofuCopy));
    }

    @Test
    public void equals_nullOtherAnimal_returnsFalse() {
        assertFalse(TOFU.equals(null));
    }

    @Test
    public void equals_nullAnimal_returnsFalse() {
        assertThrows(NullPointerException.class, () ->((Animal) null).equals(TOFU));
    }

    @Test
    public void equals_differentType_returnsFalse() {
        assertFalse(TOFU.equals(5));
    }

    @Test
    public void equals_differentAnimal_returnsFalse() {
        assertFalse(TOFU.equals(MUFFIN));
    }

    @Test
    public void equals_differentName_returnsFalse() {
        Animal editedTofu = new AnimalBuilder(TOFU).withName("Muffin").build();
        assertFalse(TOFU.equals(editedTofu));
    }

    @Test
    public void equals_differentPetId_returnsFalse() {
        Animal editedTofu = new AnimalBuilder(TOFU).withPetId("2345").build();
        assertFalse(TOFU.equals(editedTofu));
    }

    @Test
    public void equals_differentSex_returnsFalse() {
        Animal editedTofu = new AnimalBuilder(TOFU).withSex("MALE").build();
        assertFalse(TOFU.equals(editedTofu));
    }

    @Test
    public void equals_differentSpecies_returnsFalse() {
        Animal editedTofu = new AnimalBuilder(TOFU).withSpecies("Dog").build();
        assertFalse(TOFU.equals(editedTofu));
    }

    @Test
    public void equals_differentBreed_returnsFalse() {
        Animal editedTofu = new AnimalBuilder(TOFU).withBreed("Jack Russell Terrier").build();
        assertFalse(TOFU.equals(editedTofu));
    }

    @Test
    public void equals_differentDateOfBirth_returnsFalse() {
        Animal editedTofu = new AnimalBuilder(TOFU).withDateOfBirth("2019-02-02").build();
        assertFalse(TOFU.equals(editedTofu));
    }

    @Test
    public void equals_differentAdmissionDate_returnsFalse() {
        Animal editedTofu = new AnimalBuilder(TOFU).withAdmissionDate("2020-02-02").build();
        assertFalse(TOFU.equals(editedTofu));
    }

}
