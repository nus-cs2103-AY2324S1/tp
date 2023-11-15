package seedu.address.model.policy;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LICENCE_PLATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LICENCE_PLATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_EXPIRY_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_EXPIRY_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_ISSUE_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_ISSUE_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_NO_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_NO_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.LicencePlate;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;

public class PolicyExpirationDateComparatorTest {

    private PolicyExpirationDateComparator comparator = new PolicyExpirationDateComparator();

    private Policy amyPolicy = new Policy(
            new Company(VALID_COMPANY_AMY),
            new PolicyNumber(VALID_POLICY_NO_AMY),
            new PolicyDate(VALID_POLICY_ISSUE_DATE_AMY),
            new PolicyDate(VALID_POLICY_EXPIRY_DATE_AMY)
    );

    private Policy amyPolicyCopy = new Policy(
            new Company(VALID_COMPANY_AMY),
            new PolicyNumber(VALID_POLICY_NO_AMY),
            new PolicyDate(VALID_POLICY_ISSUE_DATE_AMY),
            new PolicyDate(VALID_POLICY_EXPIRY_DATE_AMY)
    );

    private Policy bobPolicy = new Policy(
            new Company(VALID_COMPANY_BOB),
            new PolicyNumber(VALID_POLICY_NO_BOB),
            new PolicyDate(VALID_POLICY_ISSUE_DATE_BOB),
            new PolicyDate(VALID_POLICY_EXPIRY_DATE_BOB)
    );

    private Person amyPerson = new Person(
            new Name(VALID_NAME_AMY),
            new Phone(VALID_PHONE_AMY),
            new Email(VALID_EMAIL_AMY),
            new Address(VALID_ADDRESS_AMY),
            createTags(VALID_TAG_FRIEND),
            new Nric(VALID_NRIC_AMY),
            new LicencePlate(VALID_LICENCE_PLATE_AMY),
            new Remark(VALID_REMARK_AMY),
            amyPolicy
    );

    private Person bobPerson = new Person(
            new Name(VALID_NAME_BOB),
            new Phone(VALID_PHONE_BOB),
            new Email(VALID_EMAIL_BOB),
            new Address(VALID_ADDRESS_BOB),
            createTags(VALID_TAG_HUSBAND),
            new Nric(VALID_NRIC_BOB),
            new LicencePlate(VALID_LICENCE_PLATE_BOB),
            new Remark(VALID_REMARK_BOB),
            bobPolicy
    );

    private Person amyPersonCopy = new Person(
            new Name(VALID_NAME_AMY),
            new Phone(VALID_PHONE_AMY),
            new Email(VALID_EMAIL_AMY),
            new Address(VALID_ADDRESS_AMY),
            createTags(VALID_TAG_FRIEND),
            new Nric(VALID_NRIC_AMY),
            new LicencePlate(VALID_LICENCE_PLATE_AMY),
            new Remark(VALID_REMARK_AMY),
            amyPolicyCopy
    );

    private Set<Tag> createTags(String... tagNames) {
        Set<Tag> tags = new HashSet<>();
        for (String tagName : tagNames) {
            tags.add(new Tag(tagName));
        }
        return tags;
    }

    @Test
    public void compare_firstPersonExpiryDateBeforeSecondPerson_showsFirstPersonComesFirstInSort() {
        assertTrue(comparator.compare(bobPerson, amyPerson) < 0);
    }

    @Test
    public void compare_firstPersonExpiryDateSameAsSecondPerson_showsOrderDoesNotMatter() {
        assertTrue(comparator.compare(amyPersonCopy, amyPerson) == 0);
    }

    @Test
    public void compare_firstPersonExpiryDateAfterSecondPerson_showsSecondPersonComesFirstInSort() {
        assertTrue(comparator.compare(amyPerson, bobPerson) > 0);
    }

}
