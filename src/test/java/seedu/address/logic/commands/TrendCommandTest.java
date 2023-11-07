package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.trendresults.TrendCommandResult;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class TrendCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        Command command1 = new TrendCommand("2021");
        Command command2 = new TrendCommand("2021");
        Command command3 = new TrendCommand("2022");

        assertEquals(command1, command2);

        assertNotEquals(command1, command3);

        assertNotEquals(command1, null);

        assertFalse(command1.equals(0.5f));

    }

    @Test
    public void executeToCorrectCommandResult() throws CommandException {
        Command command1 = new TrendCommand("2022");

        assertTrue(command1.execute(model) instanceof TrendCommandResult);
    }

    @Test
    public void testEquality() {
        Command command1 = new TrendCommand("2023");
        Command command2 = new TrendCommand("2023");
        assertEquals(command1, command2);
    }

    @Test
    public void testToString() {
        Command command1 = new TrendCommand("2023");
        Command command2 = new TrendCommand("2023");
        assertEquals(command1.toString(), command2.toString());
    }
}
