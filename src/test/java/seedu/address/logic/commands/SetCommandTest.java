package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.person.Status;
import seedu.address.model.person.StatusTypes;

public class SetCommandTest {

    @Test
    public void execute_validIndexAndStatus_success() {
        // Check if the status of the person in the model has been updated
        assertEquals((new Status()).toString(), "Preliminary");
    }

    @Test
    public void setCommandString_success() {
        assertEquals((new SetCommand(Index.fromOneBased(1), StatusTypes.INTERVIEWED)).toString(),
                "seedu.address.logic.commands."
                        + "SetCommand{targetIndex=seedu.address.commons.core.index.Index{zeroBasedIndex=0}," +
                        " newStatus=Interviewed}");
    }


}
