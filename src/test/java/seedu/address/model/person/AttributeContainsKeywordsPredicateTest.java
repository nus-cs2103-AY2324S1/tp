package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AttributeContainsKeywordsPredicate;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.patients.Patient;
import seedu.address.testutil.PatientBuilder;

public class AttributeContainsKeywordsPredicateTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void testValidGenderAttribute() {
        Patient patient = new PatientBuilder()
            .withName("John Doe")
            .withPhone("91110000")
            .withBirthdate("03-02-1999")
            .withGender("M")
            .withRemark("Peanut Allergy")
            .withTreatmentName("Cleaning")
            .withAddress("128A Rodeo Drive")
            .withEmail("johndoe@gmail.com")
            .withTags("new")
            .build();

        model.addPatient(patient);
        AttributeContainsKeywordsPredicate predicate = new AttributeContainsKeywordsPredicate("gender", "M");
        assertTrue(predicate.test(patient));
    }

    @Test
    public void testValidBirthdayAttribute() {
        Patient patient = new PatientBuilder()
            .withName("John Doe")
            .withPhone("91110000")
            .withBirthdate("03-02-1999")
            .withGender("M")
            .withRemark("Peanut Allergy")
            .withTreatmentName("Cleaning")
            .withAddress("128A Rodeo Drive")
            .withEmail("johndoe@gmail.com")
            .withTags("new")
            .build();

        model.addPatient(patient);
        AttributeContainsKeywordsPredicate predicate = new AttributeContainsKeywordsPredicate("birthday", "03-02-1999");
        assertTrue(predicate.test(patient));
    }

    @Test
    public void testInvalidAttribute() {
        Patient patient = new PatientBuilder()
            .withName("John Doe")
            .withPhone("91110000")
            .withBirthdate("03-02-1999")
            .withGender("M")
            .withRemark("Peanut Allergy")
            .withTreatmentName("Cleaning")
            .withAddress("128A Rodeo Drive")
            .withEmail("johndoe@gmail.com")
            .withTags("new")
            .build();

        model.addPatient(patient);
        AttributeContainsKeywordsPredicate predicate = new AttributeContainsKeywordsPredicate("invalid", "testing");
        assertFalse(predicate.test(patient));
    }

    @Test
    public void testValidAttributeWithNoMatch() {
        Patient patient = new PatientBuilder()
            .withName("John Doe")
            .withPhone("91110000")
            .withBirthdate("03-02-1999")
            .withGender("M")
            .withRemark("Peanut Allergy")
            .withTreatmentName("Cleaning")
            .withAddress("128A Rodeo Drive")
            .withEmail("johndoe@gmail.com")
            .withTags("new")
            .build();

        model.addPatient(patient);
        AttributeContainsKeywordsPredicate predicate = new AttributeContainsKeywordsPredicate("phone", "87665555");
        assertFalse(predicate.test(patient));
    }

    @Test
    public void testValidEmailAttribute() {
        Patient patient = new PatientBuilder()
            .withName("John Doe")
            .withPhone("91110000")
            .withBirthdate("03-02-1999")
            .withGender("M")
            .withRemark("Peanut Allergy")
            .withTreatmentName("Cleaning")
            .withAddress("128A Rodeo Drive")
            .withEmail("johndoe@gmail.com")
            .withTags("new")
            .build();

        model.addPatient(patient);
        AttributeContainsKeywordsPredicate predicate = new AttributeContainsKeywordsPredicate("email",
            "johndoe@gmail.com");
        assertTrue(predicate.test(patient));
    }

    @Test
    public void testValidPhoneAttribute() {
        Patient patient = new PatientBuilder()
            .withName("John Doe")
            .withPhone("91110000")
            .withBirthdate("03-02-1999")
            .withGender("M")
            .withRemark("Peanut Allergy")
            .withTreatmentName("Cleaning")
            .withAddress("128A Rodeo Drive")
            .withEmail("johndoe@gmail.com")
            .withTags("new")
            .build();

        model.addPatient(patient);
        AttributeContainsKeywordsPredicate predicate = new AttributeContainsKeywordsPredicate("phone", "91110000");
        assertTrue(predicate.test(patient));
    }

    @Test
    public void testValidTagsAttribute() {
        Patient patient = new PatientBuilder()
            .withName("John Doe")
            .withPhone("91110000")
            .withBirthdate("03-02-1999")
            .withGender("M")
            .withRemark("Peanut Allergy")
            .withTreatmentName("Cleaning")
            .withAddress("128A Rodeo Drive")
            .withEmail("johndoe@gmail.com")
            .withTags("new")
            .build();

        model.addPatient(patient);
        AttributeContainsKeywordsPredicate predicate = new AttributeContainsKeywordsPredicate("tags", "new");
        assertTrue(predicate.test(patient));
    }

    @Test
    public void testValidRemarkAttribute() {
        Patient patient = new PatientBuilder()
            .withName("John Doe")
            .withPhone("91110000")
            .withBirthdate("03-02-1999")
            .withGender("M")
            .withRemark("Peanut Allergy")
            .withTreatmentName("Cleaning")
            .withAddress("128A Rodeo Drive")
            .withEmail("johndoe@gmail.com")
            .withTags("new")
            .build();

        model.addPatient(patient);
        AttributeContainsKeywordsPredicate predicate = new AttributeContainsKeywordsPredicate("remark", "peanut");
        assertTrue(predicate.test(patient));
    }

    @Test
    public void testValidTreatmentAttribute() {
        Patient patient = new PatientBuilder()
            .withName("John Doe")
            .withPhone("91110000")
            .withBirthdate("03-02-1999")
            .withGender("M")
            .withRemark("Peanut Allergy")
            .withTreatmentName("Cleaning")
            .withAddress("128A Rodeo Drive")
            .withEmail("johndoe@gmail.com")
            .withTags("new")
            .build();

        model.addPatient(patient);
        AttributeContainsKeywordsPredicate predicate = new AttributeContainsKeywordsPredicate("treatment", "Cleaning");
        assertTrue(predicate.test(patient));
    }

    @Test
    public void testValidAddressAttribute() {
        Patient patient = new PatientBuilder()
            .withName("John Doe")
            .withPhone("91110000")
            .withBirthdate("03-02-1999")
            .withGender("M")
            .withRemark("Peanut Allergy")
            .withTreatmentName("Cleaning")
            .withAddress("128A Rodeo Drive")
            .withEmail("johndoe@gmail.com")
            .withTags("new")
            .build();

        model.addPatient(patient);
        AttributeContainsKeywordsPredicate predicate = new AttributeContainsKeywordsPredicate("address", "Rodeo");
        assertTrue(predicate.test(patient));
    }
}
