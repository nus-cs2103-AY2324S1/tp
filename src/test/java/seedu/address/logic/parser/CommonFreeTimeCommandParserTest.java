package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommonFreetimeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class CommonFreeTimeCommandParserTest {
    private CommonFreeTimeCommandParser parser = new CommonFreeTimeCommandParser();

    @Test
    public void parse_validArgs_returnsCommonFreetimeCommand() throws ParseException {
        assertParseSuccess(parser, "1",
                new CommonFreetimeCommand(ParserUtil.parseIndex("1")));
    }

}
