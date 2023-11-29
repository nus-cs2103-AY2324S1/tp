package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.DentistAttributeContainsKeywordsPredicate;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.dentist.Dentist;
import seedu.address.testutil.DentistBuilder;

public class DentistAttributeContainsKeywordsPredicateTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void testValidYoeAttribute() {
        Dentist dentist = new DentistBuilder()
            .withName("John Doe")
            .withPhone("98765432")
            .withEmail("johndoe@gmail.com")
            .withAddress("456, Orchard Road, #12-345")
            .withSpecialization("Orthodontics")
            .withYoe("5")
            .withTags("friendly", "experienced")
            .build();

        model.addDentist(dentist);

        DentistAttributeContainsKeywordsPredicate predicate = new DentistAttributeContainsKeywordsPredicate(
            "experience", "5");
        assertTrue(predicate.test(dentist));
    }

    @Test
    public void testValidSpecializationAttribute() {
        Dentist dentist = new DentistBuilder()
            .withName("John Doe")
            .withPhone("98765432")
            .withEmail("johndoe@gmail.com")
            .withAddress("456, Orchard Road, #12-345")
            .withSpecialization("Orthodontics")
            .withYoe("5")
            .withTags("friendly", "experienced")
            .build();

        model.addDentist(dentist);
        DentistAttributeContainsKeywordsPredicate predicate = new DentistAttributeContainsKeywordsPredicate(
            "specialization", "Orthodontics");
        assertTrue(predicate.test(dentist));
    }

    @Test
    public void testInvalidAttribute() {
        Dentist dentist = new DentistBuilder()
            .withName("John Doe")
            .withPhone("98765432")
            .withEmail("johndoe@gmail.com")
            .withAddress("456, Orchard Road, #12-345")
            .withSpecialization("Orthodontics")
            .withYoe("5")
            .withTags("friendly", "experienced")
            .build();

        model.addDentist(dentist);
        DentistAttributeContainsKeywordsPredicate predicate = new DentistAttributeContainsKeywordsPredicate(
            "birthdate", "testing");
        assertFalse(predicate.test(dentist));
    }

    @Test
    public void testValidAttributeWithNoMatch() {
        Dentist dentist = new DentistBuilder()
            .withName("John Doe")
            .withPhone("98765432")
            .withEmail("johndoe@gmail.com")
            .withAddress("456, Orchard Road, #12-345")
            .withSpecialization("Orthodontics")
            .withYoe("5")
            .withTags("friendly", "experienced")
            .build();

        model.addDentist(dentist);
        DentistAttributeContainsKeywordsPredicate predicate = new DentistAttributeContainsKeywordsPredicate(
            "specialization", "blank");
        assertFalse(predicate.test(dentist));
    }

    @Test
    public void testValidEmailAttribute() {
        Dentist dentist = new DentistBuilder()
            .withName("John Doe")
            .withPhone("98765432")
            .withEmail("johndoe@gmail.com")
            .withAddress("456, Orchard Road, #12-345")
            .withSpecialization("Orthodontics")
            .withYoe("5")
            .withTags("friendly", "experienced")
            .build();

        model.addDentist(dentist);
        DentistAttributeContainsKeywordsPredicate predicate = new DentistAttributeContainsKeywordsPredicate(
            "email",
            "johndoe@gmail.com");
        assertTrue(predicate.test(dentist));
    }

    @Test
    public void testValidPhoneAttribute() {
        Dentist dentist = new DentistBuilder()
            .withName("John Doe")
            .withPhone("98765432")
            .withEmail("johndoe@gmail.com")
            .withAddress("456, Orchard Road, #12-345")
            .withSpecialization("Orthodontics")
            .withYoe("5")
            .withTags("friendly", "experienced")
            .build();

        model.addDentist(dentist);
        DentistAttributeContainsKeywordsPredicate predicate = new DentistAttributeContainsKeywordsPredicate(
            "phone",
            "98765432");
        assertTrue(predicate.test(dentist));
    }

    @Test
    public void testValidTagsAttribute() {
        Dentist dentist = new DentistBuilder()
            .withName("John Doe")
            .withPhone("98765432")
            .withEmail("johndoe@gmail.com")
            .withAddress("456, Orchard Road, #12-345")
            .withSpecialization("Orthodontics")
            .withYoe("5")
            .withTags("friendly", "experienced")
            .build();

        model.addDentist(dentist);
        DentistAttributeContainsKeywordsPredicate predicate = new DentistAttributeContainsKeywordsPredicate(
            "tags", "experienced");
        assertTrue(predicate.test(dentist));
    }

    @Test
    public void testValidAddressAttribute() {
        Dentist dentist = new DentistBuilder()
            .withName("John Doe")
            .withPhone("98765432")
            .withEmail("johndoe@gmail.com")
            .withAddress("456, Orchard Road, #12-345")
            .withSpecialization("Orthodontics")
            .withYoe("5")
            .withTags("friendly", "experienced")
            .build();

        model.addDentist(dentist);
        DentistAttributeContainsKeywordsPredicate predicate = new DentistAttributeContainsKeywordsPredicate(
            "address", "Orchard");
        assertTrue(predicate.test(dentist));
    }

    @Test
    public void testValidNameAttribute() {
        Dentist dentist = new DentistBuilder()
            .withName("John Doe")
            .withPhone("98765432")
            .withEmail("johndoe@gmail.com")
            .withAddress("456, Orchard Road, #12-345")
            .withSpecialization("Orthodontics")
            .withYoe("5")
            .withTags("friendly", "experienced")
            .build();

        model.addDentist(dentist);
        DentistAttributeContainsKeywordsPredicate predicate = new DentistAttributeContainsKeywordsPredicate(
            "name", "Doe");
        assertTrue(predicate.test(dentist));
    }
}
