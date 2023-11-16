package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEW_PASSWORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSWORD;

import seedu.address.logic.commands.ChangePasswordCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parser to handle user input and create a {@link ChangePasswordCommand}.
 * This command is used to change the user's password.
 * It requires the current password and the new password as arguments.
 * Both the current password and the new password should be provided as part of the command.
 */
public class ChangePasswordCommandParser implements Parser<ChangePasswordCommand> {
    /**
     * Parses user input and creates a {@link ChangePasswordCommand} based on the provided passwords.
     *
     * @param args User input arguments.
     * @return A {@link ChangePasswordCommand} for changing the password.
     * @throws ParseException If the user input does not conform to the expected format.
     */
    @Override
    public ChangePasswordCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PASSWORD, PREFIX_NEW_PASSWORD);
        if (argMultimap.getValue(PREFIX_PASSWORD).isPresent()
                && argMultimap.getValue(PREFIX_NEW_PASSWORD).isPresent()) {
            String currentPw = ParserUtil.parsePassword(argMultimap.getValue(PREFIX_PASSWORD).get());
            String newPw = ParserUtil.parsePassword(argMultimap.getValue(PREFIX_NEW_PASSWORD).get());
            return new ChangePasswordCommand(currentPw, newPw);
        }
        throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChangePasswordCommand.MESSAGE_USAGE));
    }
}
