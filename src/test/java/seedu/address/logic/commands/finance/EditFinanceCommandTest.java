package seedu.address.logic.commands.finance;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.finance.EditFinanceCommand.EditFinanceDescriptor;

public class EditFinanceCommandTest {
    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditFinanceDescriptor editFinanceDescriptor = new EditFinanceDescriptor();
        EditFinanceCommand editFinanceCommand = new EditFinanceCommand(index, editFinanceDescriptor);
        String expected = EditFinanceCommand.class.getCanonicalName() + "{index=" + index + ", editFinanceDescriptor="
                + editFinanceDescriptor + "}";
        assertEquals(expected, editFinanceCommand.toString());
    }
}
