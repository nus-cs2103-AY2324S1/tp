package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.COMMANDWORD_DESC_VALID;
import static seedu.address.logic.commands.CommandTestUtil.COMMAND_WORD_1;
import static seedu.address.logic.commands.CommandTestUtil.SHORTCUT_ALIAS_1;
import static seedu.address.logic.commands.CommandTestUtil.SHORTCUT_DESC_VALID;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseBasicFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseBasicSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddShortcutCommand;

public class AddShortcutCommandParserTest {

    private AddShortcutCommandParser parser = new AddShortcutCommandParser();

    @Test
    public void parse_validInputs_returnsAddShortcutCommand() {
        assertParseBasicSuccess(parser, SHORTCUT_DESC_VALID + COMMANDWORD_DESC_VALID,
                new AddShortcutCommand(SHORTCUT_ALIAS_1, COMMAND_WORD_1));
    }

    @Test
    public void parse_notAllTagsPresent_failure() {
        // Missing command word
        assertParseBasicFailure(parser, SHORTCUT_DESC_VALID,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddShortcutCommand.MESSAGE_USAGE));

        // Missing alias
        assertParseBasicFailure(parser, COMMANDWORD_DESC_VALID,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddShortcutCommand.MESSAGE_USAGE));


    }
}
