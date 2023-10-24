package transact.logic.parser;

import static transact.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import transact.logic.commands.ViewCommand;
import transact.logic.parser.exceptions.ParseException;

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

        ViewCommand.ViewType type;
        try {
            switch (argList[0].toLowerCase()) {
            case "s":
                type = ViewCommand.ViewType.STAFF;
                break;
            case "t":
                type = ViewCommand.ViewType.TRANSACTION;
                break;
            case "o":
                type = ViewCommand.ViewType.OVERVIEW;
                break;
            default:
                type = ViewCommand.ViewType.valueOf(argList[0].toUpperCase());
            }
        } catch (IllegalArgumentException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }

        return new ViewCommand(type);
    }
}
