package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.logic.commands.ExportCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Visual;

public class ExportCommandParserTest {
    private ExportCommandParser parser = new ExportCommandParser();

    @Test
    public void parseExportValidAscSuccess() throws ParseException {
        String visual = "TABLE";
        assert new ExportCommand(new Visual(visual)).toString()
                .equals(parser.parse(CommandTestUtil.VISUAL_TABLE).toString());
    }

    @Test
    public void parseExportValidDescSuccess() throws ParseException {
        String visual = "BAR";
        assert new ExportCommand(new Visual(visual)).toString()
                .equals(parser.parse(CommandTestUtil.VISUAL_BAR).toString());
    }

    @Test
    public void parseVisualInvalidFailure() throws ParseException {
        // Invalid visual, should throw a ParseException
        String invalidVisual = "INVALID";
        try {
            parser.parse(invalidVisual);
            assert false;
        } catch (Exception e) {
            assert true;
        }
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExportCommand.MESSAGE_USAGE);

        // missing export in prefix
        assertParseFailure(parser, CommandTestUtil.VALID_VISUAL_BAR, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, CommandTestUtil.VALID_VISUAL_BAR, expectedMessage);
    }

}
