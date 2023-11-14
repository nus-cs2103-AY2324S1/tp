package seedu.address.storage.finance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.ParserUtilTest.createMoreThanAllowedString;
import static seedu.address.storage.finance.JsonAdaptedCommission.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFinances.COMMISSION_FROM_ALICE;
import static seedu.address.testutil.TypicalFinances.COMMISSION_TEN_FROM_T;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.finance.Amount;
import seedu.address.model.finance.Description;
import seedu.address.model.finance.TimeDue;
import seedu.address.model.person.Person;
import seedu.address.storage.address.JsonAdaptedPerson;


public class JsonAdaptedCommissionTest {
    private static final String TEXT_MORE_THAN_256 = createMoreThanAllowedString();
    private static final String INVALID_CLIENT_NAME = TEXT_MORE_THAN_256;
    private static final String INVALID_AMOUNT = TEXT_MORE_THAN_256;
    private static final String INVALID_DESCRIPTION = TEXT_MORE_THAN_256;
    private static final String INVALID_TIME_DUE = TEXT_MORE_THAN_256;

    private static final Person VALID_CLIENT = COMMISSION_FROM_ALICE.getClient();
    private static final String VALID_AMOUNT = COMMISSION_FROM_ALICE.getAmount().toString();
    private static final String VALID_DESCRIPTION = COMMISSION_FROM_ALICE.getDescription().toString();
    private static final String VALID_TIME_DUE = COMMISSION_FROM_ALICE.getTimeDue().toString();

    @Test
    public void toModelType_validCommissionDetails_returnsCommission() throws Exception {
        JsonAdaptedCommission commission = new JsonAdaptedCommission(COMMISSION_TEN_FROM_T);
        assertEquals(COMMISSION_TEN_FROM_T.toString(), commission.toModelType().toString());
    }

    @Test
    public void toModelType_invalidAmount_throwsIllegalValueException() {
        JsonAdaptedCommission commission =
                new JsonAdaptedCommission(INVALID_AMOUNT, new JsonAdaptedPerson(VALID_CLIENT),
                        VALID_DESCRIPTION, VALID_TIME_DUE);
        String expectedMessage = Amount.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, commission::toModelType);
    }

    @Test
    public void toModelType_nullAmount_throwsIllegalValueException() {
        JsonAdaptedCommission commission =
                new JsonAdaptedCommission(null, new JsonAdaptedPerson(VALID_CLIENT),
                        VALID_DESCRIPTION, VALID_TIME_DUE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Amount.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, commission::toModelType);
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedCommission commission =
                new JsonAdaptedCommission(VALID_AMOUNT, new JsonAdaptedPerson(VALID_CLIENT),
                        INVALID_DESCRIPTION, VALID_TIME_DUE);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, commission::toModelType);
    }

    @Test
    public void toModelType_nullTimeDue_throwsIllegalValueException() {
        JsonAdaptedCommission commission =
                new JsonAdaptedCommission(VALID_AMOUNT, new JsonAdaptedPerson(VALID_CLIENT),
                        VALID_DESCRIPTION, INVALID_TIME_DUE);
        String expectedMessage = TimeDue.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, commission::toModelType);
    }
}
