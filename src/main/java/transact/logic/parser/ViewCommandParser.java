package transact.logic.parser;

import static transact.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import transact.logic.commands.ViewCommand;
import transact.logic.parser.exceptions.ParseException;
import transact.ui.MainWindow.TabWindow;

/**
 * Parses input arguments and creates a new ViewCommand object
 */
public class ViewCommandParser implements Parser<ViewCommand> {

    @Override
    public ViewCommand parse(String args) throws ParseException {
        String[] argList = args.trim().split("\\s+");
        if (argList.length < 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }

        TabWindow type;
        switch (argList[0].toLowerCase()) {
        case "staff":
        case "s":
            type = TabWindow.ADDRESSBOOK;
            break;
        case "transaction":
        case "t":
            type = TabWindow.TRANSACTIONS;
            break;
        case "overview":
        case "o":
            type = TabWindow.OVERVIEW;
            break;
        default:
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }

        return new ViewCommand(type);
    }
}
