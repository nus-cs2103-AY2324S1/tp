package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

public class CliAnimalSyntaxTest {
    private static final Prefix NAME_PREFIX = new Prefix("n/");
    private static final Prefix PET_ID_PREFIX = new Prefix("i/");
    private static final Prefix SEX_PREFIX = new Prefix("g/");
    private static final Prefix SPECIES_PREFIX = new Prefix("s/");
    private static final Prefix BREED_PREFIX = new Prefix("b/");
    private static final Prefix DATE_OF_BIRTH_PREFIX = new Prefix("db/");
    private static final Prefix DATE_OF_ADMISSION_PREFIX = new Prefix("da/");

    @Test
    public void getPrefix_returnsCorrectPrefix() {
        assertEquals(NAME_PREFIX, CliAnimalSyntax.NAME.getPrefix());
        assertEquals(PET_ID_PREFIX, CliAnimalSyntax.PET_ID.getPrefix());
        assertEquals(SEX_PREFIX, CliAnimalSyntax.SEX.getPrefix());
        assertEquals(SPECIES_PREFIX, CliAnimalSyntax.SPECIES.getPrefix());
        assertEquals(BREED_PREFIX, CliAnimalSyntax.BREED.getPrefix());
        assertEquals(DATE_OF_BIRTH_PREFIX, CliAnimalSyntax.DATE_OF_BIRTH.getPrefix());
        assertEquals(DATE_OF_ADMISSION_PREFIX, CliAnimalSyntax.DATE_OF_ADMISSION.getPrefix());
    }

    @Test
    public void getCliSyntaxFromPrefix_invalidPrefix_throwsIllegalArgumentException() {
        Prefix invalidPrefix = new Prefix("//");
        assertThrows(IllegalArgumentException.class, () -> CliAnimalSyntax.getCliSyntaxFromPrefix(invalidPrefix));
    }

    @Test
    public void getCliSyntaxFromPrefix_validPrefix_returnsCorrectEnum() {
        assertEquals(CliAnimalSyntax.NAME, CliAnimalSyntax.getCliSyntaxFromPrefix(NAME_PREFIX));
        assertEquals(CliAnimalSyntax.PET_ID, CliAnimalSyntax.getCliSyntaxFromPrefix(PET_ID_PREFIX));
        assertEquals(CliAnimalSyntax.SEX, CliAnimalSyntax.getCliSyntaxFromPrefix(SEX_PREFIX));
        assertEquals(CliAnimalSyntax.SPECIES, CliAnimalSyntax.getCliSyntaxFromPrefix(SPECIES_PREFIX));
        assertEquals(CliAnimalSyntax.BREED, CliAnimalSyntax.getCliSyntaxFromPrefix(BREED_PREFIX));
        assertEquals(CliAnimalSyntax.DATE_OF_BIRTH, CliAnimalSyntax.getCliSyntaxFromPrefix(DATE_OF_BIRTH_PREFIX));
        assertEquals(CliAnimalSyntax.DATE_OF_ADMISSION,
            CliAnimalSyntax.getCliSyntaxFromPrefix(DATE_OF_ADMISSION_PREFIX));
    }

    @Test
    public void getMandatoryPrefixes_returnsCorrectList() {
        List<Prefix> expectedList = List.of(NAME_PREFIX, PET_ID_PREFIX, SEX_PREFIX, SPECIES_PREFIX, BREED_PREFIX,
            DATE_OF_BIRTH_PREFIX, DATE_OF_ADMISSION_PREFIX);

        List<Prefix> actualList = CliAnimalSyntax.getAnimalAttributePrefixes();

        boolean result = actualList.containsAll(expectedList);

        assertTrue(result);
        assertEquals(expectedList.size(), actualList.size());
    }


    @Test
    public void getUsage_returnsCorrectUsageString() {
        assertEquals("n/NAME", CliAnimalSyntax.NAME.getUsage());
        assertEquals("i/PET_ID", CliAnimalSyntax.PET_ID.getUsage());
        assertEquals("g/SEX", CliAnimalSyntax.SEX.getUsage());
        assertEquals("s/SPECIES", CliAnimalSyntax.SPECIES.getUsage());
        assertEquals("b/BREED", CliAnimalSyntax.BREED.getUsage());
        assertEquals("db/DATE_OF_BIRTH", CliAnimalSyntax.DATE_OF_BIRTH.getUsage());
        assertEquals("da/DATE_OF_ADMISSION", CliAnimalSyntax.DATE_OF_ADMISSION.getUsage());
    }

    @Test
    public void toString_returnsCorrectString() {
        assertEquals("NAME", CliAnimalSyntax.NAME.toString());
        assertEquals("PET_ID", CliAnimalSyntax.PET_ID.toString());
        assertEquals("SEX", CliAnimalSyntax.SEX.toString());
        assertEquals("SPECIES", CliAnimalSyntax.SPECIES.toString());
        assertEquals("BREED", CliAnimalSyntax.BREED.toString());
        assertEquals("DATE_OF_BIRTH", CliAnimalSyntax.DATE_OF_BIRTH.toString());
        assertEquals("DATE_OF_ADMISSION", CliAnimalSyntax.DATE_OF_ADMISSION.toString());
    }
}
