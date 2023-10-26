package transact.logic.parser;

import static transact.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static transact.logic.parser.CommandParserTestUtil.assertParseFailure;
import static transact.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import transact.logic.commands.ViewCommand;
import transact.ui.MainWindow.TabWindow;

public class ViewCommandParserTest {

    private final ViewCommandParser parser = new ViewCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_wrongArg_throwsParseException() {
        assertParseFailure(parser, "staffs", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgsStaff_returnsViewCommand() {
        // Use "staff" as argument
        ViewCommand expectedViewCommand = new ViewCommand(TabWindow.ADDRESSBOOK);
        assertParseSuccess(parser, "staff", expectedViewCommand);

        // Use "s" as argument
        assertParseSuccess(parser, "s", expectedViewCommand);
    }

    @Test
    public void parse_validArgsTransaction_returnsViewCommand() {
        // Use "transaction" as argument
        ViewCommand expectedViewCommand = new ViewCommand(TabWindow.TRANSACTIONS);
        assertParseSuccess(parser, "transaction", expectedViewCommand);

        // Use "t" as argument
        assertParseSuccess(parser, "t", expectedViewCommand);
    }

    @Test
    public void parse_validArgsOverview_returnsViewCommand() {
        // Use "overview" as argument
        ViewCommand expectedViewCommand = new ViewCommand(TabWindow.OVERVIEW);
        assertParseSuccess(parser, "overview", expectedViewCommand);

        // Use "o" as argument
        assertParseSuccess(parser, "o", expectedViewCommand);
    }

}
