package seedu.address.logic.parser;
import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object with only one field to edit
 */
public class EditCommandMacroParser implements Parser<EditCommand> {

    public static final String MACRO_MESSAGE_USAGE = "Edit just one field of a client specified by index.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "Edit command macro usage: <Client Field> INDEX <New Value>\n"
            + "Client Field: name, phone, email, address, telegram, profession, income, details\n"
            + "Example: name 1 John Doe\n";

    private String commandWord;

    public EditCommandMacroParser(String commandWord) {
        this.commandWord = commandWord;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MACRO_MESSAGE_USAGE));
        }
        Index index;

        try {
            index = ParserUtil.parseIndex(trimmedArgs.split("\\s+")[0]);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MACRO_MESSAGE_USAGE), pe);
        }

        String argString = trimmedArgs.split("\\s+", 2)[1];
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        switch (commandWord) {
        case "name":
            editPersonDescriptor.setName(ParserUtil.parseName(argString));
            break;
        case "phone":
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argString));
            break;
        case "email":
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argString));
            break;
        case "address":
            editPersonDescriptor.setAddress(ParserUtil.parseAddress(argString));
            break;
        case "lead":
            editPersonDescriptor.setLead(ParserUtil.parseLead(argString));
            break;
        case "telegramhandle":
            editPersonDescriptor.setTelegram(ParserUtil.parseTelegram(argString));
            break;
        case "profession":
            editPersonDescriptor.setProfession(ParserUtil.parseProfession(argString));
            break;
        case "income":
            editPersonDescriptor.setIncome(ParserUtil.parseIncome(argString));
            break;
        case "details":
            editPersonDescriptor.setDetails(ParserUtil.parseDetails(argString));
            break;
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MACRO_MESSAGE_USAGE));
        }
        return new EditCommand(index, editPersonDescriptor);
    }
}
