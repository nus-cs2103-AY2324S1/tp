package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.DeleteShortcutCommand;

import java.util.ArrayList;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.SHORTCUT_ALIAS_1;
import static seedu.address.logic.commands.CommandTestUtil.SHORTCUT_DESC_VALID;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseBasicFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseBasicSuccess;


public class DeleteShortcutCommandParserTest {
    private DeleteShortcutCommandParser parser = new DeleteShortcutCommandParser();

    @Test
    public void parse_validInputs_returnsDeleteShortcutCommand() {
        assertParseBasicSuccess(parser, SHORTCUT_DESC_VALID,
                new DeleteShortcutCommand(new ArrayList<>() {
                    {
                        add(SHORTCUT_ALIAS_1);
                    }
                }));
    }

    @Test
    public void parse_invalidInputs_failure() {
        // Empty input
        assertParseBasicFailure(parser, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteShortcutCommand.MESSAGE_USAGE));
    }

}
