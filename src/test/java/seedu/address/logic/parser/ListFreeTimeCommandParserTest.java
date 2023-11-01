package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListFreeTimeCommand;

class ListFreeTimeCommandParserTest {
    private final ListFreeTimeCommandParser parser = new ListFreeTimeCommandParser();

    @Test
    public void parse_validArgs_returnsListFreeTimeCommand() {
        assertParseSuccess(parser, "1/11/2024",
                new ListFreeTimeCommand(LocalDateTime.of(2024, 11, 1, 0, 0)));
    }
}
