package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CommandTypeTest {
    @Test
    public void toStringMethod() {
        assertTrue(CommandType.ADD.toString() == "Add Command");
        assertTrue(CommandType.CLEAR.toString() == "Clear Command");
        assertTrue(CommandType.DELETE.toString() == "Delete Command");
        assertTrue(CommandType.EDIT.toString() == "Edit Command");
        assertTrue(CommandType.EDIT_FIELD.toString() == "Edit Field Command");
        assertTrue(CommandType.EXIT.toString() == "Exit Command");
        assertTrue(CommandType.FIND.toString() == "Find Command");
        assertTrue(CommandType.HELP.toString() == "Help Command");
        assertTrue(CommandType.LIST.toString() == "List Command");
        assertTrue(CommandType.SAVE.toString() == "Save Command");
        assertTrue(CommandType.SORT.toString() == "Sort Command");
        assertTrue(CommandType.VIEW.toString() == "View Command");
        assertTrue(CommandType.VIEW_EXIT.toString() == "View Exit Command");
    }
}
