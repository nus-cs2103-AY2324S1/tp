package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPlan.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPlans.GAMING;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.person.Name;
import seedu.address.model.plan.PlanDateTime;
import seedu.address.model.plan.PlanName;
import seedu.address.testutil.AddressBookBuilder;


public class JsonAdaptedPlanTest {

    private static final AddressBook ADDRESS_BOOK = new AddressBookBuilder()
            .withPerson(ALICE).build();
    private static final String INVALID_PLANNAME = "R@chel";
    private static final String INVALID_PLANTIME = "+651234";
    private static final String INVALID_PERSON = " ";

    private static final String VALID_PLANNAME = GAMING.getPlanName().toString();
    private static final String VALID_PLANTIME = GAMING.getPlanDateTime().toStringRaw();
    private static final String VALID_PERSON = GAMING.getPlanFriend().getName().fullName;
    @Test
    public void toModelType_validPlanDetails_returnsPlan() throws Exception {
        JsonAdaptedPlan plan = new JsonAdaptedPlan(GAMING);
        assertEquals(GAMING, plan.toModelType(ADDRESS_BOOK));
    }

    @Test
    public void toModelType_invalidPlanName_throwsIllegalValueException() {
        JsonAdaptedPlan plan =
                new JsonAdaptedPlan(INVALID_PLANNAME, VALID_PLANTIME, VALID_PERSON, false);
        String expectedMessage = PlanName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> plan.toModelType(ADDRESS_BOOK));
    }

    @Test
    public void toModelType_nullPlanName_throwsIllegalValueException() {
        JsonAdaptedPlan plan =
                new JsonAdaptedPlan(null, VALID_PLANTIME, VALID_PERSON, false);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, PlanName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> plan.toModelType(ADDRESS_BOOK));
    }

    @Test
    public void toModelType_invalidPlanTime_throwsIllegalValueException() {
        JsonAdaptedPlan plan =
                new JsonAdaptedPlan(VALID_PLANNAME, INVALID_PLANTIME, VALID_PERSON, false);
        String expectedMessage = PlanDateTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> plan.toModelType(ADDRESS_BOOK));
    }

    @Test
    public void toModelType_nullPlanTime_throwsIllegalValueException() {
        JsonAdaptedPlan plan =
                new JsonAdaptedPlan(VALID_PLANNAME, null, VALID_PERSON, false);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, PlanDateTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> plan.toModelType(ADDRESS_BOOK));
    }

    @Test
    public void toModelType_invalidPerson_throwsIllegalValueException() {
        JsonAdaptedPlan plan =
                new JsonAdaptedPlan(VALID_PLANNAME, VALID_PLANTIME, INVALID_PERSON, false);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> plan.toModelType(ADDRESS_BOOK));
    }

    @Test
    public void toModelType_nullPerson_throwsIllegalValueException() {
        JsonAdaptedPlan plan =
                new JsonAdaptedPlan(VALID_PLANNAME, VALID_PLANTIME, null, false);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> plan.toModelType(ADDRESS_BOOK));
    }
}
