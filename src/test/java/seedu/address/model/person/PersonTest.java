package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPOINTMENT_NAME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FINANCIAL_PLAN_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NEXT_OF_KIN_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NEXT_OF_KIN_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.ELLE;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.model.financialplan.FinancialPlan;
import seedu.address.testutil.PersonBuilder;

public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withNextOfKinName(VALID_NEXT_OF_KIN_NAME_BOB)
                .withNextOfKinPhone(VALID_NEXT_OF_KIN_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Person editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PersonBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different nextOfKinName -> returns false
        editedAlice = new PersonBuilder(ALICE).withNextOfKinName(VALID_NEXT_OF_KIN_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different nextOfKinPhone -> returns false
        editedAlice = new PersonBuilder(ALICE).withNextOfKinPhone(VALID_NEXT_OF_KIN_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));

        // different Appointment -> returns false
        Person editedBenson = new PersonBuilder(BENSON).withAppointment(VALID_APPOINTMENT_NAME
                + ", " + VALID_APPOINTMENT_DATE).build();
        assertFalse(BENSON.equals(editedBenson));
    }

    @Test
    public void gatherEmailsByFinancialPlan_noPersonFound() {
        assertEquals(new String(), ELLE.gatherEmailsContainsFinancialPlan(VALID_FINANCIAL_PLAN_1));
    }

    @Test
    public void gatherEmailsByFinancialPlan_personFound() {
        // prompt is full plan name
        FinancialPlan elleFinancialPlan = ELLE.getFinancialPlans().iterator().next();
        String prompt = elleFinancialPlan.toString().replaceAll("[\\[\\]\\(\\)]", "");
        String prompt2 = "Sample Financial Plan 2";
        assertEquals(ELLE.getEmail().toString(), ELLE.gatherEmailsContainsFinancialPlan(prompt));
        assertEquals(ELLE.getEmail().toString(), ELLE.gatherEmailsContainsFinancialPlan(prompt2));
    }

    @Test
    public void gatherEmailsByFinancialPlan_substring_personFound() {
        String substring = "Financial Plan 2";
        assertEquals(ELLE.getEmail().toString(), ELLE.gatherEmailsContainsFinancialPlan(substring));
    }

    @Test
    public void gatherEmailsByFinancialPlan_noDuplicateEmail() {
        // prompt is substring for both bobs Financial Plans: "financial plan 1" and "financial plan 2"
        String substringBob = "financial plan";
        assertEquals(BOB.getEmail().toString(), BOB.gatherEmailsContainsFinancialPlan(substringBob));
    }

    @Test
    public void gatherEmailsByFinancialPlan_caseInsensitive() {
        String uppercaseBob = VALID_FINANCIAL_PLAN_1.toUpperCase();
        String lowercaseBob = VALID_FINANCIAL_PLAN_1.toLowerCase();
        assertEquals(BOB.getEmail().toString(), BOB.gatherEmailsContainsFinancialPlan(uppercaseBob));
        assertEquals(BOB.getEmail().toString(), BOB.gatherEmailsContainsFinancialPlan(lowercaseBob));
    }

    @Test
    public void gatherByTag_noPersonFound() {
        assertEquals(new String(), ALICE.gatherEmailsContainsTag(VALID_TAG_HUSBAND));
    }

    @Test
    public void gatherByTag_personFound() {
        // prompt full tag name
        assertEquals(BOB.getEmail().toString(), BOB.gatherEmailsContainsTag(VALID_TAG_HUSBAND));
    }

    @Test
    public void gatherByTag_substring_personFound() {
        // prompt substring
        String substring = "hus";
        assertEquals(BOB.getEmail().toString(), BOB.gatherEmailsContainsTag(substring));
    }

    @Test
    public void gatherEmailsByTag_noDuplicateEmail() {
        // prompt is substring for both bobs tags "friend" and "husband"
        String substringBob = "nd";
        assertEquals(BOB.getEmail().toString(), BOB.gatherEmailsContainsTag(substringBob));
    }

    @Test
    public void gatherEmailsByTag_caseInsensitive() {
        String uppercaseBob = VALID_TAG_HUSBAND.toUpperCase();
        String lowercaseBob = VALID_TAG_HUSBAND.toLowerCase();
        assertEquals(BOB.getEmail().toString(), BOB.gatherEmailsContainsTag(uppercaseBob));
        assertEquals(BOB.getEmail().toString(), BOB.gatherEmailsContainsTag(lowercaseBob));
    }

    @Test
    public void toStringMethod() {
        String expected = Person.class.getCanonicalName() + "{name=" + ALICE.getName() + ", phone=" + ALICE.getPhone()
                + ", email=" + ALICE.getEmail() + ", address=" + ALICE.getAddress() + ", nextOfKinName="
                + ALICE.getNextOfKinName() + ", nextOfKinPhone=" + ALICE.getNextOfKinPhone()
                + ", financialPlans=" + ALICE.getFinancialPlans() + ", tags=" + ALICE.getTags()
                + ", appointment=" + ALICE.getAppointment() + "}";
        assertEquals(expected, ALICE.toString());
    }

    @Test
    public void isSameAppointmentDate() {
        LocalDate matchingDate = LocalDate.of(2023, 05, 01);
        LocalDate nonMatchingDate = LocalDate.of(2023, 02, 01);

        assertTrue(BENSON.isSameAppointmentDate(matchingDate));
        assertFalse(BENSON.isSameAppointmentDate(nonMatchingDate));
    }

    @Test
    public void hasNullAppointment() {
        assertFalse(BENSON.hasNullAppointment());
        assertTrue(ALICE.hasNullAppointment());
    }

    @Test
    public void clearAppointment() {
        Person editedBenson = new PersonBuilder().withName("Benson Meier")
                .withAddress("311, Clementi Ave 2, #02-25").withEmail("johnd@example.com")
                .withPhone("98765432").withNextOfKinName("Benson Dad").withNextOfKinPhone("98761111")
                .withFinancialPlans("Sample Financial Plan 1", "Sample Financial Plan 2")
                .withTags("owesMoney", "friends").withNullAppointment().build();

        assertEquals(BENSON.clearAppointment(), editedBenson);
    }
}
