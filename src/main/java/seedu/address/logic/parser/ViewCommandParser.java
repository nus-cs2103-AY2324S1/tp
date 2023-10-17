package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;

import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.Arrays;


public class ViewCommandParser implements Parser<ViewCommand> {

    public static final String STUDENT_CATEGORY = "students";
    public static final String APPOINTMENT_CATEGORY = "appointments";

    public ViewCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CATEGORY);


        String category = argMultimap.getValue(PREFIX_CATEGORY).orElse("");
        if (!category.equals(STUDENT_CATEGORY) && !category.equals(APPOINTMENT_CATEGORY)) {
            System.out.println(category);
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }

        return new ViewCommand(category);
    }
}
