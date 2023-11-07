package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.barchartresults.EnrolDateBarChartCommandResult;
import seedu.address.logic.commands.barchartresults.GenderBarChartCommandResult;
import seedu.address.logic.commands.barchartresults.SecLevelBarChartCommandResult;
import seedu.address.logic.commands.barchartresults.SubjectBarChartCommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


public class BarChartCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        Command command1 = new BarChartCommand("g/");
        Command command2 = new BarChartCommand("g/");
        Command command3 = new BarChartCommand("s/");
        Command command4 = new BarChartCommand("d/", 2023);
        Command command5 = new BarChartCommand("d/", 2023);
        Command command6 = new BarChartCommand("d/Z", 2022);

        // same args -> return true
        assertEquals(command1, command1);
        assertEquals(command1, command2);
        assertEquals(command4, command5);

        // different args -> return false
        assertNotEquals(command1, command3);
        assertNotEquals(command2, command4);
        assertNotEquals(command4, command6);

        // null -> return false
        assertNotEquals(command1, null);

        //different types -> return false
        assertFalse(command1.equals(0.5f));
    }

    @Test
    public void constructor_unknownArg_throwException() {
        assertThrows(CommandException.class, () -> new BarChartCommand("f/").execute(model));
    }


    @Test
    public void executeToCorrectCommandResult() throws CommandException {
        Command command1 = new BarChartCommand("g/");
        Command command2 = new BarChartCommand("l/");
        Command command3 = new BarChartCommand("s/");
        Command command4 = new BarChartCommand("d/", 2023);

        assertTrue(command1.execute(model) instanceof GenderBarChartCommandResult);
        assertTrue(command2.execute(model) instanceof SecLevelBarChartCommandResult);
        assertTrue(command3.execute(model) instanceof SubjectBarChartCommandResult);
        assertTrue(command4.execute(model) instanceof EnrolDateBarChartCommandResult);
    }

    @Test
    public void toStringMethod() {
        Command command1 = new BarChartCommand("g/");
        Command command2 = new BarChartCommand("l/");
        Command command3 = new BarChartCommand("s/");
        Command command4 = new BarChartCommand("d/", 2023);

        String expectedClass = BarChartCommand.class.getCanonicalName();
        assertEquals(command1.toString(), expectedClass + "{category: =g/}");
        assertEquals(command2.toString(), expectedClass + "{category: =l/}");
        assertEquals(command3.toString(), expectedClass + "{category: =s/}");
        assertEquals(command4.toString(), expectedClass + "{category: =d/2023}");
    }
}
