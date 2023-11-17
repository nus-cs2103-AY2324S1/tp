package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.AnimalCommandTestUtil.DESC_POOKIE;
import static seedu.address.logic.commands.AnimalCommandTestUtil.DESC_TOFU;
import static seedu.address.logic.commands.AnimalCommandTestUtil.VALID_ADMISSION_DATE_TOFU;
import static seedu.address.logic.commands.AnimalCommandTestUtil.VALID_BREED_TOFU;
import static seedu.address.logic.commands.AnimalCommandTestUtil.VALID_DATE_OF_BIRTH_TOFU;
import static seedu.address.logic.commands.AnimalCommandTestUtil.VALID_NAME_TOFU;
import static seedu.address.logic.commands.AnimalCommandTestUtil.VALID_SEX_TOFU;
import static seedu.address.logic.commands.AnimalCommandTestUtil.VALID_SPECIES_TOFU;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditAnimalCommand.EditAnimalDescriptor;
import seedu.address.testutil.EditAnimalDescriptorBuilder;

public class EditAnimalDescriptorTest {

    @Test
    public void testEquals_sameValues_returnsTrue() {
        EditAnimalDescriptor descriptorWithSameValues = new EditAnimalDescriptor(DESC_POOKIE);
        assertEquals(DESC_POOKIE, descriptorWithSameValues);
    }

    @Test
    public void testEquals_sameObject_returnsTrue() {
        assertEquals(DESC_POOKIE, DESC_POOKIE);
    }

    @Test
    public void testEquals_null_returnsFalse() {
        assertNotEquals(null, DESC_POOKIE);
    }

    @Test
    public void testEquals_differentTypes_returnsFalse() {
        assertNotEquals(5, DESC_POOKIE);
    }

    @Test
    public void testEquals_differentValues_returnsFalse() {
        assertNotEquals(DESC_POOKIE, DESC_TOFU);
    }

    @Test
    public void testEquals_differentName_returnsFalse() {
        EditAnimalDescriptor editedPookie = new EditAnimalDescriptorBuilder(DESC_POOKIE)
                .withName(VALID_NAME_TOFU)
                .build();
        assertNotEquals(DESC_POOKIE, editedPookie);
    }

    @Test
    public void testEquals_differentSex_returnsFalse() {
        EditAnimalDescriptor editedPookie = new EditAnimalDescriptorBuilder(DESC_POOKIE)
                .withSex(VALID_SEX_TOFU)
                .build();
        assertNotEquals(DESC_POOKIE, editedPookie);
    }

    @Test
    public void testEquals_differentSpecies_returnsFalse() {
        EditAnimalDescriptor editedPookie = new EditAnimalDescriptorBuilder(DESC_POOKIE)
                .withSpecies(VALID_SPECIES_TOFU)
                .build();
        assertNotEquals(DESC_POOKIE, editedPookie);
    }

    @Test
    public void testEquals_differentBreed_returnsFalse() {
        EditAnimalDescriptor editedPookie = new EditAnimalDescriptorBuilder(DESC_POOKIE)
                .withBreed(VALID_BREED_TOFU)
                .build();
        assertNotEquals(DESC_POOKIE, editedPookie);
    }

    @Test
    public void testEquals_differentDateOfBirth_returnsFalse() {
        EditAnimalDescriptor editedPookie = new EditAnimalDescriptorBuilder(DESC_POOKIE)
                .withDateOfBirth(VALID_DATE_OF_BIRTH_TOFU)
                .build();
        assertNotEquals(DESC_POOKIE, editedPookie);
    }

    @Test
    public void testEquals_differentAdmissionDate_returnsFalse() {
        EditAnimalDescriptor editedPookie = new EditAnimalDescriptorBuilder(DESC_POOKIE)
                .withAdmissionDate(VALID_ADMISSION_DATE_TOFU)
                .build();
        assertNotEquals(DESC_POOKIE, editedPookie);
    }

    @Test
    public void toStringMethod() {
        EditAnimalDescriptor animalDescriptor = new EditAnimalDescriptor();
        String expected = EditAnimalDescriptor.class.getCanonicalName() + "{name="
                + animalDescriptor.getName().orElse(null) + ", dateOfBirth="
                + animalDescriptor.getDateOfBirth().orElse(null) + ", admissionDate="
                + animalDescriptor.getAdmissionDate().orElse(null) + ", species="
                + animalDescriptor.getSpecies().orElse(null) + ", breed="
                + animalDescriptor.getBreed().orElse(null) + ", sex="
                + animalDescriptor.getSex().orElse(null) + "}";
        assertEquals(expected, animalDescriptor.toString());
    }
}
