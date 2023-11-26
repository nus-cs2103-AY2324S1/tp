package swe.context.model.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import swe.context.testutil.TestData;


public class EmailTest {
    @Test
    public void isValid() {
        // blank email
        assertFalse(Email.isValid("")); // empty string
        assertFalse(Email.isValid(" ")); // spaces only

        // missing parts
        assertFalse(Email.isValid("@example.com")); // missing local part
        assertFalse(Email.isValid("peterjackexample.com")); // missing '@' symbol
        assertFalse(Email.isValid("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(Email.isValid("peterjack@-")); // invalid domain name
        assertFalse(Email.isValid("peterjack@exam_ple.com")); // underscore in domain name
        assertFalse(Email.isValid("peter jack@example.com")); // spaces in local part
        assertFalse(Email.isValid("peterjack@exam ple.com")); // spaces in domain name
        assertFalse(Email.isValid(" peterjack@example.com")); // leading space
        assertFalse(Email.isValid("peterjack@example.com ")); // trailing space
        assertFalse(Email.isValid("peterjack@@example.com")); // double '@' symbol
        assertFalse(Email.isValid("peter@jack@example.com")); // '@' symbol in local part
        assertFalse(Email.isValid("-peterjack@example.com")); // local part starts with a hyphen
        assertFalse(Email.isValid("peterjack-@example.com")); // local part ends with a hyphen
        assertFalse(Email.isValid("peter..jack@example.com")); // local part has two consecutive periods
        assertFalse(Email.isValid("peterjack@example@com")); // '@' symbol in domain name
        assertFalse(Email.isValid("peterjack@.example.com")); // domain name starts with a period
        assertFalse(Email.isValid("peterjack@example.com.")); // domain name ends with a period
        assertFalse(Email.isValid("peterjack@-example.com")); // domain name starts with a hyphen
        assertFalse(Email.isValid("peterjack@example.com-")); // domain name ends with a hyphen

        // valid email
        assertTrue(Email.isValid("PeterJack_1190@example.com")); // underscore in local part
        assertTrue(Email.isValid("PeterJack.1190@example.com")); // period in local part
        assertTrue(Email.isValid("PeterJack+1190@example.com")); // '+' symbol in local part
        assertTrue(Email.isValid("PeterJack-1190@example.com")); // hyphen in local part
        assertTrue(Email.isValid("a1+be.d@example1.com")); // mixture of alphanumeric and special characters
        assertTrue(Email.isValid("peter_jack@very-very-very-long-example.com")); // long domain name
        assertTrue(Email.isValid("if.you.dream.it_you.can.do.it@example.com")); // long local part
        assertTrue(Email.isValid("e1234567@u.nus.edu")); // more than one period in domain
        assertTrue(Email.isValid("cloud@my-domain.x")); // Short top level domain
    }

    @Test
    public void equals() {
        Email amy = new Email(TestData.Valid.EMAIL_AMY);

        // same values -> return true
        Email amyCopy = new Email(TestData.Valid.EMAIL_AMY);
        assertTrue(amy.equals(amyCopy));

        // same objects -> return true
        assertTrue(amy.equals(amy));

        // different type -> return false
        assertFalse(amy.equals(1));

        // null -> return false
        assertFalse(amy.equals(null));

        // different email -> return false
        Email bob = new Email(TestData.Valid.EMAIL_BOB);
        assertFalse(amy.equals(bob));
    }
}
