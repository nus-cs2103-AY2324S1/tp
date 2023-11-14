package seedu.address.storage.finance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.ParserUtilTest.createMoreThanAllowedString;
import static seedu.address.storage.finance.JsonAdaptedExpense.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFinances.EXPENSE_THIRTY_TO_K;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.finance.Amount;
import seedu.address.model.finance.Description;
import seedu.address.model.person.Person;
import seedu.address.storage.address.JsonAdaptedPerson;

public class JsonAdaptedExpenseTest {
    private static final String TEXT_MORE_THAN_256 = createMoreThanAllowedString();
    private static final String INVALID_CLIENT_NAME = TEXT_MORE_THAN_256;
    private static final String INVALID_AMOUNT = TEXT_MORE_THAN_256;
    private static final String INVALID_DESCRIPTION = TEXT_MORE_THAN_256;

    private static final Person VALID_CLIENT = EXPENSE_THIRTY_TO_K.getClient();
    private static final String VALID_AMOUNT = EXPENSE_THIRTY_TO_K.getAmount().toString();
    private static final String VALID_DESCRIPTION = EXPENSE_THIRTY_TO_K.getDescription().toString();
    private static final String VALID_TIME_DUE = EXPENSE_THIRTY_TO_K.getTimeDue().toString();

    @Test
    public void toModelType_validExpenseDetails_returnsExpense() throws Exception {
        JsonAdaptedExpense expense = new JsonAdaptedExpense(EXPENSE_THIRTY_TO_K);
        assertEquals(EXPENSE_THIRTY_TO_K.toString(), expense.toModelType().toString());
    }

    @Test
    public void toModelType_invalidAmount_throwsIllegalValueException() {
        JsonAdaptedExpense expense =
                new JsonAdaptedExpense(INVALID_AMOUNT, new JsonAdaptedPerson(VALID_CLIENT),
                        VALID_DESCRIPTION, VALID_TIME_DUE);
        String expectedMessage = Amount.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, expense::toModelType);
    }

    @Test
    public void toModelType_nullAmount_throwsIllegalValueException() {
        JsonAdaptedExpense expense =
                new JsonAdaptedExpense(null, new JsonAdaptedPerson(VALID_CLIENT),
                        VALID_DESCRIPTION, VALID_TIME_DUE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Amount.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, expense::toModelType);
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedExpense expense =
                new JsonAdaptedExpense(VALID_AMOUNT, new JsonAdaptedPerson(VALID_CLIENT),
                        INVALID_DESCRIPTION, VALID_TIME_DUE);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, expense::toModelType);
    }
}
