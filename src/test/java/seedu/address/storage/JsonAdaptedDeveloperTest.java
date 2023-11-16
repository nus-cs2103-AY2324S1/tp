package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedDeveloper.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDevelopers.BENSON;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.commons.Date;
import seedu.address.model.commons.Name;
import seedu.address.model.developer.DeveloperRoles;
import seedu.address.model.developer.GithubId;
import seedu.address.model.developer.Rating;
import seedu.address.model.developer.Salary;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Phone;

public class JsonAdaptedDeveloperTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_DATE_JOINED = "2019";
    private static final String INVALID_ROLE = "hiring";
    private static final String INVALID_SALARY = "300";
    private static final String INVALID_RATING = "-5";
    private static final String INVALID_GITHUBID = "a";
    private static final String VALID_DATE_JOINED = BENSON.getDateJoined().toString();
    private static final String VALID_ROLE = BENSON.getRole().toString();
    private static final String VALID_SALARY = BENSON.getSalary().toString();
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_RATING = BENSON.getRating().toString();
    private static final String VALID_GITHUBID = BENSON.getGithubId().toString();
    private static final List<String> VALID_PROJECTS = new ArrayList<>(BENSON.getProjects());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedDeveloper person = new JsonAdaptedDeveloper(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedDeveloper person =
                new JsonAdaptedDeveloper(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_DATE_JOINED,
                        VALID_ROLE, VALID_SALARY, VALID_PROJECTS, VALID_GITHUBID, VALID_RATING);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedDeveloper person = new JsonAdaptedDeveloper(null, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_DATE_JOINED, VALID_ROLE, VALID_SALARY, VALID_PROJECTS,
                VALID_GITHUBID, VALID_RATING);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedDeveloper person =
                new JsonAdaptedDeveloper(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_DATE_JOINED,
                        VALID_ROLE, VALID_SALARY, VALID_PROJECTS, VALID_GITHUBID, VALID_RATING);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedDeveloper person = new JsonAdaptedDeveloper(VALID_NAME, null, VALID_EMAIL,
                VALID_ADDRESS, VALID_DATE_JOINED, VALID_ROLE, VALID_SALARY, VALID_PROJECTS,
                VALID_GITHUBID, VALID_RATING);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedDeveloper person =
                new JsonAdaptedDeveloper(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS, VALID_DATE_JOINED,
                        VALID_ROLE, VALID_SALARY, VALID_PROJECTS, VALID_GITHUBID, VALID_RATING);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedDeveloper person = new JsonAdaptedDeveloper(VALID_NAME, VALID_PHONE, null,
                VALID_ADDRESS, VALID_DATE_JOINED, VALID_ROLE, VALID_SALARY, VALID_PROJECTS,
                VALID_GITHUBID, VALID_RATING);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedDeveloper person =
                new JsonAdaptedDeveloper(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS, VALID_DATE_JOINED,
                        VALID_ROLE, VALID_SALARY, VALID_PROJECTS, VALID_GITHUBID, VALID_RATING);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedDeveloper person = new JsonAdaptedDeveloper(VALID_NAME, VALID_PHONE, VALID_EMAIL,
                null, VALID_DATE_JOINED, VALID_ROLE, VALID_SALARY, VALID_PROJECTS,
                VALID_GITHUBID, VALID_RATING);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidDateJoined_throwsIllegalValueException() {
        JsonAdaptedDeveloper person = new JsonAdaptedDeveloper(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, INVALID_DATE_JOINED,
                VALID_ROLE, VALID_SALARY, VALID_PROJECTS, VALID_GITHUBID, VALID_RATING);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullDateJoined_throwsIllegalValueException() {
        JsonAdaptedDeveloper person = new JsonAdaptedDeveloper(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, null,
                VALID_ROLE, VALID_SALARY, VALID_PROJECTS, VALID_GITHUBID, VALID_RATING);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidRating_throwsIllegalValueException() {
        JsonAdaptedDeveloper person = new JsonAdaptedDeveloper(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_DATE_JOINED,
                VALID_ROLE, VALID_SALARY, VALID_PROJECTS, VALID_GITHUBID, INVALID_RATING);
        String expectedMessage = Rating.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullRating_throwsIllegalValueException() {
        JsonAdaptedDeveloper person = new JsonAdaptedDeveloper(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_DATE_JOINED,
                VALID_ROLE, VALID_SALARY, VALID_PROJECTS, VALID_GITHUBID, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Rating.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidGithubID_throwsIllegalValueException() {
        JsonAdaptedDeveloper person = new JsonAdaptedDeveloper(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_DATE_JOINED,
                VALID_ROLE, VALID_SALARY, VALID_PROJECTS, INVALID_GITHUBID, VALID_RATING);
        String expectedMessage = GithubId.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullGithubID_throwsIllegalValueException() {
        JsonAdaptedDeveloper person = new JsonAdaptedDeveloper(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_DATE_JOINED,
                VALID_ROLE, VALID_SALARY, VALID_PROJECTS, null, VALID_RATING);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, GithubId.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    /*@Test
    public void toModelType_invalidRole_throwsIllegalValueException() {
        JsonAdaptedDeveloper person = new JsonAdaptedDeveloper(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_DATE_JOINED,
                VALID_ROLE, VALID_SALARY, VALID_PROJECTS, INVALID_ROLE, VALID_RATING);
        String expectedMessage = DeveloperRoles.NO_SUCH_DEVELOPER_ROLE;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }*/

    @Test
    public void toModelType_nullRole_throwsIllegalValueException() {
        JsonAdaptedDeveloper person = new JsonAdaptedDeveloper(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_DATE_JOINED,
                null, VALID_SALARY, VALID_PROJECTS, VALID_GITHUBID, VALID_RATING);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, DeveloperRoles.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidSalary_throwsIllegalValueException() {
        JsonAdaptedDeveloper person = new JsonAdaptedDeveloper(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_DATE_JOINED,
                VALID_ROLE, INVALID_SALARY, VALID_PROJECTS, VALID_GITHUBID, VALID_RATING);
        String expectedMessage = Salary.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullSalary_throwsIllegalValueException() {
        JsonAdaptedDeveloper person = new JsonAdaptedDeveloper(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_DATE_JOINED,
                VALID_ROLE, null, VALID_PROJECTS, VALID_GITHUBID, VALID_RATING);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Salary.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

}
