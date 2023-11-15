package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LICENCE_PLATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_EXPIRY_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_ISSUE_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_NO_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.model.policy.Company;
import seedu.address.model.policy.Policy;
import seedu.address.model.policy.PolicyDate;
import seedu.address.model.policy.PolicyNumber;
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

        // same name, all other attributes different -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND).withNric(VALID_NRIC_BOB)
                .withLicencePlate(VALID_LICENCE_PLATE_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

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

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));

        // different nric -> returns false
        editedAlice = new PersonBuilder(ALICE).withNric(VALID_NRIC_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different licence plate -> returns false
        editedAlice = new PersonBuilder(ALICE).withLicencePlate(VALID_LICENCE_PLATE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different policy -> returns false
        editedAlice = new PersonBuilder(ALICE).withPolicy(VALID_COMPANY_BOB, VALID_POLICY_NO_AMY,
                VALID_POLICY_ISSUE_DATE_AMY, VALID_POLICY_EXPIRY_DATE_AMY).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Person.class.getCanonicalName() + "{name=" + ALICE.getName() + ", phone=" + ALICE.getPhone()
                + ", email=" + ALICE.getEmail() + ", address=" + ALICE.getAddress() + ", tags=" + ALICE.getTags()
                + ", nric=" + ALICE.getNric() + ", licence plate=" + ALICE.getLicencePlate()
                + ", remark=" + ALICE.getRemark() + ", policy="
                + Policy.class.getCanonicalName() + "{company=" + ALICE.getPolicy().getCompany()
                + ", policy number=" + ALICE.getPolicy().getPolicyNumber()
                + ", policy issue date=" + ALICE.getPolicy().getPolicyIssueDate() + ", policy expiry date="
                + ALICE.getPolicy().getPolicyExpiryDate() + "}}";
        assertEquals(expected, ALICE.toString());
    }

    @Test
    public void hasDefaultCompanyParameter_defaultPolicy_success() {
        Policy defautltPolicy = new Policy(new Company(Company.DEFAULT_VALUE),
                new PolicyNumber(PolicyNumber.DEFAULT_VALUE),
                new PolicyDate(PolicyDate.DEFAULT_VALUE),
                new PolicyDate(PolicyDate.DEFAULT_VALUE));
        Person defaultPerson = new Person(ALICE.getName(),
                ALICE.getPhone(),
                ALICE.getEmail(),
                ALICE.getAddress(),
                ALICE.getTags(),
                ALICE.getNric(),
                ALICE.getLicencePlate(),
                ALICE.getRemark(),
                defautltPolicy);
        assertTrue(defaultPerson.hasDefaultCompanyPolicyParameter());
    }

    @Test
    public void hasDefaultPolicyNumberParameter_defaultPolicy_success() {
        Policy defaultPolicy = new Policy(new Company(Company.DEFAULT_VALUE),
                new PolicyNumber(PolicyNumber.DEFAULT_VALUE),
                new PolicyDate(PolicyDate.DEFAULT_VALUE),
                new PolicyDate(PolicyDate.DEFAULT_VALUE));
        Person defaultPerson = new Person(ALICE.getName(),
                ALICE.getPhone(),
                ALICE.getEmail(),
                ALICE.getAddress(),
                ALICE.getTags(),
                ALICE.getNric(),
                ALICE.getLicencePlate(),
                ALICE.getRemark(),
                defaultPolicy);
        assertTrue(defaultPerson.hasDefaultPolicyNumberParameter());
    }

    @Test
    public void hasDefaultPolicyIssueDateParameter_defaultPolicy_success() {
        Policy defaultPolicy = new Policy(new Company(Company.DEFAULT_VALUE),
                new PolicyNumber(PolicyNumber.DEFAULT_VALUE),
                new PolicyDate(PolicyDate.DEFAULT_VALUE),
                new PolicyDate(PolicyDate.DEFAULT_VALUE));
        Person defaultPerson = new Person(ALICE.getName(),
                ALICE.getPhone(),
                ALICE.getEmail(),
                ALICE.getAddress(),
                ALICE.getTags(),
                ALICE.getNric(),
                ALICE.getLicencePlate(),
                ALICE.getRemark(),
                defaultPolicy);
        assertTrue(defaultPerson.hasDefaultPolicyIssueDateParameter());
    }

    @Test
    public void hasDefaultPolicyExpiryDateParameter_defaultPolicy_success() {
        Policy defaultPolicy = new Policy(new Company(Company.DEFAULT_VALUE),
                new PolicyNumber(PolicyNumber.DEFAULT_VALUE),
                new PolicyDate(PolicyDate.DEFAULT_VALUE),
                new PolicyDate(PolicyDate.DEFAULT_VALUE));
        Person defaultPerson = new Person(ALICE.getName(),
                ALICE.getPhone(),
                ALICE.getEmail(),
                ALICE.getAddress(),
                ALICE.getTags(),
                ALICE.getNric(),
                ALICE.getLicencePlate(),
                ALICE.getRemark(),
                defaultPolicy);
        assertTrue(defaultPerson.hasDefaultPolicyExpiryDateParameter());
    }

    @Test
    public void cloneWithoutPolicy_filledOutPolicy_returnsDefaultPolicy() {
        Policy defaultPolicy = new Policy(new Company(Company.DEFAULT_VALUE),
                new PolicyNumber(PolicyNumber.DEFAULT_VALUE),
                new PolicyDate(PolicyDate.DEFAULT_VALUE),
                new PolicyDate(PolicyDate.DEFAULT_VALUE));
        Person defaultPolicyAlice = new Person(ALICE.getName(),
                ALICE.getPhone(),
                ALICE.getEmail(),
                ALICE.getAddress(),
                ALICE.getTags(),
                ALICE.getNric(),
                ALICE.getLicencePlate(),
                ALICE.getRemark(),
                defaultPolicy);

        Policy filledPolicy = new Policy(new Company(VALID_COMPANY_BOB),
                new PolicyNumber(VALID_POLICY_NO_AMY), new PolicyDate(VALID_POLICY_ISSUE_DATE_AMY),
                new PolicyDate(VALID_POLICY_EXPIRY_DATE_AMY));
        Person aliceWithPolicy = new Person(ALICE.getName(),
                ALICE.getPhone(),
                ALICE.getEmail(),
                ALICE.getAddress(),
                ALICE.getTags(),
                ALICE.getNric(),
                ALICE.getLicencePlate(),
                ALICE.getRemark(),
                filledPolicy);

        assertEquals(aliceWithPolicy.cloneWithoutPolicy(),
                defaultPolicyAlice);
    }

    @Test
    public void compareDatesTest_comparingPolicyWitIdenticalIssueAndExpiryDate_showsSameDateOuput() {
        Policy defaultPolicy = new Policy(new Company(Company.DEFAULT_VALUE),
                new PolicyNumber(PolicyNumber.DEFAULT_VALUE),
                new PolicyDate(PolicyDate.DEFAULT_VALUE),
                new PolicyDate(PolicyDate.DEFAULT_VALUE));
        Person defaultPolicyAlice = new Person(ALICE.getName(),
                ALICE.getPhone(),
                ALICE.getEmail(),
                ALICE.getAddress(),
                ALICE.getTags(),
                ALICE.getNric(),
                ALICE.getLicencePlate(),
                ALICE.getRemark(),
                defaultPolicy);
        assertTrue(defaultPolicyAlice.comparePolicyDates() == 0);
    }

    @Test
    public void compareDatesTest_comparingValidPolicyWithIssueBeforeExpiry_showsValidDateOutput() {
        Policy filledPolicy = new Policy(new Company(VALID_COMPANY_BOB),
                new PolicyNumber(VALID_POLICY_NO_AMY), new PolicyDate(VALID_POLICY_ISSUE_DATE_AMY),
                new PolicyDate(VALID_POLICY_EXPIRY_DATE_AMY));
        Person aliceWithPolicy = new Person(ALICE.getName(),
                ALICE.getPhone(),
                ALICE.getEmail(),
                ALICE.getAddress(),
                ALICE.getTags(),
                ALICE.getNric(),
                ALICE.getLicencePlate(),
                ALICE.getRemark(),
                filledPolicy);
        assertTrue(aliceWithPolicy.comparePolicyDates() > 0);
    }
}
