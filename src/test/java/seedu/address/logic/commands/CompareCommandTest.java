package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.testutil.AnotherPersonBuilder;
import seedu.address.testutil.PersonBuilder;

public class CompareCommandTest {

    @Test
    public void execute_sameIndex_throwsCommandException() {
        Model model = new ModelManager();
        model.addPerson(new PersonBuilder().build());
        model.addPerson(new AnotherPersonBuilder().build());

        CompareCommand compareCommand = new CompareCommand(Index.fromOneBased(1),
                Index.fromOneBased(1));
        assertThrows(CommandException.class, () -> compareCommand.execute(model),
                "Error: Please provide distinct indices. " + "You cannot compare the same applicant.");
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Model model = new ModelManager();
        model.addPerson(new PersonBuilder().build());
        model.addPerson(new AnotherPersonBuilder().build());

        CompareCommand compareCommand = new CompareCommand(Index.fromOneBased(1), Index.fromOneBased(3));
        assertThrows(CommandException.class, () -> compareCommand.execute(model),
                "Error: One or both of the specified applicants were not found in the list.");
    }
}
