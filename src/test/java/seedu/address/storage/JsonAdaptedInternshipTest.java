package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedInternship.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInternships.JANESTREET;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.Deadline;
import seedu.address.model.internship.Duration;
import seedu.address.model.internship.Role;
import seedu.address.model.internship.StartDate;
import seedu.address.model.requirement.Requirement;

public class JsonAdaptedInternshipTest {

    private static final String INVALID_COMPANY_NAME = "Cit@del";
    private static final String INVALID_APPLICATION_STATUS = "Y3t to apply";
    private static final String INVALID_DEADLINE = "123123123/1-11/1";
    private static final String INVALID_DURATION = " ";
    private static final String INVALID_ROLE = "C0ff33 M@k&r";
    private static final String INVALID_START_DATE = "50/50/-1111";
    private static final String INVALID_REQUIREMENT = "哈哈";

    private static final String VALID_COMPANY_NAME = JANESTREET.getCompanyName().toString();
    private static final String VALID_APPLICATION_STATUS = JANESTREET.getApplicationStatus().toString();
    private static final String VALID_DEADLINE = JANESTREET.getDeadline().toString();
    private static final String VALID_DURATION = JANESTREET.getDuration().toString();
    private static final String VALID_ROLE = JANESTREET.getRole().toString();
    private static final String VALID_START_DATE = JANESTREET.getStartDate().toString();
    private static final List<JsonAdaptedRequirement> VALID_REQUIREMENTS = JANESTREET.getRequirements().stream()
            .map(JsonAdaptedRequirement::new)
            .collect(Collectors.toList());

    /**
     * A static class to hold the test data.
     */
    private static class TestData {
        private final String companyName;
        private final String role;
        private final String applicationStatus;
        private final String deadline;
        private final String startDate;
        private final String duration;
        private final List<JsonAdaptedRequirement> requirements;
        private final String expectedMessage;

        TestData(String companyName, String role, String applicationStatus,
                 String deadline, String startDate, String duration,
                 List<JsonAdaptedRequirement> requirements, String expectedMessage) {
            this.companyName = companyName;
            this.role = role;
            this.applicationStatus = applicationStatus;
            this.deadline = deadline;
            this.startDate = startDate;
            this.duration = duration;
            this.requirements = requirements;
            this.expectedMessage = expectedMessage;
        }
    }

    /**
     * Returns the null error message associated with the particular parameter type when the parameter value is missing.
     * @param fieldType A valid parameter type in Internship.
     * @return The correct null error message.
     */
    private static String expectedMissingFieldMessage(Class<?> fieldType) {
        return String.format(MISSING_FIELD_MESSAGE_FORMAT, fieldType.getSimpleName());
    }

    @ParameterizedTest
    @MethodSource("provideInvalidDataForModelType")
    public void toModelType_invalidData_throwsIllegalValueException(TestData data) {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(
                data.companyName, data.role, data.applicationStatus, data.deadline,
                data.startDate, data.duration, data.requirements);

        assertThrows(IllegalValueException.class, data.expectedMessage, internship::toModelType);
    }

    private static Stream<Arguments> provideInvalidDataForModelType() {
        List<JsonAdaptedRequirement> invalidRequirements = new ArrayList<>(VALID_REQUIREMENTS);
        invalidRequirements.add(new JsonAdaptedRequirement(INVALID_REQUIREMENT));

        return Stream.of(
                new TestData(INVALID_COMPANY_NAME, VALID_ROLE, VALID_APPLICATION_STATUS, VALID_DEADLINE,
                        VALID_START_DATE, VALID_DURATION, VALID_REQUIREMENTS, CompanyName.MESSAGE_CONSTRAINTS),
                new TestData(null, VALID_ROLE, VALID_APPLICATION_STATUS, VALID_DEADLINE, VALID_START_DATE,
                        VALID_DURATION, VALID_REQUIREMENTS, expectedMissingFieldMessage(CompanyName.class)),
                new TestData(VALID_COMPANY_NAME, INVALID_ROLE, VALID_APPLICATION_STATUS, VALID_DEADLINE,
                        VALID_START_DATE, VALID_DURATION, VALID_REQUIREMENTS, Role.MESSAGE_CONSTRAINTS),
                new TestData(VALID_COMPANY_NAME, null, VALID_APPLICATION_STATUS, VALID_DEADLINE,
                        VALID_START_DATE, VALID_DURATION, VALID_REQUIREMENTS, expectedMissingFieldMessage(Role.class)),
                new TestData(VALID_COMPANY_NAME, VALID_ROLE, INVALID_APPLICATION_STATUS, VALID_DEADLINE,
                        VALID_START_DATE, VALID_DURATION, VALID_REQUIREMENTS, ApplicationStatus.MESSAGE_CONSTRAINTS),
                new TestData(VALID_COMPANY_NAME, VALID_ROLE, null, VALID_DEADLINE, VALID_START_DATE,
                        VALID_DURATION, VALID_REQUIREMENTS, expectedMissingFieldMessage(ApplicationStatus.class)),
                new TestData(VALID_COMPANY_NAME, VALID_ROLE, VALID_APPLICATION_STATUS, INVALID_DEADLINE,
                        VALID_START_DATE, VALID_DURATION, VALID_REQUIREMENTS, Deadline.MESSAGE_CONSTRAINTS),
                new TestData(VALID_COMPANY_NAME, VALID_ROLE, VALID_APPLICATION_STATUS, null,
                        VALID_START_DATE, VALID_DURATION, VALID_REQUIREMENTS,
                        expectedMissingFieldMessage(Deadline.class)),
                new TestData(VALID_COMPANY_NAME, VALID_ROLE, VALID_APPLICATION_STATUS, VALID_DEADLINE,
                        INVALID_START_DATE, VALID_DURATION, VALID_REQUIREMENTS, StartDate.MESSAGE_CONSTRAINTS),
                new TestData(VALID_COMPANY_NAME, VALID_ROLE, VALID_APPLICATION_STATUS, VALID_DEADLINE, null,
                        VALID_DURATION, VALID_REQUIREMENTS, expectedMissingFieldMessage(StartDate.class)),
                new TestData(VALID_COMPANY_NAME, VALID_ROLE, VALID_APPLICATION_STATUS, VALID_DEADLINE,
                        VALID_START_DATE, INVALID_DURATION, VALID_REQUIREMENTS, Duration.MESSAGE_CONSTRAINTS),
                new TestData(VALID_COMPANY_NAME, VALID_ROLE, VALID_APPLICATION_STATUS, VALID_DEADLINE,
                        VALID_START_DATE, null, VALID_REQUIREMENTS,
                        expectedMissingFieldMessage(Duration.class)),
                new TestData(VALID_COMPANY_NAME, VALID_ROLE, VALID_APPLICATION_STATUS, VALID_DEADLINE,
                        VALID_START_DATE, VALID_DURATION, invalidRequirements, Requirement.MESSAGE_CONSTRAINTS)
        ).map(Arguments::of);
    }

    @Test
    public void toModelType_validInternshipDetails_returnsInternship() throws Exception {
        JsonAdaptedInternship internship = new JsonAdaptedInternship(JANESTREET);
        assertEquals(JANESTREET, internship.toModelType());
    }

}
