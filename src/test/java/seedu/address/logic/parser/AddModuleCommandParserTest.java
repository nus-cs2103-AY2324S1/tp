package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CS2100;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddModuleCommand;
import seedu.address.model.module.Module;
import seedu.address.testutil.ModuleBuilder;

public class AddModuleCommandParserTest {
    private AddModuleCommandParser parser = new AddModuleCommandParser();

    @Test
    public void parse_modulePresent_success() {
        Module testModule = new ModuleBuilder().build(VALID_MODULE_CS2100);

        assertParseSuccess(parser, " m/" + VALID_MODULE_CS2100, new AddModuleCommand(testModule));
    }

    @Test
    public void parse_failedInputs_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddModuleCommand.MESSAGE_USAGE);
        String emptyInput = " ";
        String nonsenseInput = "thism/";

        assertParseFailure(parser, emptyInput, expectedMessage);
        assertParseFailure(parser, nonsenseInput, expectedMessage);
    }
}
